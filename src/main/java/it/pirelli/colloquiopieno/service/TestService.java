package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.TestRequestDTO;
import it.pirelli.colloquiopieno.dto.TestResponseDTO;

import java.util.List;

public interface TestService {

    List<TestResponseDTO> getAll();

    TestResponseDTO getById(Long testId);

    TestResponseDTO create(TestRequestDTO testRequestDTO);

    TestResponseDTO update(Long testId, TestRequestDTO testRequestDTO);

    void delete(Long testId);
}
