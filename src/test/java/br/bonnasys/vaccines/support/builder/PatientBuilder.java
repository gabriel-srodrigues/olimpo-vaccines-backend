package br.bonnasys.vaccines.support.builder;

import br.bonnasys.vaccines.domain.model.Patient;

import java.time.LocalDate;

public final class PatientBuilder {
    private String name;
    private String phone;
    private String email;
    private LocalDate birthdate;

    public static PatientBuilder any() {
        PatientBuilder patientBuilder = new PatientBuilder();
        patientBuilder.name = "Jay Pritcher";
        patientBuilder.email = "jay@moveis.com.br";
        patientBuilder.phone = "(99) 99999-9999";
        patientBuilder.birthdate = LocalDate.now().minusYears(21);
        return patientBuilder;
    }

    public PatientBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Patient build() {
        return new Patient(this.name, this.phone, this.email, this.birthdate);
    }
}
