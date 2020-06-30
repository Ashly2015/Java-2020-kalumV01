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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/kalum-oauth/v1")
public class UsuarioController {
    private Logger logger= LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/usuarios")
    public ResponseEntity<?> create(@RequestBody Usuario value){
        Usuario usuario=null;
        Map<String, Object> response = new HashMap<>();

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



}
