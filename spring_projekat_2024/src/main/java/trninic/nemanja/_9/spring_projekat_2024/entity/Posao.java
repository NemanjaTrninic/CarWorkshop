package trninic.nemanja._9.spring_projekat_2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "posao")
public class Posao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posao_id")
    private Integer id;

    @Column(unique = true,nullable = false)
    private String ime;

//    @ManyToMany(mappedBy = "posao")
//    @JsonIgnore
//    private List<Klijent> klijent;

    @Column(nullable = false)
    private LocalDateTime datum_preuzimanja;

    private LocalDateTime azurirano;

    @JsonIgnore
    private LocalDateTime obrisano;

}
