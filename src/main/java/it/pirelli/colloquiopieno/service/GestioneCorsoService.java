package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.GestioneCorsoFilterDTO;
import it.pirelli.colloquiopieno.dto.GestioneCorsoResponseDTO;

import java.util.List;

public interface GestioneCorsoService {
    List<GestioneCorsoResponseDTO> getAll();

    List<GestioneCorsoResponseDTO> getByFilter(GestioneCorsoFilterDTO gestioneCorsoFilterDTO);

    GestioneCorsoResponseDTO getById(Long gestioneCorsoId);

    void delete(Long gestioneCorsoId);
}
