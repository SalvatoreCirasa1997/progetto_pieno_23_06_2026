package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.UtenteRequestDTO;
import it.pirelli.colloquiopieno.dto.UtenteFilterDTO;
import it.pirelli.colloquiopieno.dto.UtenteResponseDTO;

import java.util.List;

public interface UtenteService {
    List<UtenteResponseDTO> getAll();

    UtenteResponseDTO getById(Long utenteId);

    List<UtenteResponseDTO> getByFilter(UtenteFilterDTO utenteFilterDTO);

    UtenteResponseDTO insert(UtenteRequestDTO utenteRequestDTO);

    UtenteResponseDTO update(UtenteRequestDTO utenteRequestDTO, Long utenteId);

    void delete(Long utenteId);
}
