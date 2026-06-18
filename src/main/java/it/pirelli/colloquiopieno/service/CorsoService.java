package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.CorsoDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;

import java.util.List;

public interface CorsoService {
    List<CorsoResponseDTO> getAll();

    CorsoResponseDTO getById(Long corsoId);

    CorsoResponseDTO insert(CorsoDTO corsoDTO);

    CorsoResponseDTO update(CorsoDTO corsoDTO, Long corsoId);

    void delete(Long corsoId);
}
