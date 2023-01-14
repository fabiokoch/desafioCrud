package com.br.fabio.desafioCrud.exception;

import com.br.fabio.desafioCrud.constants.Errors;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    public ExceptionResponse(final Errors errorCode) {
        this.message = errorCode.getMessage();

    }

}
