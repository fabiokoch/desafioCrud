package com.br.fabio.desafioCrud.constants;

public enum Errors {
    VEHICLE_NOT_FOUND("Vehicle not found");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
