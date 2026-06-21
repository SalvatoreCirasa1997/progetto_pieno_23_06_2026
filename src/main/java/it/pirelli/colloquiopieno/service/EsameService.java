package it.pirelli.colloquiopieno.service;

import it.pirelli.colloquiopieno.dto.EsameRequestDTO;
import it.pirelli.colloquiopieno.dto.EsameResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface EsameService {
    List<EsameResponseDTO> getAll();

    EsameResponseDTO getById(Long id);

    EsameResponseDTO insert(EsameRequestDTO esameRequestDTO);

    EsameResponseDTO update(Long idEsame, EsameRequestDTO esameRequestDTO);

    void delete(Long idEsame);
}
