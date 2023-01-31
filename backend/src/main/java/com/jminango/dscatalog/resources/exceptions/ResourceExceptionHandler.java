package com.jminango.dscatalog.resources.exceptions;

import com.jminango.dscatalog.servicies.exceptions.EntityNotFoundExceptions;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundExceptions.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundExceptions e, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setError("Resource not Found");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}