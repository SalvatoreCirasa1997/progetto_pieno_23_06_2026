package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.GestioneCorsoFilterDTO;
import it.pirelli.colloquiopieno.dto.GestioneCorsoResponseDTO;
import it.pirelli.colloquiopieno.entity.GestioneCorso;
import it.pirelli.colloquiopieno.mapper.CorsoMapper;
import it.pirelli.colloquiopieno.mapper.UtenteMapper;
import it.pirelli.colloquiopieno.repository.GestioneCorsoRepository;
import it.pirelli.colloquiopieno.service.GestioneCorsoService;
import it.pirelli.colloquiopieno.specification.GestioneCorsoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GestioneCorsoServiceImpl implements GestioneCorsoService {

    private final GestioneCorsoRepository gestioneCorsoRepository;
    private final CorsoMapper corsoMapper;
    private final UtenteMapper utenteMapper;

    @Override
    public List<GestioneCorsoResponseDTO> getAll() {

        log.info("Avvio del servizio getAll per recuperare tutte le coppie utente-corso.");

        List<GestioneCorsoResponseDTO> gestioneCorsoResponseDTO = gestioneCorsoRepository.findAll().stream()
                .map(gestioneCorso -> GestioneCorsoResponseDTO.builder()
                        .corso(corsoMapper.toDto(gestioneCorso.getCorso()))
                        .user(utenteMapper.toDto(gestioneCorso.getUtente()))
                        .build())
                .collect(Collectors.toList());

        log.info("Fine del servizio getAll per recuperare tutte le coppie utente-corso.");
        return gestioneCorsoResponseDTO;
    }

    @Override
    public List<GestioneCorsoResponseDTO> getByFilter(GestioneCorsoFilterDTO gestioneCorsoFilterDTO) {
        log.info("Avvio del servizio getByFilter per recuperare le coppie utente-corso in base al filtro.");

        Specification<GestioneCorso> spec = GestioneCorsoSpecification.build(gestioneCorsoFilterDTO.getUtenteId(), gestioneCorsoFilterDTO.getCorsoId());

        List<GestioneCorsoResponseDTO> gestioneCorsoDto = gestioneCorsoRepository.findAll(spec).stream()
                .map(gestioneCorso -> GestioneCorsoResponseDTO.builder()
                        .corso(corsoMapper.toDto(gestioneCorso.getCorso()))
                        .user(utenteMapper.toDto(gestioneCorso.getUtente()))
                        .build())
                .toList();

        log.info("Fine del servizio getByFilter per recuperare le coppie utente-corso in base al filtro.");
        return gestioneCorsoDto;
    }

    @Override
    public void delete(Long utenteId, Long corsoId) {
        log.info("Avvio del servizio delete per la rimozione di un utente da uno o piu' corsi e viceversa.");

        Specification<GestioneCorso> spec = GestioneCorsoSpecification.build(utenteId, corsoId);
        List<GestioneCorso> gestioneCorsi = gestioneCorsoRepository.findAll(spec);

        if(gestioneCorsi.isEmpty()){
            throw new IllegalArgumentException("Nessuna coppia utente-corso trovata per i parametri forniti.");
        }
        gestioneCorsoRepository.deleteAll(gestioneCorsi);

        log.info("Fine del servizio delete per la rimozione di un utente da uno o piu' corsi e viceversa.");
    }

}
