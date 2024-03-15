package br.bonnasys.vaccines.domain.exception;

public class VaccineNotFoundException extends RuntimeException {

    public VaccineNotFoundException() {
        super("Vacina não encontrada", null, true, false);
    }
}
