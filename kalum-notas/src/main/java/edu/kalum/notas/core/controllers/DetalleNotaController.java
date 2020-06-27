package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.DetalleNota;
import edu.kalum.notas.core.models.service.IDetalleActividadService;
import edu.kalum.notas.core.models.service.IDetalleNotaService;
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
public class DetalleNotaController {
    private Logger logger= LoggerFactory.getLogger(DetalleNotaController.class);
    @Autowired
    private IDetalleNotaService detalleNotaService;

    @GetMapping("/detalle-notas")
    public ResponseEntity<?> listarDetalleANotas(){
        logger.info("Iniciando proceso de consultas de detalle notas");
        Map<String,Object> response = new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<DetalleNota> detalleNotas=this.detalleNotaService.findAll();
            if (detalleNotas.size() == 0) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Finalizando proceso de consulta de detalle notas");
                return new ResponseEntity<List<DetalleNota>>(detalleNotas,HttpStatus.OK);
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

    @GetMapping("detalle-notas/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        logger.info("Iniciando proceso de consultas de detalle nota por id");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            DetalleNota detalleNota= this.detalleNotaService.findById(id);
            if(detalleNota==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                logger.info("Finalizando proceso de consulta de detalle nota");
                return new ResponseEntity<DetalleNota>(detalleNota,HttpStatus.OK);
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

    @PostMapping("/detalle-notas")
    public ResponseEntity<?> create(@Valid @RequestBody DetalleNota elemnto, BindingResult result) {
        logger.info("Iniciando proceso de creacion de detalle nota");
        Map<String, Object> response = new HashMap<>();
        DetalleNota detalleNota = null;
        if (result.hasErrors()) {
            logger.error("Errores");
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            logger.info("Iniciando insercion de datos");
            elemnto.setDetalleNotaId(UUID.randomUUID().toString());
            detalleNota= this.detalleNotaService.save(elemnto);
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
        response.put("mensaje","El detalle de nota ha sido creado con exito");
        response.put("detalle nota",detalleNota);
        logger.info("Finalizando proceso de consulta de detalle nota");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/detalle-notas/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody DetalleNota value, BindingResult result, @PathVariable String id){
        logger.info("Iniciando proceso de modificacion de detalle nota");
        Map<String,Object> response=new HashMap<>();
        logger.debug("Iniciando consulta a la base de datos");
        DetalleNota update=this.detalleNotaService.findById(id);
        if(update==null){
            logger.warn("No existen registros en la base de datos");
            response.put("mensaje", "No existe el registro con el id".concat(id));
            response.put("Error","No existe el registro con el id".concat(id));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        if(result.hasErrors()){
            List<String> errores=result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            update.setValorNota(value.getValorNota());
            this.detalleNotaService.save(update);
            update.setDetalleActividad(value.getDetalleActividad());
            this.detalleNotaService.save(update);
            update.setAlumno(value.getAlumno());
            this.detalleNotaService.save(update);
            response.put("mensaje","El detalle de nota ha sido actualizado correctamente");
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

    @DeleteMapping("/detalle-notas/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        logger.info("Iniciando proceso de eliminacion de detalle nota por id");
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            logger.debug("Iniciando consulta a la base de datos");
            DetalleNota registro =this.detalleNotaService.findById(id);
            if(registro==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existe el registro con el id".concat(id));
                response.put("Error","No existe el registro con el id".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }
            logger.info("Finalizando proceso de consulta de detalle nota");
            this.detalleNotaService.delete(id);
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al eliminar el detalle nota en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al eliminar el detalle nota de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","El detalle nota ha sido eliminado correctamente");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }


}
