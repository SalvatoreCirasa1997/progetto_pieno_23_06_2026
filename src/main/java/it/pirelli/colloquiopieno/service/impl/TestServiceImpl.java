package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.TestDTO;
import it.pirelli.colloquiopieno.entity.Test;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.TestMapper;
import it.pirelli.colloquiopieno.repository.TestRepository;
import it.pirelli.colloquiopieno.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TestDTO> getAll() {
        return testRepository.findAll().stream()
                .map(testMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TestDTO getById(Long testId) {
        return testRepository.findById(testId)
                .map(testMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Test", testId));
    }

    @Override
    @Transactional
    public TestDTO create(TestDTO testDTO) {
        if (testRepository.existsByEmail(testDTO.getEmail())) {
            throw new DuplicateResourceException("Test", "email", testDTO.getEmail());
        }

        log.info("Creazione nuovo Test con email: {}", testDTO.getEmail());

        Test testEntity = testMapper.toEntity(testDTO);
        Test savedEntity = testRepository.save(testEntity);
        TestDTO savedDTO = testMapper.toDto(savedEntity);

        log.info("Creato Test con ID: {}", savedDTO.getId());
        return savedDTO;
    }

    @Override
    @Transactional
    public TestDTO update(Long testId, TestDTO testDTO) {
        // Prima recupero l'entità esistente
        var existingEntity = testRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException("Test", testId));

        if (!existingEntity.getEmail().equals(testDTO.getEmail()) &&
                testRepository.existsByEmail(testDTO.getEmail())) {
            throw new DuplicateResourceException("Test", "email", testDTO.getEmail());
        }

        testMapper.updateEntity(testDTO, existingEntity);

        TestDTO updatedDTO = testMapper.toDto(testRepository.save(existingEntity));
        log.info("Aggiornato Test con ID: {}", testId);

        return updatedDTO;
    }

    @Override
    @Transactional
    public void delete(Long testId) {
        if (!testRepository.existsById(testId)) {
            throw new ResourceNotFoundException("Test", testId);
        }
        log.info("Eliminato Test con ID: {}", testId);
        testRepository.deleteById(testId);
    }
}