package com.br.fabio.desafioCrud.exception.handler;


import com.br.fabio.desafioCrud.constants.Errors;
import com.br.fabio.desafioCrud.exception.ExceptionResponse;
import com.br.fabio.desafioCrud.exception.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(VehicleNotFoundException.class)
    public final ResponseEntity<Object> handleVehicleNotFound(final VehicleNotFoundException ex) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse(Errors.VEHICLE_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }


}