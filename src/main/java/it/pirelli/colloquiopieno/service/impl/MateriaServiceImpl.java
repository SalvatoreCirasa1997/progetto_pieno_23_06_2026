package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.MateriaRequestDTO;
import it.pirelli.colloquiopieno.dto.MateriaResponseDTO;
import it.pirelli.colloquiopieno.entity.Materia;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.MateriaMapper;
import it.pirelli.colloquiopieno.repository.MateriaRepository;
import it.pirelli.colloquiopieno.service.MateriaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    @Override
    public List<MateriaResponseDTO> getAll() {
        log.info("Avvio del servizio getAll per recuperare tutte le materie.");

        List<MateriaResponseDTO> response =  materiaMapper.toDto(materiaRepository.findAll());

        log.info("Fine del servizio getAll per recuperare tutte le materie.");
        return response;
    }

    @Override
    public MateriaResponseDTO getById(Long id) {
        log.info("Avvio del servizio getById per recuperare la materia con ID: {}", id);

         MateriaResponseDTO response = materiaRepository.findById(id)
                .map(materiaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Materia" + id));

        log.info("Fine del servizio getById per recuperare la materia con ID: {}", id);
        return response;
    }

    @Override
    @Transactional
    public MateriaResponseDTO insert(MateriaRequestDTO materiaRequestDTO) {
        log.info("Avvio del servizio insert per inserire una nuova materia.");

        if(materiaRepository.existsByNome(materiaRequestDTO.getNome())){
            throw new DuplicateResourceException("Materia", "nome", materiaRequestDTO.getNome());
        }

        Materia materia = materiaMapper.toEntity(materiaRequestDTO);
        MateriaResponseDTO response = materiaMapper.toDto(materiaRepository.save(materia));

        log.info("Fine del servizio insert per inserire una nuova materia.");
        return response;
    }

    @Override
    @Transactional
    public MateriaResponseDTO update(Long idMateria, MateriaRequestDTO materiaRequestDTO) {
        log.info("Avvio del servizio update per aggiornare la materia con ID: {}", idMateria);

        Materia materia = materiaRepository.findById(idMateria)
                .orElseThrow(() -> new ResourceNotFoundException("Materia" + idMateria));

        if(!materia.getNome().equals(materiaRequestDTO.getNome()) && materiaRepository.existsByNomeAndIdNot(materiaRequestDTO.getNome(), idMateria)){
            throw new DuplicateResourceException("Materia", "nome", materiaRequestDTO.getNome());
        }

        materiaMapper.updateEntity(materiaRequestDTO, materia);
        MateriaResponseDTO response = materiaMapper.toDto(materiaRepository.save(materia));

        log.info("Fine del servizio update per aggiornare la materia con ID: {}", idMateria);
        return response;
    }

    @Override
    public void delete(Long idMateria) {
        log.info("Avvio del servizio delete per eliminare la materia con ID: {}", idMateria);

        Materia materia = materiaRepository.findById(idMateria)
                .orElseThrow(() -> new ResourceNotFoundException("Materia" + idMateria));

        materiaRepository.delete(materia);

        log.info("Fine del servizio delete per eliminare la materia con ID: {}", idMateria);

    }
}
