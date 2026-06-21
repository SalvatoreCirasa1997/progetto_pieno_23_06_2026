package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Corso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso,Long> {
    boolean existsByTitolo(String titolo);

    boolean existsByTitoloAndIdNot(@NotBlank(message = "Il titolo non può essere vuoto") @Size(min = 2, max = 100, message = "Il titolo deve essere tra 2 e 100 caratteri") String titolo, Long corsoId);
}
