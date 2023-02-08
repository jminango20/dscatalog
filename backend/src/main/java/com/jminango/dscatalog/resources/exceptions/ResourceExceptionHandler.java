package com.jminango.dscatalog.resources.exceptions;

import com.jminango.dscatalog.servicies.exceptions.DataBaseExceptions;
import com.jminango.dscatalog.servicies.exceptions.ResourceNotFoundExceptions;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundExceptions.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundExceptions e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Resource not Found");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(value = DataBaseExceptions.class)
    public ResponseEntity<StandardError> dataBaseException(DataBaseExceptions e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Data Base Exception");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}