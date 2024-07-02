package trninic.nemanja._9.spring_projekat_2024.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trninic.nemanja._9.spring_projekat_2024.entity.Posao;
import trninic.nemanja._9.spring_projekat_2024.entity.Usluga;
import trninic.nemanja._9.spring_projekat_2024.model.PosaoModel;
import trninic.nemanja._9.spring_projekat_2024.model.UslugaModel;
import trninic.nemanja._9.spring_projekat_2024.repository.PosaoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PosaoServis {

    private final PosaoRepository repository;

    public List<Posao> getAllPoslove(){

        return repository.findAllByObrisanoIsNull();
    }

    public Optional<Posao> getPosloveById(Integer id){
        return repository.findByIdAndObrisanoIsNull(id); //
    }

    public Posao savePoslove(PosaoModel posaoModel){
        Posao posao = new Posao();
        posao.setIme(posaoModel.getIme());
        posao.setDatum_preuzimanja(LocalDateTime.now());
        return repository.save(posao);
    }

    public Posao updatePoslove(Integer id,PosaoModel posaoModel){
        Posao posao= repository.findByIdAndObrisanoIsNull(id).orElseThrow();
        posao.setIme(posaoModel.getIme());
        posao.setAzurirano(LocalDateTime.now());
        return repository.save(posao);
    }

    public void  deletePoslove(Integer id){
        Posao posao = repository.findByIdAndObrisanoIsNull(id).orElseThrow();

        posao.setObrisano(LocalDateTime.now());
        repository.save(posao);
    }
}
