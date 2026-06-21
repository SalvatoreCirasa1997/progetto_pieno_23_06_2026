package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso,Long> {
    boolean existsByTitolo(String titolo);

    boolean existsByTitoloAndIdNot(String titolo, Long corsoId);

    @Query("SELECT c.id, c.numeroMassimoPartecipanti FROM Corso c WHERE c.id IN :corsoIds")
    List<Object[]> findMaxParticipantsByCorsoIds(@Param("corsoIds") List<Long> corsoIds);
}
