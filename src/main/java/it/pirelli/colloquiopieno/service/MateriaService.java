package it.pirelli.colloquiopieno.service;

import it.pirelli.colloquiopieno.dto.MateriaRequestDTO;
import it.pirelli.colloquiopieno.dto.MateriaResponseDTO;

import java.util.List;

public interface MateriaService {
    List<MateriaResponseDTO> getAll();

    MateriaResponseDTO getById(Long id);

    MateriaResponseDTO insert(MateriaRequestDTO materiaRequestDTO);

    MateriaResponseDTO update(Long idMateria, MateriaRequestDTO materiaRequestDTO);

    void delete(Long idMateria);
}
