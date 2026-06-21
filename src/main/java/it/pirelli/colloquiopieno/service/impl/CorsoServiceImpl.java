package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.CorsoRequestDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.entity.Corso;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.CorsoMapper;
import it.pirelli.colloquiopieno.repository.CorsoRepository;
import it.pirelli.colloquiopieno.service.CorsoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorsoServiceImpl implements CorsoService {

    private final CorsoRepository corsoRepository;
    private final CorsoMapper corsoMapper;

    @Override
    public List<CorsoResponseDTO> getAll() {
        log.info("Avvio del servizio getAll per recuperare tutti i corsi.");

        List<CorsoResponseDTO> corsiDto = corsoRepository.findAll().stream().map(corsoMapper::toDto).toList();

        log.info("Fine del servizio getAll per recuperare tutti i corsi.");
        return corsiDto;
    }

    @Override
    public CorsoResponseDTO getById(Long corsoId) {
        log.info("Avvio del servizio getById per recuperare il corso con ID: {}", corsoId);

        CorsoResponseDTO corsoDto = corsoRepository.findById(corsoId).stream().map(corsoMapper::toDto).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Corso", corsoId));

        log.info("Fine del servizio getById per il corso con ID: {}", corsoId);
        return corsoDto;
    }

    @Override
    public List<Corso> getByIds(List<Long> corsoIds) {
        List<Corso> corsi = corsoRepository.findAllById(corsoIds);

        Set<Long> foundIds = corsi.stream().map(Corso::getId).collect(Collectors.toSet());

        List<Long> missingIds = corsoIds.stream().filter(id -> !foundIds.contains(id)).toList();

        if (!missingIds.isEmpty()) {
            throw new ResourceNotFoundException("Corsi non trovati: " + missingIds);
        }

        return corsi;
    }

    @Override
    public CorsoResponseDTO insert(CorsoRequestDTO corsoRequestDTO) {
        if(corsoRepository.existsByTitolo(corsoRequestDTO.getTitolo())) {
            throw new IllegalArgumentException("Esiste già un corso con il titolo: " + corsoRequestDTO.getTitolo());
        }

        log.info("Avvio del servizio insert per la creazione di un nuovo corso: {}", corsoRequestDTO.getTitolo());

        CorsoResponseDTO corsoDto = corsoMapper.toDto(corsoRepository.save(corsoMapper.toEntity(corsoRequestDTO)));

        log.info("Fine del servizio insert per la creazione del corso: {}", corsoRequestDTO.getTitolo());
        return corsoDto;
    }

    @Override
    public CorsoResponseDTO update(CorsoRequestDTO corsoRequestDTO, Long corsoId) {
        Corso existingCorso = corsoRepository.findById(corsoId).orElseThrow(() -> new ResourceNotFoundException("Corso", corsoId));

        if(!existingCorso.getTitolo().equals(corsoRequestDTO.getTitolo())) {
            if(corsoRepository.existsByTitoloAndIdNot(corsoRequestDTO.getTitolo(), corsoId)) {
                throw new DuplicateResourceException("Corso", "titolo", corsoRequestDTO.getTitolo());
            }
        }

        log.info("Avvio del servizio update per l'aggiornamento del corso con ID: {}", corsoId);

        corsoMapper.updateCorso(corsoRequestDTO, existingCorso);
        CorsoResponseDTO updatedCorsoDto = corsoMapper.toDto(corsoRepository.save(existingCorso));

        log.info("Fine del servizio update per il corso con ID: {}", corsoId);
        return updatedCorsoDto;
    }

    @Override
    public void delete(Long corsoId) {
        if(!corsoRepository.existsById(corsoId)){
            throw new ResourceNotFoundException("Corso", corsoId);
        }

        log.info("Avvio del servizio delete per l'eliminazione del corso con ID: {}", corsoId);

        corsoRepository.deleteById(corsoId);

        log.info("Fine del servizio delete per il corso con ID: {}", corsoId);
    }

    @Override
    public Map<Long, Integer> getMaxParticipantsByCorsoIds(List<Long> corsoIds) {
        List<Object[]> results = corsoRepository.findMaxParticipantsByCorsoIds(corsoIds);

        return results.stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> ((Number) row[1]).intValue()
                ));
    }
}
