package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.TestRequestDTO;
import it.pirelli.colloquiopieno.dto.TestResponseDTO;
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
    public List<TestResponseDTO> getAll() {
        return testRepository.findAll().stream()
                .map(testMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TestResponseDTO getById(Long testId) {
        return testRepository.findById(testId)
                .map(testMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Test", testId));
    }

    @Override
    @Transactional
    public TestResponseDTO create(TestRequestDTO testRequestDTO) {
        if (testRepository.existsByEmail(testRequestDTO.getEmail())) {
            throw new DuplicateResourceException("Test", "email", testRequestDTO.getEmail());
        }

        log.info("Creazione nuovo Test con email: {}", testRequestDTO.getEmail());

        Test testEntity = testMapper.toEntity(testRequestDTO);
        Test savedEntity = testRepository.save(testEntity);
        TestResponseDTO savedDTO = testMapper.toDto(savedEntity);

        log.info("Creato Test con ID: {}", savedDTO.getId());
        return savedDTO;
    }

    @Override
    @Transactional
    public TestResponseDTO update(Long testId, TestRequestDTO testRequestDTO) {
        Test existingEntity = testRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException("Test", testId));

        if (!existingEntity.getEmail().equals(testRequestDTO.getEmail()) &&
                testRepository.existsByEmail(testRequestDTO.getEmail())) {
            throw new DuplicateResourceException("Test", "email", testRequestDTO.getEmail());
        }

        testMapper.updateEntity(testRequestDTO, existingEntity);

        TestResponseDTO updatedDTO = testMapper.toDto(testRepository.save(existingEntity));
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