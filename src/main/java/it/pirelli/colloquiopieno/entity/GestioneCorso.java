package it.pirelli.colloquiopieno.entity;

import it.pirelli.colloquiopieno.entity.id.GestioneCorsoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gestione_corsi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GestioneCorso {

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
}