package br.bonnasys.vaccines.domain.exception;

public class IllegalVaccinationException extends RuntimeException {

    public IllegalVaccinationException(String message) {
        super(message, null, true, false);
    }
}
