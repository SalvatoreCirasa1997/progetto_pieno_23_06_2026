package it.pirelli.colloquiopieno.service;

import it.pirelli.colloquiopieno.dto.AulaRequestDTO;
import it.pirelli.colloquiopieno.dto.AulaResponseDTO;

import java.util.List;

public interface AulaService {
    List<AulaResponseDTO> getAll();

    AulaResponseDTO getById(Long id);

    AulaResponseDTO insert(AulaRequestDTO aulaRequestDTO);

    AulaResponseDTO update(Long idAula, AulaRequestDTO aulaRequestDTO);

    void delete(Long idAula);
}
