package trninic.nemanja._9.spring_projekat_2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trninic.nemanja._9.spring_projekat_2024.entity.Posao;
import trninic.nemanja._9.spring_projekat_2024.entity.Usluga;

import java.util.List;
import java.util.Optional;

@Repository

public interface PosaoRepository  extends JpaRepository<Posao,Integer> {

    List<Posao> findAllByObrisanoIsNull();


    Optional<Posao> findByIdAndObrisanoIsNull(Integer id);

}
