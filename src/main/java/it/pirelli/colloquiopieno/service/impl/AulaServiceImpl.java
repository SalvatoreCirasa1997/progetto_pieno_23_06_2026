package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.AulaRequestDTO;
import it.pirelli.colloquiopieno.dto.AulaResponseDTO;
import it.pirelli.colloquiopieno.entity.Aula;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.AulaMapper;
import it.pirelli.colloquiopieno.repository.AulaRepository;
import it.pirelli.colloquiopieno.service.AulaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class AulaServiceImpl implements AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;

    @Override
    public List<AulaResponseDTO> getAll() {
        log.info("Avvio del servizio getAll");

        List<AulaResponseDTO> dto = aulaRepository.findAll().stream().map(aulaMapper::toDto).toList();

        log.info("Fine del servizio getAll");
        return dto;
    }

    @Override
    public AulaResponseDTO getById(Long id) {
        log.info("Avvio del servizio getById");

        AulaResponseDTO dto = aulaRepository.findById(id).map(aulaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Aula", id));

        log.info("Fine del servizio getById");
        return dto;
    }

    @Override
    public AulaResponseDTO insert(AulaRequestDTO aulaRequestDTO) {
        log.info("Avvio del servizio insert");

        if (aulaRepository.existsByNome(aulaRequestDTO.getNome())) {
            throw new IllegalArgumentException("Un'aula con nome " + aulaRequestDTO.getNome() + " esiste già");
        }

        Aula entity = aulaRepository.save(aulaMapper.toEntity(aulaRequestDTO));
        AulaResponseDTO dto = aulaMapper.toDto(entity);

        log.info("Fine del servizio insert");
        return dto;
    }

    @Override
    public AulaResponseDTO update(Long idAula, AulaRequestDTO aulaRequestDTO) {
        log.info("Avvio del servizio update");

        Aula existingAula = aulaRepository.findById(idAula)
                .orElseThrow(() -> new ResourceNotFoundException("Aula", idAula));

        if(!existingAula.getNome().equals(aulaRequestDTO.getNome())) {
            if (aulaRepository.existsByNomeAndIdNot(aulaRequestDTO.getNome(), idAula)) {
                throw new IllegalArgumentException("Un'aula con nome " + aulaRequestDTO.getNome() + " esiste già");
            }
        }

        aulaMapper.updateEntity(aulaRequestDTO, existingAula);
        AulaResponseDTO dto = aulaMapper.toDto(aulaRepository.save(existingAula));

        log.info("Fine del servizio update");
        return dto;
    }

    @Override
    public void delete(Long idAula) {
        log.info("Avvio del servizio delete");
        if(!aulaRepository.existsById(idAula)) {
            throw new ResourceNotFoundException("Aula", idAula);
        }
        aulaRepository.deleteById(idAula);
        log.info("Fine del servizio delete");
    }
}
