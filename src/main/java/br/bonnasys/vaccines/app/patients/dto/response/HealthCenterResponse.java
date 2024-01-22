package br.bonnasys.vaccines.app.patients.dto.response;

import br.bonnasys.vaccines.domain.model.Vaccine;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
public class HealthCenterResponse {
    private String id;
    private String name;
    private String address;
    private String state;
    private String neighborhood;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
