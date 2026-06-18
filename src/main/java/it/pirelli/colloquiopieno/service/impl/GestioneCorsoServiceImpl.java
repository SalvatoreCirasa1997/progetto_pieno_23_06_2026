package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.GestioneCorsoFilterDTO;
import it.pirelli.colloquiopieno.dto.GestioneCorsoResponseDTO;
import it.pirelli.colloquiopieno.repository.GestioneCorsoRepository;
import it.pirelli.colloquiopieno.service.GestioneCorsoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GestioneCorsoServiceImpl implements GestioneCorsoService {

    private final GestioneCorsoRepository gestioneCorsoRepository;

    @Override
    public List<GestioneCorsoResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public List<GestioneCorsoResponseDTO> getByFilter(GestioneCorsoFilterDTO gestioneCorsoFilterDTO) {
        return List.of();
    }

    @Override
    public GestioneCorsoResponseDTO getById(Long gestioneCorsoId) {
        return null;
    }

    @Override
    public void delete(Long gestioneCorsoId) {

    }
}
