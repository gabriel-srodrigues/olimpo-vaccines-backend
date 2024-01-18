package br.bonnasys.vaccines.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String producer;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
