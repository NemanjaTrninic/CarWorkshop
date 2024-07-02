package trninic.nemanja._9.spring_projekat_2024.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExceptionModel {

    private  String name;
    private String path;
    private String message;
}
