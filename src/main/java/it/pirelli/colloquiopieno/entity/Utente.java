package it.pirelli.colloquiopieno.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "utenti",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_utente_cf", columnNames = "cf")
        },
        indexes = {
                @Index(name = "idx_utente_cf", columnList = "cf")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utente implements Serializable {

    @Serial
    private static final long serialVersionUID = 2657741697472332466L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "indirizzo", length = 100)
    private String indirizzo;

    @Column(name = "cf", nullable = false, length = 16, unique = true)
    private String cf;
}
