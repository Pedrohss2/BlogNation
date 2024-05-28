package com.BlogNation.br.controller.exception;

import com.BlogNation.br.dto.error.CustomError;
import com.BlogNation.br.dto.error.ValidationError;
import com.BlogNation.br.service.exception.DatabaseException;
import com.BlogNation.br.service.exception.ForbiddenException;
import com.BlogNation.br.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Duration;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Duration duration = Duration.ofHours(3L);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now().minus(duration), status.value(), e.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> databaseException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now().minus(duration), status.value(), e.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError>  methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now().minus(duration), status.value(), "invalid data");

        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            validationError.addError(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomError> forbiddenException(ForbiddenException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now().minus(duration), status.value(), e.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

}