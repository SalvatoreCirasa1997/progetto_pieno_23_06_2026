package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.UtenteDTO;
import it.pirelli.colloquiopieno.dto.UtenteFilterDTO;
import it.pirelli.colloquiopieno.dto.UtenteResponseDTO;
import it.pirelli.colloquiopieno.repository.UtenteRepository;
import it.pirelli.colloquiopieno.service.UtenteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;

    @Override
    public List<UtenteResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public List<UtenteResponseDTO> getById(Long utenteId) {
        return List.of();
    }

    @Override
    public List<UtenteResponseDTO> getByFilter(UtenteFilterDTO utenteFilterDTO) {
        return List.of();
    }

    @Override
    public UtenteResponseDTO insert(UtenteDTO utenteDTO) {
        return null;
    }

    @Override
    public UtenteResponseDTO update(UtenteDTO utenteDTO, Long utenteId) {
        return null;
    }

    @Override
    public void delete(Long utenteId) {

    }
}
