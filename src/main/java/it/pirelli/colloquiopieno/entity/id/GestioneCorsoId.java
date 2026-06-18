package it.pirelli.colloquiopieno.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GestioneCorsoId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4462315870334541661L;

    @Column(name = "id_corso")
    private Long idCorso;

    @Column(name = "id_utente")
    private Long idUtente;
}
