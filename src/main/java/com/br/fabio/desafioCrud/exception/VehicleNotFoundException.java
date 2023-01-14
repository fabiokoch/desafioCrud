package com.br.fabio.desafioCrud.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException() {
    }

    public VehicleNotFoundException(String msg) {
        super(msg);
    }
}
