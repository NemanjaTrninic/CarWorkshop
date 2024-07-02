package trninic.nemanja._9.spring_projekat_2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "usluga")
public class Usluga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usluga_id")
    private Integer id;

    @Column(unique = true,nullable = false)
    private String vrsta_usluge;

    @Column(nullable = false)
    private String cena; //

    @Column(nullable = false)
    private LocalDateTime datum_preuzimanja;

    private LocalDateTime azurirano;

    @JsonIgnore
    private LocalDateTime obrisano;

}
