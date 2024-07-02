package trninic.nemanja._9.spring_projekat_2024.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trninic.nemanja._9.spring_projekat_2024.entity.Posao;

@NoArgsConstructor
@Getter
@Setter
public class TogglePosao {
    private Boolean active;
    private Posao posao;
}
