package it.pirelli.colloquiopieno.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "test",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_test_email", columnNames = "email")
        },
        indexes = {
                @Index(name = "idx_test_nome", columnList = "nome"),
                @Index(name = "idx_test_email", columnList = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test implements Serializable {

    @Serial
    private static final long serialVersionUID = 1366165678239170060L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_test_id")
    @SequenceGenerator(name = "seq_test_id", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "cellulare", length = 20)
    private String cellulare;

    @Column(name = "indirizzo", length = 255)
    private String indirizzo;

    @Column(name = "citta", length = 100)
    private String citta;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    @Builder.Default
    private Boolean active = true;

    @Version
    @Column(name = "version")
    private Integer version;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (active == null) active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}