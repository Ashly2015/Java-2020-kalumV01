package edu.kalum.notas.core.controllers;

import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.service.ICarreraTecnicaService;
import edu.kalum.notas.core.models.service.IModuloService;
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
public class ModuloController {
    @Autowired
    private IModuloService moduloService;


    @GetMapping("/modulos")
    public ResponseEntity<?> listarModulos(){
        Map<String,Object> response = new HashMap<>();
        try {
            List<Modulo> modulos=this.moduloService.findAll();
            if (modulos.size() == 0) {
                response.put("mensaje", "No exsisten registros");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            } else {
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
    @GetMapping("modulos/{id}")
    public ResponseEntity<?> show (@PathVariable String id){
        Map<String,Object> response=new HashMap<>();
        try {
            Modulo modulo= this.moduloService.findById(id);
            if(modulo==null){
                response.put("mensaje","No exsisten registros con el id: ".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Modulo>(modulo,HttpStatus.OK);
            }

        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar la conexion a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @PostMapping("/modulos")
    public ResponseEntity<?> create(@Valid @RequestBody Modulo elemnto, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        Modulo modulo = null;
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            elemnto.setModuloId(UUID.randomUUID().toString());
            modulo= this.moduloService.save(elemnto);
        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al realizar la conexion a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar el insert a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
        response.put("mensaje","El modulo ha sido creado con exito");
        response.put("modulo",modulo);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/modulos/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody Modulo value, BindingResult result, @PathVariable String id){
        Map<String,Object> response=new HashMap<>();
        Modulo update=this.moduloService.findById(id);
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
            update.setNombreModulo(value.getNombreModulo());
            this.moduloService.save(update);
            update.setNumeroSeminarios(value.getNumeroSeminarios());
            this.moduloService.save(update);
            update.setCarreraTecnica(value.getCarreraTecnica());
            this.moduloService.save(update);
            response.put("mensaje","El modulo ha sido actualizado correctamente");
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

    @DeleteMapping("/modulos/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String,Object> response=new HashMap<String,Object>();
        try{
            Modulo registro =this.moduloService.findById(id);
            if(registro==null){
                response.put("mensaje", "No existe el registro con el id".concat(id));
                response.put("Error","No existe el registro con el id".concat(id));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }
            this.moduloService.delete(id);
        }catch (CannotCreateTransactionException e){
            response.put("mensaje","Error al eliminar el modulo en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar el modulo de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));

        }

        response.put("mensaje","El modulo ha sido eliminado correctamente");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
}
