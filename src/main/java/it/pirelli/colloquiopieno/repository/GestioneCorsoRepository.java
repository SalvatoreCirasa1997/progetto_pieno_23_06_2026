package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.GestioneCorso;
import it.pirelli.colloquiopieno.entity.id.GestioneCorsoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GestioneCorsoRepository extends JpaRepository<GestioneCorso, GestioneCorsoId>, JpaSpecificationExecutor <GestioneCorso>{
}
