package it.pirelli.colloquiopieno.specification;

import it.pirelli.colloquiopieno.entity.GestioneCorso;
import org.springframework.data.jpa.domain.Specification;

public class GestioneCorsoSpecification {

    public static Specification<GestioneCorso> hasUtenteId(Long utenteId) {
        return (root, query, cb) ->
                utenteId == null
                        ? null
                        : cb.equal(root.get("utente").get("id"), utenteId);
    }

    public static Specification<GestioneCorso> hasCorsoId(Long corsoId) {
        return (root, query, cb) ->
                corsoId == null
                        ? null
                        : cb.equal(root.get("corso").get("id"), corsoId);
    }

    public static Specification<GestioneCorso> build(Long utenteId, Long corsoId) {
        return Specification
                .where(hasUtenteId(utenteId))
                .and(hasCorsoId(corsoId));
    }
}
