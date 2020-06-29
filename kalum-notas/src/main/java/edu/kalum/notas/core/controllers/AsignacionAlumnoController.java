package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entity.Alumno;
import edu.kalum.notas.core.models.entity.AsignacionAlumno;
import edu.kalum.notas.core.models.service.IAlumnoServiceImpl;
import edu.kalum.notas.core.models.service.IAsignacionAlumnoService;
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
public class AsignacionAlumnoController {
    private Logger logger= LoggerFactory.getLogger(AsignacionAlumnoController.class);
    @Autowired
    private IAsignacionAlumnoService asignacionAlumnoService;

    @GetMapping("/asignaciones-alumnos")
    public ResponseEntity<?> listarAsignacionesAlumnos(){
        logger.info("Iniciando proceso de consultas de asignaciones alumnos");
        Map<String,Object> response = new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<AsignacionAlumno> asignacionAlumnos=this.asignacionAlumnoService.findAll();
            if (asignacionAlumnos.size() == 0) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Finalizando proceso de consulta de asignaciones alumnos");
                return new ResponseEntity<List<AsignacionAlumno>>(asignacionAlumnos,HttpStatus.OK);
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

    @GetMapping("asignaciones-alumnos/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        logger.info("Iniciando proceso de consultas de asignacion alumno por id");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            AsignacionAlumno asignacionAlumno= this.asignacionAlumnoService.findById(id);
            if(asignacionAlumno==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                logger.info("Finalizando proceso de consulta de asignacion alumno");
                return new ResponseEntity<AsignacionAlumno>(asignacionAlumno,HttpStatus.OK);
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

    @PostMapping("/asignaciones-alumnos")
    public ResponseEntity<?> create(@Valid @RequestBody AsignacionAlumno elemnto, BindingResult result) {
        logger.info("Iniciando proceso de creacion de asignacion alumno");
        Map<String, Object> response = new HashMap<>();
        AsignacionAlumno asignacionAlumno = null;
        if (result.hasErrors()) {
            logger.error("Errores");
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            logger.info("Iniciando insercion de datos");
            elemnto.setAsignacionId(UUID.randomUUID().toString());
            asignacionAlumno= this.asignacionAlumnoService.save(elemnto);
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
        response.put("mensaje","La aignacion del alumno ha sido creado con exito");
        response.put("asignacion alumno",asignacionAlumno);
        logger.info("Finalizando proceso de creacion de asignacion de alumno");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/asignaciones-alumnos/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody AsignacionAlumno value, BindingResult result, @PathVariable String id){
        logger.info("Iniciando proceso de modificacion de asignacion alumno");
        Map<String,Object> response=new HashMap<>();
        logger.debug("Iniciando consulta a la base de datos");
        AsignacionAlumno update=this.asignacionAlumnoService.findById(id);
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
            update.setFechaAsignacion(value.getFechaAsignacion());
            this.asignacionAlumnoService.save(update);
            update.setAlumno(value.getAlumno());
            this.asignacionAlumnoService.save(update);
            update.setClase(value.getClase());
            this.asignacionAlumnoService.save(update);
            update.setObservaciones(value.getObservaciones());
            this.asignacionAlumnoService.save(update);
            response.put("mensaje","La asignacion del alumno ha sido actualizado correctamente");
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

    @DeleteMapping("/asignaciones-alumnos/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        logger.info("Iniciando proceso de eliminacion de asignacion alumno por id");
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            logger.debug("Iniciando consulta a la base de datos");
            AsignacionAlumno registro =this.asignacionAlumnoService.findById(id);
            if(registro==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existe el registro con el id".concat(id));
                response.put("Error","No existe el registro con el id".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }
            logger.info("Finalizando proceso de eliminacion de asignacion alumno");
            this.asignacionAlumnoService.delete(id);
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al eliminar la asignacion del alumno en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al eliminar la asignacion del alumno de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","La asignacion del  alumno ha sido eliminado correctamente");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }


}
