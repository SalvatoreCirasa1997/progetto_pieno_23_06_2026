package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.EsameRequestDTO;
import it.pirelli.colloquiopieno.dto.EsameResponseDTO;
import it.pirelli.colloquiopieno.entity.Esame;
import it.pirelli.colloquiopieno.entity.Materia;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.EsameMapper;
import it.pirelli.colloquiopieno.mapper.MateriaMapper;
import it.pirelli.colloquiopieno.repository.EsameRepository;
import it.pirelli.colloquiopieno.repository.MateriaRepository;
import it.pirelli.colloquiopieno.service.EsameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsameServiceImpl implements EsameService {

    private final EsameRepository esameRepository;
    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;
    private final EsameMapper esameMapper;

    @Override
    public List<EsameResponseDTO> getAll() {
        log.info("Avvio del servizio getAll.");

        List<EsameResponseDTO> response = esameRepository.findAll().stream()
                .map(esame ->{
                    EsameResponseDTO esameResponseDTO = esameMapper.toDto(esame);
                    esameResponseDTO.setMateria(materiaMapper.toDto(esame.getMateria()));
                    return esameResponseDTO;
                })
                .collect(Collectors.toList());

        log.info("Fine del servizio getAll encontrada.");
        return response;
    }

    @Override
    public EsameResponseDTO getById(Long id) {
        log.info("Avvio del servizio getById.");

        EsameResponseDTO response = esameRepository.findById(id).map(esame ->{
            EsameResponseDTO esameResponseDTO = esameMapper.toDto(esame);
            esameResponseDTO.setMateria(materiaMapper.toDto(esame.getMateria()));
            return esameResponseDTO;
        }).orElseThrow(() -> new ResourceNotFoundException("esame", id));

        log.info("Fine del servizio getById encontrada.");
        return response;
    }

    @Override
    @Transactional
    public EsameResponseDTO insert(EsameRequestDTO esameRequestDTO) {
        log.info("Avvio del servizio insert.");

        Materia materia = materiaRepository.findById(esameRequestDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("materia", esameRequestDTO.getIdMateria()));

        if(esameRepository.existsByNome(esameRequestDTO.getNome())) {
            throw new DuplicateResourceException("Esame", "nome", esameRequestDTO.getNome());
        }

        Esame esame = esameMapper.toEntity(esameRequestDTO);
        esame.setMateria(materia);

        EsameResponseDTO response = esameMapper.toDto(esameRepository.save(esame));
        response.setMateria(materiaMapper.toDto(materia));

        log.info("Fine del servizio insert encontrada.");
        return response;
    }

    @Override
    public EsameResponseDTO update(Long idEsame, EsameRequestDTO esameRequestDTO) {
        log.info("Avvio del servizio update.");

        Esame existingEsame =  esameRepository.findById(idEsame)
                .orElseThrow(() -> new ResourceNotFoundException("esame", idEsame));

        Materia materia = materiaRepository.findById(esameRequestDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("materia", esameRequestDTO.getIdMateria()));

        if(!existingEsame.getNome().equals(esameRequestDTO.getNome())){
            if(esameRepository.existsByNomeAndIdNot(esameRequestDTO.getNome(), idEsame)) {
                throw new DuplicateResourceException("Esame", "nome", esameRequestDTO.getNome());
            }
        }

        esameMapper.updateEntity(esameRequestDTO, existingEsame);
        existingEsame.setMateria(materia);

        EsameResponseDTO response = esameMapper.toDto(esameRepository.save(existingEsame));
        response.setMateria(materiaMapper.toDto(materia));

        log.info("Fine del servizio update encontrada.");
        return response;
    }

    @Override
    public void delete(Long idEsame) {
        log.info("Avvio del servizio deleteById.");

        if(!esameRepository.existsById(idEsame)) {
            throw new ResourceNotFoundException("esame", idEsame);
        }
        esameRepository.deleteById(idEsame);
        log.info("Fine del servizio deleteById.");
    }
}
