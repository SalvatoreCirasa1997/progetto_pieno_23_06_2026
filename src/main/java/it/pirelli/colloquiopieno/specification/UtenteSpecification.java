package it.pirelli.colloquiopieno.specification;

import it.pirelli.colloquiopieno.dto.UtenteFilterDTO;
import it.pirelli.colloquiopieno.entity.Utente;
import org.springframework.data.jpa.domain.Specification;

public class UtenteSpecification {

    public static Specification<Utente> hasNome(String nome) {
        return (root, query, cb) ->
                nome == null || nome.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Utente> hasCognome(String cognome) {
        return (root, query, cb) ->
                cognome == null || cognome.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("cognome")), "%" + cognome.toLowerCase() + "%");
    }

    public static Specification<Utente> hasEmail(String email) {
        return (root, query, cb) ->
                email == null || email.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Utente> hasIndirizzo(String indirizzo) {
        return (root, query, cb) ->
                indirizzo == null || indirizzo.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("indirizzo")), "%" + indirizzo.toLowerCase() + "%");
    }

    public static Specification<Utente> hasCf(String cf) {
        return (root, query, cb) ->
                cf == null || cf.isBlank()
                        ? null
                        : cb.equal(root.get("cf"), cf);
    }

    public static Specification<Utente> build(UtenteFilterDTO filter) {
        return Specification
                .where(hasNome(filter.getNome()))
                .and(hasCognome(filter.getCognome()))
                .and(hasEmail(filter.getEmail()))
                .and(hasIndirizzo(filter.getIndirizzo()))
                .and(hasCf(filter.getCf()));
    }
}