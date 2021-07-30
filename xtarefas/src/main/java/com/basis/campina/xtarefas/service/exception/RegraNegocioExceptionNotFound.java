package com.basis.campina.xtarefas.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegraNegocioExceptionNotFound extends RuntimeException {

    public RegraNegocioExceptionNotFound(final String message) {
        this(message, null);
    }

    public RegraNegocioExceptionNotFound(final String message, final Throwable cause) {
        super(message, cause);
    }


}
