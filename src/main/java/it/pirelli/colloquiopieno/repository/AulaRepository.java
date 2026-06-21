package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Aula;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(@NotBlank(message = "Il nome dell'aula non può essere vuoto") @Size(max = 50, message = "Il nome dell'aula non può superare i 50 caratteri") String nome, Long idAula);
}
