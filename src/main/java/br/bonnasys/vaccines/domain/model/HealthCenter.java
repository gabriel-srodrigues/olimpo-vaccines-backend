package br.bonnasys.vaccines.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "health_center")
public class HealthCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    //uuid -> 36 caracteres sendo 32[0-9,a-f] e 4 "-" f6de9739-05e2-4eb3-b401-49ccdfe26b2a
    private String id;
    private String name;
    private String address;
    private String state;
    private String neighborhood;

    @ElementCollection
    @CollectionTable(name = "vaccine_stock",
            joinColumns = @JoinColumn(name = "health_center_id", foreignKey = @ForeignKey(name = "health_center_hc_id")))
    @MapKeyJoinColumn(name = "vaccine_id", foreignKey = @ForeignKey(name = "health_center_vaccine_id"))
    @Column(name = "amount")
    private Map<Vaccine, Integer> stock = new HashMap<>();

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @PrePersist
    public void beforeCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.setCreatedAt(now);
        this.setUpdatedAt(now);
    }
}
