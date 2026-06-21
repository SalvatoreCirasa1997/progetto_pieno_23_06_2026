package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.GestioneCorsoFilterDTO;
import it.pirelli.colloquiopieno.dto.GestioneCorsoResponseDTO;
import it.pirelli.colloquiopieno.entity.Corso;
import it.pirelli.colloquiopieno.entity.GestioneCorso;
import it.pirelli.colloquiopieno.entity.Utente;
import it.pirelli.colloquiopieno.enums.StatoCorsoEnum;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.CorsoMapper;
import it.pirelli.colloquiopieno.mapper.UtenteMapper;
import it.pirelli.colloquiopieno.repository.GestioneCorsoRepository;
import it.pirelli.colloquiopieno.repository.UtenteRepository;
import it.pirelli.colloquiopieno.service.CorsoService;
import it.pirelli.colloquiopieno.service.GestioneCorsoService;
import it.pirelli.colloquiopieno.specification.GestioneCorsoSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GestioneCorsoServiceImpl implements GestioneCorsoService {

    private final GestioneCorsoRepository gestioneCorsoRepository;
    private final CorsoMapper corsoMapper;
    private final UtenteMapper utenteMapper;
    private final CorsoService corsoService;
    private final UtenteRepository utenteRepository;

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

        if (gestioneCorsi.isEmpty()) {
            throw new ResourceNotFoundException("Nessuna coppia utente-corso trovata per i parametri forniti.");
        }
        gestioneCorsoRepository.deleteAll(gestioneCorsi);

        log.info("Fine del servizio delete per la rimozione di un utente da uno o piu' corsi e viceversa.");
    }

    @Override
    @Transactional
    public List<GestioneCorsoResponseDTO> subscribe(Long utenteId, List<Long> corsiList) {
        log.info("Avvio subscribe per utente {} ai corsi: {}", utenteId, corsiList);

        if (corsiList == null || corsiList.isEmpty()) {
            throw new IllegalArgumentException("La lista dei corsi non può essere vuota.");
        }

        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato."));

        List<Corso> corsi = corsoService.getByIds(corsiList);
        if (corsi.size() != corsiList.size()) {
            throw new ResourceNotFoundException("Alcuni corsi non sono stati trovati.");
        }

        List<GestioneCorso> iscrizioni = prepareAndValidateSubscriptions(utente, corsi);

        return saveAndMapSubscriptions(iscrizioni);
    }


    private List<GestioneCorso> prepareAndValidateSubscriptions(Utente utente, List<Corso> corsi) {
        List<Long> corsoIds = corsi.stream().map(Corso::getId).toList();

        List<Long> corsiGiaIscritti = gestioneCorsoRepository.findCorsoIdsByUtenteId(utente.getId());
        Map<Long, Integer> conteggioIscrizioni = gestioneCorsoRepository.countSubscriptionsByCorsoIdsAsMap(corsoIds);
        Map<Long, Integer> limitiCorsi = corsoService.getMaxParticipantsByCorsoIds(corsoIds);

        return corsi.stream()
                .map(corso -> {
                    validateCorso(corso, corsiGiaIscritti, conteggioIscrizioni, limitiCorsi);
                    return GestioneCorso.builder().corso(corso).utente(utente).build();
                })
                .toList();
    }

    private void validateCorso(Corso corso, List<Long> corsiGiaIscritti, Map<Long, Integer> conteggioIscrizioni, Map<Long, Integer> limitiCorsi) {
        if (!StatoCorsoEnum.NON_ANCORA_INIZIATO.equals(corso.getStato())) {
            throw new IllegalArgumentException("Corso " + corso.getId() + " non disponibile");
        }
        if (corsiGiaIscritti.contains(corso.getId())) {
            throw new IllegalArgumentException("Utente già iscritto al corso " + corso.getId());
        }
        if (conteggioIscrizioni.getOrDefault(corso.getId(), 0) >= limitiCorsi.getOrDefault(corso.getId(), 0)) {
            throw new IllegalArgumentException("Limite raggiunto per il corso " + corso.getId());
        }
    }

    private List<GestioneCorsoResponseDTO> saveAndMapSubscriptions(List<GestioneCorso> iscrizioni) {
        List<GestioneCorso> saved = gestioneCorsoRepository.saveAll(iscrizioni);
        return saved.stream()
                .map(gc -> GestioneCorsoResponseDTO.builder()
                        .corso(corsoMapper.toDto(gc.getCorso()))
                        .user(utenteMapper.toDto(gc.getUtente()))
                        .build())
                .toList();
    }
}
