package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {

    boolean existsByCf(String cf);

    boolean existsByEmail(String email);

    boolean existsByCfAndIdNot(String cf, Long utenteId);

    boolean existsByEmailAndIdNot(String email, Long utenteId);
}
