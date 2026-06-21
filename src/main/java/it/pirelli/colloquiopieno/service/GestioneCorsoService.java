package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.GestioneCorsoFilterDTO;
import it.pirelli.colloquiopieno.dto.GestioneCorsoResponseDTO;

import java.util.List;

public interface GestioneCorsoService {
    List<GestioneCorsoResponseDTO> getAll();

    List<GestioneCorsoResponseDTO> getByFilter(GestioneCorsoFilterDTO gestioneCorsoFilterDTO);

    void delete(Long utenteId, Long corsoId);

    List<GestioneCorsoResponseDTO> subscribe(Long utenteId, List<Long> corsiList);
}
