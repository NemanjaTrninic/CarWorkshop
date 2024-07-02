package trninic.nemanja._9.spring_projekat_2024.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trninic.nemanja._9.spring_projekat_2024.entity.Usluga;
import trninic.nemanja._9.spring_projekat_2024.model.UslugaModel;
import trninic.nemanja._9.spring_projekat_2024.repository.UslugaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UslugaServis {

    private final UslugaRepository repository;

    public List<Usluga> getAllUsluge(){
        return repository.findAllByObrisanoIsNull();
    }

    public Optional<Usluga> getUslugaById(Integer id){
        return repository.findByIdAndObrisanoIsNull(id); //
    }

    public Usluga saveUsluga(UslugaModel uslugaModel){
        Usluga usluga = new Usluga();
        usluga.setVrsta_usluge(uslugaModel.getVrsta_usluge());
        usluga.setCena(uslugaModel.getCena());
        usluga.setDatum_preuzimanja(LocalDateTime.now());
        return repository.save(usluga);
    }

    public Usluga updateUsluga(Integer id,UslugaModel uslugaModel){
        Usluga usluga = repository.findByIdAndObrisanoIsNull(id).orElseThrow();
        usluga.setVrsta_usluge(uslugaModel.getVrsta_usluge());
        usluga.setAzurirano(LocalDateTime.now());
        return repository.save(usluga);
    }

    public void  deleteUsluga(Integer id){
        Usluga usluga = repository.findByIdAndObrisanoIsNull(id).orElseThrow();

        usluga.setObrisano(LocalDateTime.now());
         repository.save(usluga);
    }
}
