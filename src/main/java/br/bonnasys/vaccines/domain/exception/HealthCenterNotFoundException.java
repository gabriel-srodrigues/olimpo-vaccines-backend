package br.bonnasys.vaccines.domain.exception;

public class HealthCenterNotFoundException extends RuntimeException {

    public HealthCenterNotFoundException() {
        super("Meu deus, centro não encontrado", null, true, false);
    }
}
