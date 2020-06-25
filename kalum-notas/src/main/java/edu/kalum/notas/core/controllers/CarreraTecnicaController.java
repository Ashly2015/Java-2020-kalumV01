package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.service.ICarreraTecnicaService;
import edu.kalum.notas.core.models.service.IModuloService;
import javassist.tools.reflect.CannotCreateException;
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

    @Autowired
    private ICarreraTecnicaService carreraTecnicaService;
    @Autowired
    private IModuloService moduloService;

    @GetMapping("/carrerasTecnicas")
    public  ResponseEntity<?> listarCarrerasTecnicas(){

        Map<String,Object> response=new HashMap<>();
        try {
            List<CarreraTecnica> carreras = this.carreraTecnicaService.findAll();
            if (carreras.size() == 0) {
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<CarreraTecnica>>(carreras, HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/carrerasTecnicas/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        Map<String,Object> response=new HashMap<>();
        try {
            CarreraTecnica carrera= this.carreraTecnicaService.findById(id);

            if (carrera==null) {
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<CarreraTecnica>(carrera,HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @PostMapping("/carrerasTecnicas")
    public ResponseEntity<?> create(@Valid @RequestBody CarreraTecnica elemnto, BindingResult result) {
        CarreraTecnica carreraTecnica = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            elemnto.setCarreraId(UUID.randomUUID().toString());
            carreraTecnica= this.carreraTecnicaService.save(elemnto);
        } catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }

        response.put("mensaje","La carrera tecnica ha sido creada con exito");
        response.put("carreraTecnica",carreraTecnica);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/carrerasTecnicas/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            CarreraTecnica registro =this.carreraTecnicaService.findById(id);
            if(registro==null){
                response.put("mensaje", "No existe el registro con el id".concat(id));
                response.put("Error","No existe el registro con el id".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }
            this.carreraTecnicaService.delete(id);
        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al eliminar la carrera tecnica en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar la carrera tecnica de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","La carrera tecnica ha sido eliminada correctamente");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @PutMapping("/carrerasTecnicas/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody CarreraTecnica value, BindingResult result, @PathVariable String id){
        Map<String,Object> response=new HashMap<>();
        CarreraTecnica update=this.carreraTecnicaService.findById(id);
        if(update==null){
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
            update.setNombreCarrera(value.getNombreCarrera());
            this.carreraTecnicaService.save(update);
            response.put("mensaje","La carrera tecnica ha sido actualizada correctamente");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);

        }catch (DataAccessException e){
            response.put("mensaje","Error al actualizar la informcaion");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);


        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    @GetMapping("/carrerasTecnicas/{id}/modulos")
    public ResponseEntity<?> showModulos(@PathVariable String id){
        Map<String,Object> response=new HashMap<>();

        try {
            List<Modulo> modulos=this.moduloService.buscarModulos(id);
            if(modulos==null || modulos.size()==0){
                response.put("mensaje", "No existen modulos para esta carrera tecnica con el id".concat(id));

                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<List<Modulo>>(modulos,HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }


    }

}
