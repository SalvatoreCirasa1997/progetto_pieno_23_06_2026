package it.pirelli.colloquiopieno.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aule",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_aula_nome", columnNames = "nome")
    },
    indexes = {
        @Index(name = "idx_aula_nome", columnList = "nome")
})
public class Aula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "aula_id_seq", name = "aula_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "capacita", nullable = false)
    private Integer capacita;

    @Column(name = "piano")
    private Integer piano;

    @Column(name = "indirizzo", nullable = false, length = 100)
    private String indirizzo;

    @Column(name = "has_proiettore")
    private Boolean hasProiettore;

    @Column(name = "has_lim")
    private Boolean hasLim;

    //OneToMany -> un'aula può ospitare piu corsi
    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Corso> corsi = new ArrayList<>();
}
