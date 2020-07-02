package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.service.ICarreraTecnicaService;
import edu.kalum.notas.core.models.service.IModuloService;
import javassist.tools.reflect.CannotCreateException;
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
public class CarreraTecnicaController {
    private Logger logger= LoggerFactory.getLogger(CarreraTecnicaController.class);

    @Autowired
    private ICarreraTecnicaService carreraTecnicaService;
    @Autowired
    private IModuloService moduloService;


    @GetMapping("/carrerasTecnicas")
    public  ResponseEntity<?> listarCarrerasTecnicas(){
        logger.info("Iniciando proceso de consultas de carreras tecnincas");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<CarreraTecnica> carreras = this.carreraTecnicaService.findAll();
            if (carreras.size() == 0) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Finalizando proceso de consulta de carreras tecnicas");
                return new ResponseEntity<List<CarreraTecnica>>(carreras, HttpStatus.OK);
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

    @GetMapping("/carrerasTecnicas/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        logger.info("Iniciando proceso de consultas de carreras tecnincas");
        Map<String,Object> response=new HashMap<>();
        try {
            logger.debug("Iniciando consulta a la base de datos");
            CarreraTecnica carrera= this.carreraTecnicaService.findById(id);

            if (carrera==null) {
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                logger.info("Finalizando proceso de consulta de carreras tecnicas");
                return new ResponseEntity<CarreraTecnica>(carrera,HttpStatus.OK);
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

    @PostMapping("/carrerasTecnicas")
    public ResponseEntity<?> create(@Valid @RequestBody CarreraTecnica elemnto, BindingResult result) {
        logger.info("Iniciando proceso de creacion de carreras tecnincas");
        CarreraTecnica carreraTecnica = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            logger.error("Errores");
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            logger.info("Iniciando insercion de datos");
            elemnto.setCarreraId(UUID.randomUUID().toString());
            carreraTecnica= this.carreraTecnicaService.save(elemnto);
        } catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }

        response.put("mensaje","La carrera tecnica ha sido creada con exito");
        response.put("carreraTecnica",carreraTecnica);
        logger.info("Finalizando proceso de consulta de carreras tecnicas");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/carrerasTecnicas/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        logger.info("Iniciando proceso de eliminacion de carreras tecnincas");
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            logger.debug("Iniciando consulta a la base de datos");
            CarreraTecnica registro =this.carreraTecnicaService.findById(id);
            if(registro==null){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existe el registro con el id".concat(id));
                response.put("Error","No existe el registro con el id".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }

            this.carreraTecnicaService.delete(id);
        }catch (CannotCreateTransactionException e){
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensaje","Error al eliminar la carrera tecnica en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            logger.error("Error al consultar la informacion a la base de datos");
            response.put("mensaje","Error al eliminar la carrera tecnica de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","La carrera tecnica ha sido eliminada correctamente");
        logger.info("Finalizando proceso de consulta de carreras tecnicas");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @PutMapping("/carrerasTecnicas/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody CarreraTecnica value, BindingResult result, @PathVariable String id){
        logger.info("Iniciando proceso de modificacion de carreras tecnincas");
        Map<String,Object> response=new HashMap<>();
        logger.debug("Iniciando consulta a la base de datos");
        CarreraTecnica update=this.carreraTecnicaService.findById(id);
        if(update==null){
            logger.warn("No existen registros en la base de datos");
            response.put("mensaje", "No existe el registro con el id".concat(id));
            response.put("Error","No existe el registro con el id".concat(id));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        logger.info("Finalizando proceso de consulta de carreras tecnicas");
        if(result.hasErrors()){
            List<String> errores=result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            update.setNombreCarrera(value.getNombreCarrera());
            this.carreraTecnicaService.save(update);
            response.put("mensaje","La carrera tecnica ha sido actualizada correctamente");
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
    @GetMapping("/carrerasTecnicas/{id}/modulos")
    public ResponseEntity<?> showModulos(@PathVariable String id){
        logger.info("Iniciando proceso de consultas de modulos segun carreras tecnincas");
        Map<String,Object> response=new HashMap<>();

        try {
            logger.debug("Iniciando consulta a la base de datos");
            List<Modulo> modulos=this.moduloService.buscarModulos(id);
            if(modulos==null || modulos.size()==0){
                logger.warn("No existen registros en la base de datos");
                response.put("mensaje", "No existen modulos para esta carrera tecnica con el id".concat(id));

                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
            }else{
                logger.info("Finalizando proceso de consulta de carreras tecnicas");
                return new ResponseEntity<List<Modulo>>(modulos,HttpStatus.OK);
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
