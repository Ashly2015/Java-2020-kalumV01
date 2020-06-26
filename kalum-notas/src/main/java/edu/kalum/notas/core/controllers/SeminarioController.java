package edu.kalum.notas.core.controllers;


import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.entity.Seminario;
import edu.kalum.notas.core.models.service.ISeminarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/kalum-notas/v1")
public class SeminarioController {
    private Logger logger= LoggerFactory.getLogger(SeminarioController.class);
    @Autowired
    private ISeminarioService seminarioService;

    @GetMapping("/seminarios")
    public ResponseEntity<?> listarSeminarios(){
        logger.info("Iniciando proceso de consultas de seminarios");
        Map<String,Object> response = new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<Seminario> seminarios=this.seminarioService.findAll();
            if (seminarios.size() == 0) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Finalizando proceso de consulta de seminarios");
                return new ResponseEntity<List<Seminario>>(seminarios,HttpStatus.OK);
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

    @GetMapping("seminarios/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        logger.info("Iniciando proceso de consultas de seminarios por id");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            Seminario seminario= this.seminarioService.findById(id);
            if(seminario==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                logger.info("Finalizando proceso de consulta de seminarios");
                return new ResponseEntity<Seminario>(seminario,HttpStatus.OK);
            }

        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar la conexion a la base de datos");
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

    @PostMapping("/seminarios")
    public ResponseEntity<?> create(@Valid @RequestBody Seminario elemnto, BindingResult result) {
        logger.info("Iniciando proceso de creacion de seminarios");
        Map<String, Object> response = new HashMap<>();
        Seminario seminario = null;
        if (result.hasErrors()) {
            logger.error("Errores");
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            logger.info("Iniciando insercion de datos");
            elemnto.setSeminarioId(UUID.randomUUID().toString());
            seminario= this.seminarioService.save(elemnto);
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar la conexion a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        response.put("mensaje","El seminario ha sido creado con exito");
        response.put("modulo",seminario);
        logger.info("Finalizando proceso de consulta de seminarios");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }


}
