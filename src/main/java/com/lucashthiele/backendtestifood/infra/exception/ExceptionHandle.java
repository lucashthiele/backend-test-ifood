package com.lucashthiele.backendtestifood.infra.exception;

import com.lucashthiele.backendtestifood.exceptions.CategoryNotFoundException;
import com.lucashthiele.backendtestifood.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ValidationErrorDTO> handleProductNotFound(ProductNotFoundException err){
        var error = new ValidationErrorDTO(err.getMessage());

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ValidationErrorDTO> handleCategoryNotFound(CategoryNotFoundException err){
        var error = new ValidationErrorDTO(err.getMessage());

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationErrorDTO> handleGenericError(Exception err){
        var error = new ValidationErrorDTO(err.getMessage());

        return ResponseEntity.status(500).body(error);
    }

}
