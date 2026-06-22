package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.UtenteRequestDTO;
import it.pirelli.colloquiopieno.dto.UtenteFilterDTO;
import it.pirelli.colloquiopieno.dto.UtenteResponseDTO;
import it.pirelli.colloquiopieno.entity.Utente;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.UtenteMapper;
import it.pirelli.colloquiopieno.repository.UtenteRepository;
import it.pirelli.colloquiopieno.service.UtenteService;
import it.pirelli.colloquiopieno.specification.UtenteSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteMapper utenteMapper;

    @Override
    public List<UtenteResponseDTO> getAll() {
        log.info("Avvio del servizio getAll per il recupero di tutti gli utenti.");

        List<UtenteResponseDTO>utentiDto = utenteRepository.findAll().stream().map(utenteMapper::toDto).toList();

        log.info("Fine del servizio getAll per il recupero di tutti gli utenti.");
        return utentiDto;
    }

    @Override
    public UtenteResponseDTO getById(Long utenteId) {
        log.info("Avvio del servizio getById per il recupero dell'utente con ID: {}", utenteId);

        UtenteResponseDTO utenteDto = utenteRepository.findById(utenteId).map(utenteMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Utente", utenteId));

        log.info("Fine del servizio getById per l'utente con ID: {}", utenteId);
        return utenteDto;
    }

    @Override
    public List<UtenteResponseDTO> getByFilter(UtenteFilterDTO filter) {

        log.info("Avvio del servizio getByFilter per il recupero degli utenti con i filtri: {}", filter);

        Specification<Utente> spec = UtenteSpecification.build(filter);

        List<UtenteResponseDTO> utentiDto = utenteRepository.findAll(spec).stream()
                .map(utenteMapper::toDto)
                .toList();

        log.info("Fine del servizio getByFilter per il recupero degli utenti con i filtri: {}", filter);
        return utentiDto;
    }

    @Override
    @Transactional
    public UtenteResponseDTO insert(UtenteRequestDTO utenteRequestDTO) {
        if(utenteRepository.existsByCf(utenteRequestDTO.getCf())) {
            throw new DuplicateResourceException("Utente", "cf", utenteRequestDTO.getCf());
        }
        if(utenteRepository.existsByEmail(utenteRequestDTO.getEmail())) {
            throw new DuplicateResourceException("Utente", "email", utenteRequestDTO.getEmail());
        }

        log.info("Avvio del servizio insert per la creazione di un nuovo utente");

        Utente utente = utenteMapper.toEntity(utenteRequestDTO);
        utente = utenteRepository.save(utente);
        UtenteResponseDTO utenteDto = utenteMapper.toDto(utente);

        log.info("Fine del servizio insert per la creazione di un nuovo utente.");
        return utenteDto;
    }

    @Override
    @Transactional
    public UtenteResponseDTO update(UtenteRequestDTO utenteRequestDTO, Long utenteId) {
        Utente existingUtente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Utente", utenteId));

        if(!existingUtente.getCf().equals(utenteRequestDTO.getCf())) {
            if(utenteRepository.existsByCfAndIdNot(utenteRequestDTO.getCf(), utenteId)) {
                throw new DuplicateResourceException("Utente", "cf", utenteRequestDTO.getCf());
            }
        }
        if(!existingUtente.getEmail().equals(utenteRequestDTO.getEmail())) {
            if(utenteRepository.existsByEmailAndIdNot(utenteRequestDTO.getEmail(), utenteId)) {
                throw new DuplicateResourceException("Utente", "email", utenteRequestDTO.getEmail());
            }
        }

        log.info("Avvio del servizio update per l'aggiornamento dell'utente con ID: {}", utenteId);

        utenteMapper.updateUtente(utenteRequestDTO, existingUtente);
        UtenteResponseDTO utenteDto = utenteMapper.toDto(utenteRepository.save(existingUtente));

        log.info("Fine del servizio update per l'utente con ID: {}", utenteId);
        return utenteDto;
    }

    @Override
    public void delete(Long utenteId) {
        if(!utenteRepository.existsById(utenteId)) {
            throw new ResourceNotFoundException("Utente", utenteId);
        }

        log.info("Avvio del servizio delete per l'eliminazione dell'utente con ID: {}", utenteId);

        utenteRepository.deleteById(utenteId);

        log.info("Fine del servizio delete per l'utente con ID: {}", utenteId);
    }
}
