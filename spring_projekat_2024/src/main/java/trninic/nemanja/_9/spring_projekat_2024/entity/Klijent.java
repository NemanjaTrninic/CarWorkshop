package trninic.nemanja._9.spring_projekat_2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "klijent")
@NoArgsConstructor
@Getter
@Setter
public class Klijent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "klijent_id")
    private Integer id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false, unique = true)
    private String mobilni;

    @OneToOne(optional = false)//
    @JoinColumn(name = "usluga_id",nullable = false)
    private Usluga usluga;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "klijent_posao",
//            joinColumns = @JoinColumn(name = "klijent_id"),
//            inverseJoinColumns = @JoinColumn(name = "posao_id")
//    )
//    List<Posao> posao;

    @Column(nullable = false)
    private LocalDateTime datum_preuzimanja = LocalDateTime.now();

    private LocalDateTime azurirano;

    @JsonIgnore
    private LocalDateTime obrisano;
}
