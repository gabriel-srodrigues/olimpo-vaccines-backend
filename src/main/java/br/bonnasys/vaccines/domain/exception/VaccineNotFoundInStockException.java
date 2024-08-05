package br.bonnasys.vaccines.domain.exception;

public class VaccineNotFoundInStockException extends RuntimeException {
    private static final String MESSAGE = "Vacina: [%s] não está disponivel no centro de saúde: [%s]";

    public VaccineNotFoundInStockException(String healthCenterId, String vaccineId) {
        super(MESSAGE.formatted(vaccineId, healthCenterId), null, true,  false);
    }
}
