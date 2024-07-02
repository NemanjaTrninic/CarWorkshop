package trninic.nemanja._9.spring_projekat_2024.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionModel handleError(Exception e, HttpStatus request){
        ExceptionModel model = new ExceptionModel();
        model.setName(e.getClass().getSimpleName());
        model.setMessage(e.getMessage());

        return model;
    }
}
