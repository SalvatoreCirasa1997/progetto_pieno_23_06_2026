package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.UtenteDTO;
import it.pirelli.colloquiopieno.dto.UtenteFilterDTO;
import it.pirelli.colloquiopieno.dto.UtenteResponseDTO;

import java.util.List;

public interface UtenteService {
    List<UtenteResponseDTO> getAll();

    List<UtenteResponseDTO> getById(Long utenteId);

    List<UtenteResponseDTO> getByFilter(UtenteFilterDTO utenteFilterDTO);

    UtenteResponseDTO insert(UtenteDTO utenteDTO);

    UtenteResponseDTO update(UtenteDTO utenteDTO, Long utenteId);

    void delete(Long utenteId);
}
