package express.az.tms.handler;

import express.az.tms.exception.ApplicationException;
import express.az.tms.model.dto.response.ExceptionResponse;
import express.az.tms.model.enums.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.constraints.NotNull;


@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handlerApplicationException(@NotNull ApplicationException exception) {
        Exceptions exceptions = exception.getExceptions();

        return new ResponseEntity<>(exceptions.toString(), exceptions.getStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> handlerValidationException(@NotNull BindException exception) {
        FieldError fieldError = exception.getFieldError();

        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation error occurred!";

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.of(errorMessage, HttpStatus.BAD_REQUEST));

    }

}
