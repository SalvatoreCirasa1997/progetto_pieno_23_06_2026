package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.CorsoDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.CorsoMapper;
import it.pirelli.colloquiopieno.repository.CorsoRepository;
import it.pirelli.colloquiopieno.service.CorsoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorsoServiceImpl implements CorsoService {

    private final CorsoRepository corsoRepository;
    private final CorsoMapper corsoMapper;

    @Override
    public List<CorsoResponseDTO> getAll() {
        return corsoRepository.findAll().stream().map(corsoMapper::toDto).toList();
    }

    @Override
    public CorsoResponseDTO getById(Long corsoId) {
        return corsoRepository.findById(corsoId).stream().map(corsoMapper::toDto).findFirst().orElseThrow(() -> new ResourceNotFoundException("Corso", corsoId));
    }

    @Override
    public CorsoResponseDTO insert(CorsoDTO corsoDTO) {
        return null;
    }

    @Override
    public CorsoResponseDTO update(CorsoDTO corsoDTO, Long corsoId) {
        return null;
    }

    @Override
    public void delete(Long corsoId) {

    }
}
