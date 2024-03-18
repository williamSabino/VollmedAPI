package com.medico.vollMed.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404(EntityNotFoundException ex){
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(tratarErros400::new));
    }

    @ExceptionHandler(ValidarException.class)
    public ResponseEntity tratarValidacoesConsulta(ValidarException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record tratarErros400(String erro, String descricao){
        tratarErros400(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
