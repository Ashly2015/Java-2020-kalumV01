package edu.kalum.oauth.core.controller;

import edu.kalum.oauth.core.models.entity.Usuario;
import edu.kalum.oauth.core.models.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kalum-oauth/v1")
public class UsuarioController {
    private Logger logger= LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/usuarios")
    public  ResponseEntity<?> listarUsuarios(){
        logger.info("Iniciando proceso de consultas de cusuarios");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<Usuario> usuarios = this.usuarioService.findAll();
            if (usuarios.size() == 0) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Finalizando proceso de consulta de usuarios");
                return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> show (@PathVariable Long id){
        logger.info("Iniciando proceso de consultas de usuario");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            Usuario usuario= this.usuarioService.findById(id);

            if (usuario==null) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje","No exsisten registros con el id: ".concat(String.valueOf(id)));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                logger.info("Finalizando proceso de consulta de usuario");
                return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }

    }



    @PostMapping("/usuarios")
    public ResponseEntity<?> create(@Valid @RequestBody Usuario value, BindingResult result){
        Usuario usuario=null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            logger.error("Errores");
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            logger.info("Iniciando insercion de datos");
            value.setPassword(passwordEncoder.encode(value.getPassword()));
            usuario= this.usuarioService.save(value);
        } catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }

        response.put("mensaje","El usuario ha sido creada con exito");
        response.put("usuario",usuario);
        logger.info("Finalizando proceso de insercion de usuario");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        logger.info("Iniciando proceso de eliminacion de usuarios");
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            logger.debug("Iniciando consulta a la base de datos");
            Usuario registro =this.usuarioService.findById(id);
            if(registro==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existe el registro con el id".concat(String.valueOf(id)));
                response.put("Error","No existe el registro con el id".concat(String.valueOf(id)));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }

            this.usuarioService.delete(id);
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al eliminar el usuario en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al eliminar el usuario de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","El usuario ha sido eliminada correctamente");
        logger.info("Finalizando proceso de consulta de usuarios");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody Usuario value, BindingResult result, @PathVariable Long id){
        logger.info("Iniciando proceso de modificacion de usuarios");
        Map<String,Object> response=new HashMap<>();
        logger.debug("Iniciando consulta a la base de datos");
        Usuario update=this.usuarioService.findById(id);
        if(update==null){
            logger.warn("No existen registros en la base de datos");
            response.put("mensaje", "No existe el registro con el id".concat(String.valueOf(id)));
            response.put("Error","No existe el registro con el id".concat(String.valueOf(id)));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        logger.info("Finalizando proceso de consulta de usuarios");
        if(result.hasErrors()){
            List<String> errores=result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            update.setPassword(value.getPassword());
            this.usuarioService.save(update);
            update.setUsername(value.getUsername());
            this.usuarioService.save(update);
            response.put("mensaje","El usurio ha sido actualizada correctamente");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);

        }catch (DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al actualizar la informcaion");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);


        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
