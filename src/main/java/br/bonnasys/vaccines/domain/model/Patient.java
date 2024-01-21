package br.bonnasys.vaccines.domain.model;

import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientUseCase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patients")
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private OffsetDateTime createdAt; //yyyy-MM-ddTHH:mm:ss-Z 2024-01-11T20:59:00-03:00
    private OffsetDateTime updatedAt;
    @OneToMany
    @JsonIgnore
    private List<VaccineRegistration> history;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }

    public Patient(String name, String phone, String email, LocalDate birthdate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Patient(String id,
                   String name,
                   String phone,
                   String email,
                   LocalDate birthdate,
                   OffsetDateTime createdAt,
                   OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
