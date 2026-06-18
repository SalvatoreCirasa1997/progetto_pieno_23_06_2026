package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso,Long> {
}
