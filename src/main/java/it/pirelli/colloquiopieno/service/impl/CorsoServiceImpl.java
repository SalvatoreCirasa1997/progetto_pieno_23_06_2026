package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.AulaResponseDTO;
import it.pirelli.colloquiopieno.dto.CorsoRequestDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.dto.MateriaResponseDTO;
import it.pirelli.colloquiopieno.entity.Aula;
import it.pirelli.colloquiopieno.entity.Corso;
import it.pirelli.colloquiopieno.entity.Materia;
import it.pirelli.colloquiopieno.enums.StatoCorsoEnum;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.AulaMapper;
import it.pirelli.colloquiopieno.mapper.CorsoMapper;
import it.pirelli.colloquiopieno.mapper.MateriaMapper;
import it.pirelli.colloquiopieno.repository.AulaRepository;
import it.pirelli.colloquiopieno.repository.CorsoRepository;
import it.pirelli.colloquiopieno.repository.GestioneCorsoRepository;
import it.pirelli.colloquiopieno.repository.MateriaRepository;
import it.pirelli.colloquiopieno.service.CorsoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    private final AulaMapper aulaMapper;
    private final GestioneCorsoRepository gestioneCorsoRepository;
    private final AulaRepository aulaRepository;
    private final MateriaMapper materiaMapper;
    private final MateriaRepository materiaRepository;

    @Override
    public List<CorsoResponseDTO> getAll() {
        log.info("Avvio del servizio getAll per recuperare tutti i corsi.");

        List<CorsoResponseDTO> corsiDto = corsoRepository.findAll().stream()
                .map(corso -> {
                    CorsoResponseDTO corsoDto = corsoMapper.toDto(corso);
                    AulaResponseDTO aulaDto = aulaMapper.toDto(corso.getAula());
                    MateriaResponseDTO materiaDto = materiaMapper.toDto(corso.getMateria());
                    corsoDto.setAula(aulaDto);
                    corsoDto.setMateria(materiaDto);
                    return corsoDto;
                })
                .toList();

        log.info("Fine del servizio getAll per recuperare tutti i corsi.");
        return corsiDto;
    }

    @Override
    public CorsoResponseDTO getById(Long corsoId) {
        log.info("Avvio del servizio getById per recuperare il corso con ID: {}", corsoId);

        CorsoResponseDTO corsoDto = corsoRepository.findById(corsoId).map(corso -> {
            CorsoResponseDTO dto = corsoMapper.toDto(corso);
            AulaResponseDTO aulaDto = aulaMapper.toDto(corso.getAula());
            dto.setAula(aulaDto);
            MateriaResponseDTO materiaDto = materiaMapper.toDto(corso.getMateria());
            dto.setMateria(materiaDto);
            return dto;
        }).orElseThrow(() -> new ResourceNotFoundException("Corso", corsoId));

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
    @Transactional
    public CorsoResponseDTO insert(CorsoRequestDTO corsoRequestDTO) {
        if(corsoRepository.existsByTitolo(corsoRequestDTO.getTitolo())) {
            throw new IllegalArgumentException("Esiste già un corso con il titolo: " + corsoRequestDTO.getTitolo());
        }

        Aula aula = aulaRepository.findById(corsoRequestDTO.getIdAula())
                .orElseThrow(() -> new ResourceNotFoundException("Aula", corsoRequestDTO.getIdAula()));

        Materia materia = materiaRepository.findById(corsoRequestDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("Materia", corsoRequestDTO.getIdMateria()));

        log.info("Avvio del servizio insert per la creazione di un nuovo corso: {}", corsoRequestDTO.getTitolo());

        Corso corso = corsoMapper.toEntity(corsoRequestDTO);
        corso.setAula(aula);
        corso.setMateria(materia);

        CorsoResponseDTO corsoDto = corsoMapper.toDto(corsoRepository.save(corso));
        corsoDto.setMateria(materiaMapper.toDto(materia));
        corsoDto.setAula(aulaMapper.toDto(aula));

        log.info("Fine del servizio insert per la creazione del corso: {}", corsoRequestDTO.getTitolo());
        return corsoDto;
    }

    @Override
    @Transactional
    public CorsoResponseDTO update(CorsoRequestDTO corsoRequestDTO, Long corsoId) {
        Corso existingCorso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new ResourceNotFoundException("Corso", corsoId));

        if(!existingCorso.getTitolo().equals(corsoRequestDTO.getTitolo())) {
            if(corsoRepository.existsByTitoloAndIdNot(corsoRequestDTO.getTitolo(), corsoId)) {
                throw new DuplicateResourceException("Corso", "titolo", corsoRequestDTO.getTitolo());
            }
        }

        Aula aula = aulaRepository.findById(corsoRequestDTO.getIdAula())
                .orElseThrow(() -> new ResourceNotFoundException("Aula", corsoRequestDTO.getIdAula()));

        Materia materia = materiaRepository.findById(corsoRequestDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("Materia", corsoRequestDTO.getIdMateria()));

        log.info("Avvio del servizio update per l'aggiornamento del corso con ID: {}", corsoId);

        corsoMapper.updateCorso(corsoRequestDTO, existingCorso);
        existingCorso.setAula(aula);
        existingCorso.setMateria(materia);

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

    @Override
    public List<CorsoResponseDTO> getAvailableCourses() {
        log.info("Avvio del servizio getAvailableCourses per recuperare i corsi disponibili.");

        List<Corso> corsiList = corsoRepository.findAllByStato(StatoCorsoEnum.NON_ANCORA_INIZIATO);

        List<Long> corsoIds = corsiList.stream().map(Corso::getId).toList();

        Map<Long, Integer> conteggioIscrizioni = gestioneCorsoRepository.countSubscriptionsByCorsoIdsAsMap(corsoIds);

        List<CorsoResponseDTO> corsiDisponibili = corsiList.stream()
                .filter(corso -> conteggioIscrizioni.getOrDefault(corso.getId(), 0) < corso.getNumeroMassimoPartecipanti())
                .map(corsoMapper::toDto)
                //.sorted((c1,c2) -> c1.getTitolo().compareToIgnoreCase(c2.getTitolo()))
                .sorted(Comparator
                        .comparingInt((CorsoResponseDTO c) -> c.getStato().getOrder())
                        .thenComparing(Comparator.comparing(CorsoResponseDTO::getDataInizio).reversed()))
                .toList();
        /*
        // Esempio Bubble Sort: ordinamento per ID (crescente)
        int n = corsiDisponibili.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (corsiDisponibili.get(j).getId() > corsiDisponibili.get(j + 1).getId()) {
                    CorsoResponseDTO temp = corsiDisponibili.get(j);
                    corsiDisponibili.set(j, corsiDisponibili.get(j + 1));
                    corsiDisponibili.set(j + 1, temp);
                }
            }
        }

        // Esempio: ordinamento per ID (decrescente)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (corsiDisponibili.get(j).getId() < corsiDisponibili.get(j + 1).getId()) {
                    CorsoResponseDTO temp = corsiDisponibili.get(j);
                    corsiDisponibili.set(j, corsiDisponibili.get(j + 1));
                    corsiDisponibili.set(j + 1, temp);
                }
            }
        }
        */

        log.info("Fine del servizio getAvailableCourses per i corsi disponibili.");
        return corsiDisponibili;
    }
}
