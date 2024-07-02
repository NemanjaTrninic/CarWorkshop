package trninic.nemanja._9.spring_projekat_2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trninic.nemanja._9.spring_projekat_2024.entity.Klijent;
import trninic.nemanja._9.spring_projekat_2024.model.KlijentModel;
import trninic.nemanja._9.spring_projekat_2024.model.TogglePosao;
import trninic.nemanja._9.spring_projekat_2024.service.KlijentServis;

import java.util.List;

@RestController
@RequestMapping(path = "/api/klijent")
@RequiredArgsConstructor
//prvo povezivanje na html jer cross-origin nije dozvoljen
@CrossOrigin
public class KlijentController {

    private final KlijentServis servis;

    @GetMapping()
    public List<Klijent> getAllKlijenti() {

        return servis.getAllKlijenti();
    }

    //pretraga po Id-u
    @GetMapping(path = "/{id}")
    public ResponseEntity<Klijent> getKlijentById(@PathVariable Integer id) {
        return ResponseEntity.of(servis.getKlijentById(id));
    }

    //pretraga po UQ-u
    @GetMapping(path = "/mobilni/{mobilni}")
    public List<Klijent> getKlijentByMobilni(@PathVariable String mobilni) {
        return servis.getKlijentByMobilni(mobilni);
    }

    //post request, da sascuvamo nov podatak
    @PostMapping
    public Klijent createKlijent(@RequestBody KlijentModel klijent){

        return servis.createKlijent(klijent);
    }

    @PutMapping(path = "/{id}")
    public Klijent updateKlijent(@PathVariable Integer id,@RequestBody KlijentModel klijent){
        return servis.updateKlijent(id,klijent);
    }

    @PutMapping(path = "/posao/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void togglePosao(@PathVariable Integer id, @RequestBody TogglePosao model){ //
        servis.togglePosaoForKlijentId(id,model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void obrisiKlijent(@PathVariable Integer id){
        servis.obrisiKlijent(id);
    }

    @GetMapping(path="/search/{inputText}")
    public List<Klijent> searchClientsByAllColumns(@PathVariable String inputText) {
        return servis.searchByAllColumns(inputText);
    }


}
