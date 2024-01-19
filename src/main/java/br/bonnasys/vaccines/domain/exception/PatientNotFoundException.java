package br.bonnasys.vaccines.domain.exception;

import org.springframework.context.MessageSource;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException() {
        super("Patient not found", null, true, false);
    }
}
