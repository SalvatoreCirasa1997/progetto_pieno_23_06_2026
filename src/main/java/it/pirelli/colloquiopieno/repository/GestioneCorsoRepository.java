package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.GestioneCorso;
import it.pirelli.colloquiopieno.entity.id.GestioneCorsoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface GestioneCorsoRepository extends JpaRepository<GestioneCorso, GestioneCorsoId>, JpaSpecificationExecutor <GestioneCorso>{
    boolean existsByUtenteIdAndCorsoId(Long id, Long id1);

    @Query("SELECT gc.corso.id FROM GestioneCorso gc WHERE gc.utente.id = :utenteId")
    List<Long> findCorsoIdsByUtenteId(@Param("utenteId") Long utenteId);

    @Query("""
        SELECT gc.corso.id, COUNT(gc)
        FROM GestioneCorso gc
        WHERE gc.corso.id IN :corsoIds
        GROUP BY gc.corso.id
    """)
    List<Object[]> countSubscriptionsByCorsoIds(@Param("corsoIds") List<Long> corsoIds);

    default Map<Long, Integer> countSubscriptionsByCorsoIdsAsMap(List<Long> corsoIds) {
        List<Object[]> results = countSubscriptionsByCorsoIds(corsoIds);
        return results.stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> ((Number) row[1]).intValue()
                ));
    }
}
