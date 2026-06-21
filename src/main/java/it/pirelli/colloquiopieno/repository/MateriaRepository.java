package it.pirelli.colloquiopieno.repository;

import it.pirelli.colloquiopieno.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Long idMateria);
}
