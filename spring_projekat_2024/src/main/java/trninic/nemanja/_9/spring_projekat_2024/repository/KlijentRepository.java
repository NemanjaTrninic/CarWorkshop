package trninic.nemanja._9.spring_projekat_2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trninic.nemanja._9.spring_projekat_2024.entity.Klijent;

import java.util.List;
import java.util.Optional;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent,Integer> {

    List<Klijent> findAllByObrisanoIsNull();

    //dopremanje po Id-ju
    Optional<Klijent> findByIdAndObrisanoIsNull(Integer id);


    // pravimo custom .findByUQ
    List<Klijent> findByMobilniContainsAndObrisanoIsNull(String mobilni);

    List<Klijent> findByMobilniContainsOrImeContainsOrPrezimeContainsAndObrisanoIsNull(String mobilniSearch,String imeSearch,String prezimeSearch);



}
