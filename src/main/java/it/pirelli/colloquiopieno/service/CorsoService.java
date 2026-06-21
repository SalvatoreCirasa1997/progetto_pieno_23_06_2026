package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.CorsoRequestDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;

import java.util.List;

public interface CorsoService {
    List<CorsoResponseDTO> getAll();

    CorsoResponseDTO getById(Long corsoId);

    CorsoResponseDTO insert(CorsoRequestDTO corsoRequestDTO);

    CorsoResponseDTO update(CorsoRequestDTO corsoRequestDTO, Long corsoId);

    void delete(Long corsoId);
}
