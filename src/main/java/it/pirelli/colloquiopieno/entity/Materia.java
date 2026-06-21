package it.pirelli.colloquiopieno.entity;

import it.pirelli.colloquiopieno.enums.TipoDurataEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "materie",
        uniqueConstraints = @UniqueConstraint(name = "uk_materia_nome", columnNames = "nome"),
        indexes = @Index(name = "idx_materia_nome", columnList = "nome"))
public class Materia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "materia_id_seq", name = "seq_materia_id", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descrizione", length = 250)
    private String descrizione;

    @Column(name = "durata", nullable = false)
    private Integer durata;

    @Column(name = "tipo_durata", length = 50, nullable = false)
    private TipoDurataEnum tipoDurata;

    @Column(name = "cfu")
    private Integer cfu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Corso> corsi =  new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Esame> esami = new ArrayList<>();
}
