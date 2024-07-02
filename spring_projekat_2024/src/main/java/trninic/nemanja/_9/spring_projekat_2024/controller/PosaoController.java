package trninic.nemanja._9.spring_projekat_2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trninic.nemanja._9.spring_projekat_2024.entity.Posao;
import trninic.nemanja._9.spring_projekat_2024.entity.Usluga;
import trninic.nemanja._9.spring_projekat_2024.model.PosaoModel;
import trninic.nemanja._9.spring_projekat_2024.model.UslugaModel;
import trninic.nemanja._9.spring_projekat_2024.service.PosaoServis;

import java.util.List;

@RestController
@RequestMapping(path = "/api/posao")
@RequiredArgsConstructor
//prvo povezivanje na html jer cross-origin nije dozvoljen
@CrossOrigin

public class PosaoController {
    private final PosaoServis posaoServis;

    @GetMapping
    public List<Posao> getAll(){
        return posaoServis.getAllPoslove();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Posao> getById(@PathVariable Integer id){
        return ResponseEntity.of(posaoServis.getPosloveById(id));
    }

    @PostMapping
    public Posao createUsluga(@RequestBody PosaoModel posaoModel){

        return  posaoServis.savePoslove(posaoModel);
    }

    @PutMapping(path = "/{id}")
    public Posao updateUsluga(@PathVariable Integer id ,@RequestBody PosaoModel posaoModel){
        return  posaoServis.updatePoslove(id,posaoModel);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosao(@PathVariable Integer id){
        posaoServis.deletePoslove(id);
    }


}
