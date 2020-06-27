package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.DetalleNota;
import edu.kalum.notas.core.models.entity.Seminario;
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
public class DetalleActividadController {
    private Logger logger= LoggerFactory.getLogger(DetalleActividadController.class);
    @Autowired
    private IDetalleActividadService detalleActividadService;
    @Autowired
    private IDetalleNotaService detalleNotaService;

    @GetMapping("/detalle-actividades")
    public ResponseEntity<?> listarDetalleActividades(){
        logger.info("Iniciando proceso de consultas de detalle actividades");
        Map<String,Object> response = new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<DetalleActividad> detalleActividades=this.detalleActividadService.findAll();
            if (detalleActividades.size() == 0) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Finalizando proceso de consulta de detalle actividades");
                return new ResponseEntity<List<DetalleActividad>>(detalleActividades,HttpStatus.OK);
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

    @GetMapping("detalle-actividades/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        logger.info("Iniciando proceso de consultas de detalle actividad por id");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            DetalleActividad detalleActividad= this.detalleActividadService.findById(id);
            if(detalleActividad==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                logger.info("Finalizando proceso de consulta de detalle actividad");
                return new ResponseEntity<DetalleActividad>(detalleActividad,HttpStatus.OK);
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

    @PostMapping("/detalle-actividades")
    public ResponseEntity<?> create(@Valid @RequestBody DetalleActividad elemnto, BindingResult result) {
        logger.info("Iniciando proceso de creacion de detalle actividades");
        Map<String, Object> response = new HashMap<>();
        DetalleActividad detalleActividad = null;
        if (result.hasErrors()) {
            logger.error("Errores");
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            logger.info("Iniciando insercion de datos");
            elemnto.setDetalleActividadId(UUID.randomUUID().toString());
            detalleActividad= this.detalleActividadService.save(elemnto);
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
        response.put("mensaje","El detalle de actividad ha sido creado con exito");
        response.put("detalle actividad",detalleActividad);
        logger.info("Finalizando proceso de consulta de detalle actividad");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/detalle-actividades/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody DetalleActividad value, BindingResult result, @PathVariable String id){
        logger.info("Iniciando proceso de modificacion de detalle actividad");
        Map<String,Object> response=new HashMap<>();
        logger.debug("Iniciando consulta a la base de datos");
        DetalleActividad update=this.detalleActividadService.findById(id);
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
            update.setNombreActividad(value.getNombreActividad());
            this.detalleActividadService.save(update);
            update.setNotaActividad(value.getNotaActividad());
            this.detalleActividadService.save(update);
            update.setFechaCreacion(value.getFechaCreacion());
            this.detalleActividadService.save(update);
            update.setFechaEntrega(value.getFechaEntrega());
            this.detalleActividadService.save(update);
            update.setFechaPostergacion(value.getFechaPostergacion());
            this.detalleActividadService.save(update);
            update.setEstado(value.getEstado());
            this.detalleActividadService.save(update);
            update.setSeminario(value.getSeminario());
            this.detalleActividadService.save(update);
            response.put("mensaje","El detalle de actividad ha sido actualizado correctamente");
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

    @DeleteMapping("/detalle-actividades/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        logger.info("Iniciando proceso de eliminacion de detalle actividad por id");
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            logger.debug("Iniciando consulta a la base de datos");
            DetalleActividad registro =this.detalleActividadService.findById(id);
            if(registro==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existe el registro con el id".concat(id));
                response.put("Error","No existe el registro con el id".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }
            logger.info("Finalizando proceso de consulta de detalle actividad");
            this.detalleActividadService.delete(id);
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al eliminar el detalle actividad en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al eliminar el detalle nota de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","El detalle actividad ha sido eliminado correctamente");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @GetMapping("/detalle-actividades/{id}/detalle-notas")
    public ResponseEntity<?> showDetalleNotas(@PathVariable String id){
        logger.info("Iniciando proceso de consultas de detalle notas segun detalle actividades");
        Map<String,Object> response=new HashMap<>();

        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<DetalleNota> detalleNotas=this.detalleNotaService.buscarDetalleNotas(id);
            if(detalleNotas==null || detalleNotas.size()==0){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existen detalle actividades para este detalle nota con el id".concat(id));

                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
            }else{
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

}
