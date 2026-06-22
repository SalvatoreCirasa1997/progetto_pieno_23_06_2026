package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsameRepository extends JpaRepository<Esame, Long> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Long id);
}
