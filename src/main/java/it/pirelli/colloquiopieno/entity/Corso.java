package it.pirelli.colloquiopieno.entity;

import it.pirelli.colloquiopieno.enums.StatoCorsoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "corsi",
        uniqueConstraints = {
            @UniqueConstraint(name = "uk_corso_titolo", columnNames = "titolo")
        },
        indexes = {
            @Index(name = "idx_corso_titolo", columnList = "titolo")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Corso implements Serializable {

    @Serial
    private static final long serialVersionUID = -672538307770379690L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "corso_id_seq", sequenceName = "corso_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "titolo", nullable = false, length = 100)
    private String titolo;

    @Column(name = "descrizione", length = 250)
    private String descrizione;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoCorsoEnum stato;

    @Column(name = "numero_massimo_partecipanti")
    private Integer numeroMassimoPartecipanti;


    //ManyToOne -> Il corso può trovarsi in una sola aula
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aula", nullable = false)
    private Aula aula;

    //ManyToOne -> Il corso può avere una sola materia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;
}
