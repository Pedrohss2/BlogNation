package com.BlogNation.br.dto.error;

import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.removeIf(x -> x.getFieldName().equals(fieldName));

        errors.add(new FieldMessage(fieldName, message));
    }

}
