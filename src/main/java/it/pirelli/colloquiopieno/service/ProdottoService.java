package it.pirelli.colloquiopieno.service;

import it.pirelli.colloquiopieno.dto.ProdottoRequestDTO;
import it.pirelli.colloquiopieno.dto.ProdottoResponseDTO;

import java.util.List;

public interface ProdottoService {
    
    List<ProdottoResponseDTO> getAll();

    ProdottoResponseDTO getById(Long id);

    ProdottoResponseDTO insert(ProdottoRequestDTO prodottoRequestDTO);

    ProdottoResponseDTO update(Long id, ProdottoRequestDTO prodottoRequestDTO);

    void delete(Long id);
}
