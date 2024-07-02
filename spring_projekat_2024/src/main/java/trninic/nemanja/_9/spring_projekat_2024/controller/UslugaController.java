package trninic.nemanja._9.spring_projekat_2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trninic.nemanja._9.spring_projekat_2024.entity.Usluga;
import trninic.nemanja._9.spring_projekat_2024.model.UslugaModel;
import trninic.nemanja._9.spring_projekat_2024.service.UslugaServis;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/usluga")
public class UslugaController {

    private final UslugaServis servis;

    @GetMapping
    public List<Usluga> getAll(){
        return servis.getAllUsluge();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usluga> getById(@PathVariable Integer id){
        return ResponseEntity.of(servis.getUslugaById(id));
    }

    @PostMapping
    public Usluga createUsluga(@RequestBody UslugaModel model){
        return  servis.saveUsluga(model);
    }

    @PutMapping(path = "/{id}")
    public Usluga updateUsluga(@PathVariable Integer id ,@RequestBody UslugaModel model){
        return  servis.updateUsluga(id,model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsluga(@PathVariable Integer id){
        servis.deleteUsluga(id);
    }

}
