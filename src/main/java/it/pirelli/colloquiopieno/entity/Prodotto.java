package it.pirelli.colloquiopieno.entity;

import it.pirelli.colloquiopieno.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "prodotto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prodotto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prodotto_id")
    @SequenceGenerator(name = "seq_prodotto_id", sequenceName = "prodotto_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @Column(name = "prezzo", nullable = false)
    private Double prezzo;

    @Column(name = "disponibilita", nullable = false)
    private Integer disponibilita;

    @Column(name = "descrizione", length = 100)
    private String descrizione;

    @CreationTimestamp
    @Column(name = "data_creazione", nullable = false, updatable = false)
    private LocalDateTime dataCreazione;

    @UpdateTimestamp
    @Column(name = "data_ultima_modifica")
    private LocalDateTime dataUltimaModifica;
}
