package com.ProdutosECompras.Project.controller.exceptions;

import com.ProdutosECompras.Project.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExeptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErro> ResourceNotFound(ResourceNotFoundException a, HttpServletRequest request) {

        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; // Fornece o código de status
        StandardErro err = new StandardErro(Instant.now(), status.value(), error, a.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
