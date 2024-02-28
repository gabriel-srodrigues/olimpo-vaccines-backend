package br.bonnasys.vaccines.support.builder;

import br.bonnasys.vaccines.domain.model.Patient;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public final class PatientBuilder {
    private String name;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String id;

    public static PatientBuilder any() {
        PatientBuilder patientBuilder = new PatientBuilder();
        patientBuilder.name = "Jay Pritcher";
        patientBuilder.email = "jay@moveis.com.br";
        patientBuilder.phone = "(99) 99999-9999";
        patientBuilder.birthdate = LocalDate.now().minusYears(21);
        patientBuilder.createdAt = OffsetDateTime.now();
        patientBuilder.updatedAt = OffsetDateTime.now();
        return patientBuilder;
    }

    public PatientBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PatientBuilder withId() {
        this.id = UUID.randomUUID().toString();
        return this;
    }

    public PatientBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public Patient build() {
        return new Patient(this.id, this.name, this.phone, this.email, this.birthdate, this.createdAt, this.updatedAt);
    }
}
