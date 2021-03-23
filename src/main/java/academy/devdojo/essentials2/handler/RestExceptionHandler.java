package academy.devdojo.essentials2.handler;

import academy.devdojo.essentials2.exception.BadRequestException;
import academy.devdojo.essentials2.exception.BadRequestExceptionDetails;
import academy.devdojo.essentials2.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timesTamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, check documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException
            (MethodArgumentNotValidException exception){
        log.info("Fields{}", exception.getBindingResult().getFieldError().getField());
        return null;
//        return new ResponseEntity<>(
//                BadRequestExceptionDetails.builder()
//                        .timesTamp(LocalDateTime.now())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .title("Bad Request Exception, check documentation")
//                        .details(exception.getMessage())
//                        .developerMessage(exception.getClass().getName())
//                        .build(), HttpStatus.BAD_REQUEST
//        );
    }
}
