package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.CorsoRequestDTO;
import it.pirelli.colloquiopieno.dto.CorsoResponseDTO;
import it.pirelli.colloquiopieno.service.CorsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corso")
@RequiredArgsConstructor
public class CorsoController {

    private final CorsoService corsoService;

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<CorsoResponseDTO>> getAll() {
        return ResponseEntity.ok(corsoService.getAll());
    }

    @GetMapping(value = "/{corsoId}", produces = "application/json")
    public ResponseEntity<CorsoResponseDTO> getById(
            @PathVariable Long corsoId
    ) {
        return ResponseEntity.ok(corsoService.getById(corsoId));
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CorsoResponseDTO> insert(
            @Valid @RequestBody CorsoRequestDTO corsoRequestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(corsoService.insert(corsoRequestDTO));
    }

    @PutMapping(value = "/{corsoId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CorsoResponseDTO> update(
            @PathVariable Long corsoId,
            @Valid @RequestBody CorsoRequestDTO corsoRequestDTO
    ) {
        return ResponseEntity.ok(corsoService.update(corsoRequestDTO, corsoId));
    }

    @DeleteMapping(value = "/{corsoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long corsoId
    ) {
        corsoService.delete(corsoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(name = "/get-available-courses", produces = "application/json")
    public ResponseEntity<List<CorsoResponseDTO>> getAvailableCourses(){
        return ResponseEntity.ok(corsoService.getAvailableCourses());
    }
}
