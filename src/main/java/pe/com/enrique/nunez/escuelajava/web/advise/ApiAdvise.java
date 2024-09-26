package pe.com.enrique.nunez.escuelajava.web.advise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.com.enrique.nunez.escuelajava.exception.ApiException;
import pe.com.enrique.nunez.escuelajava.web.util.ResponseMessage;

@RestControllerAdvice
public class ApiAdvise {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseMessage> findApiAdvise(ApiException apiException) {

        ResponseMessage msg = ResponseMessage
                .builder().code(apiException.getCode()).message(apiException.getMessage())
                .build();

        return new ResponseEntity<>(msg, apiException.getHttpStatus());

    }
}
