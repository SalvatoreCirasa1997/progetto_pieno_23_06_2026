package it.pirelli.colloquiopieno.entity;

import it.pirelli.colloquiopieno.entity.id.GestioneCorsoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "gestione_corsi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GestioneCorso implements Serializable {

    @EmbeddedId
    private GestioneCorsoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCorso")
    @JoinColumn(name = "id_corso")
    private Corso corso;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUtente")
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @Column(name = "data_iscrizione", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataIscrizione;
}