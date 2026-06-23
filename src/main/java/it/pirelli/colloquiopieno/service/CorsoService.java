package it.pirelli.colloquiopieno.service;


import it.pirelli.colloquiopieno.dto.CorsoRequestDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.entity.Corso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CorsoService {
    List<CorsoResponseDTO> getAll();

    CorsoResponseDTO getById(Long corsoId);

    List<Corso> getByIds(List<Long> corsoIds);

    CorsoResponseDTO insert(CorsoRequestDTO corsoRequestDTO);

    CorsoResponseDTO update(CorsoRequestDTO corsoRequestDTO, Long corsoId);

    void delete(Long corsoId);

    Map<Long, Integer> getMaxParticipantsByCorsoIds(List<Long> corsiList);

    List<CorsoResponseDTO> getAvailableCourses();

    Page<CorsoResponseDTO> getAll(Pageable pageable);
}
