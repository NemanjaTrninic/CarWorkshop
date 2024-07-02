package trninic.nemanja._9.spring_projekat_2024.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class KlijentModel {

    private String ime;
    private String prezime;
    private String mobilni;
    private Integer usluga_id;
}
