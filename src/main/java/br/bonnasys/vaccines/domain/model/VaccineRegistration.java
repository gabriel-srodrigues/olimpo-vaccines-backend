package br.bonnasys.vaccines.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "vaccine_registration")
public class VaccineRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "fk_vr_patient_id"))
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", foreignKey = @ForeignKey(name = "fk_vr_vaccine_id"))
    private Vaccine vaccine;

    @ManyToOne
    @JoinColumn(name = "health_center_id", foreignKey = @ForeignKey(name = "fk_vr_health_center_id"))
    private HealthCenter healthCenter;

    private OffsetDateTime registrationDate;

    public static VaccineRegistration from(Patient patient, Vaccine vaccine, HealthCenter healthCenter) {
        VaccineRegistration registration = new VaccineRegistration();
        registration.setPatient(patient);
        registration.setVaccine(vaccine);
        registration.setHealthCenter(healthCenter);
        registration.setRegistrationDate(OffsetDateTime.now());
        return registration;
    }
}
