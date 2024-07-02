package trninic.nemanja._9.spring_projekat_2024.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trninic.nemanja._9.spring_projekat_2024.entity.Klijent;
import trninic.nemanja._9.spring_projekat_2024.entity.Posao;
import trninic.nemanja._9.spring_projekat_2024.entity.Usluga;
import trninic.nemanja._9.spring_projekat_2024.model.KlijentModel;
import trninic.nemanja._9.spring_projekat_2024.model.TogglePosao;
import trninic.nemanja._9.spring_projekat_2024.repository.KlijentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KlijentServis {

    private final KlijentRepository repository;

    private final UslugaServis uslugaServis;

    public List<Klijent> getAllKlijenti() {
        return repository.findAllByObrisanoIsNull();
    }

    //pretraga po Id-ju
    public Optional<Klijent> getKlijentById(Integer id) {
        return repository.findByIdAndObrisanoIsNull(id);
    }

    //pretraga po unique key

    public List<Klijent> getKlijentByMobilni(String mobilni) {

        return repository.findByMobilniContainsAndObrisanoIsNull(mobilni);
    }

    public Klijent createKlijent(KlijentModel model) {
        Usluga usluga = uslugaServis.getUslugaById(model.getUsluga_id()).orElseThrow();
        Klijent klijent = new Klijent();
        // zbog update-a je null, da bi pravili novog klijenta,wrapper
        klijent.setIme(model.getIme());
        klijent.setPrezime(model.getPrezime());
        klijent.setMobilni(model.getMobilni());
        klijent.setUsluga(usluga);
        klijent.setDatum_preuzimanja(LocalDateTime.now());
        return repository.save(klijent);
    }

    public Klijent updateKlijent(Integer id, KlijentModel model) {
        Klijent klijent = repository.findByIdAndObrisanoIsNull(id).orElseThrow();
        Usluga usluga = uslugaServis.getUslugaById(model.getUsluga_id()).orElseThrow();
        klijent.setIme(model.getIme());
        klijent.setPrezime(model.getPrezime());
        klijent.setMobilni(model.getMobilni());
        klijent.setUsluga(usluga);
        klijent.setAzurirano(LocalDateTime.now());
        return repository.save(klijent);
    }

    public void obrisiKlijent(Integer id) {
        Klijent klijent = repository.findByIdAndObrisanoIsNull(id).orElseThrow();
        klijent.setObrisano(LocalDateTime.now());
        repository.save(klijent);
    }

    public List<Klijent> searchByAllColumns(String inputText) {
        return repository.findByMobilniContainsOrImeContainsOrPrezimeContainsAndObrisanoIsNull(inputText, inputText, inputText);
    }

    // problematicno
    public void togglePosaoForKlijentId(Integer id, TogglePosao togglePosao) { //
        Klijent klijent = repository.findByIdAndObrisanoIsNull(id).orElseThrow();

        //List<Posao> klijentPosao = klijent.getPosao();


       /* if(togglePosao.getActive() && klijentPosao.contains(togglePosao.getPosao()))
            return;
        if(!togglePosao.getActive() && !klijentPosao.contains(togglePosao.getPosao()))
            return;
*/

//        boolean contains = klijentPosao.stream().map(Posao::getId).toList().contains(togglePosao.getPosao().getId());
//
//        if(!togglePosao.getActive() && contains){
//            klijentPosao.remove(klijentPosao.stream().filter(p->p.getId() ==togglePosao.getPosao().getId()).findFirst().get());
//            klijent.setPosao(klijentPosao);
//            repository.save(klijent);
//        }
//
//        if(togglePosao.getActive() && !contains){
//            klijentPosao.add(togglePosao.getPosao());
//            klijent.setPosao(klijentPosao);
//            repository.save(klijent);
//        }

       /* if(!togglePosao.getActive() && !contains){ // sam dodao
            klijentPosao.remove(togglePosao.getPosao());
            klijent.setPosao(klijentPosao);
            repository.save(klijent);
        }*/

    }

    /*private void saveKlijentPosaoFromPosaoId(Klijent klijent,List<Posao> poslovi){
        List<Posao> newPosao = new ArrayList<>();
        for(Integer posaoId: posaoIds){
            Posao posao = new Posao();
            posao.setId(posaoId);
            newPosao.add(posao);
        }
        klijent.setPosao(poslovi);//
        repository.save(klijent);

    }
*/
}
