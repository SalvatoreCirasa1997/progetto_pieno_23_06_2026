package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.CorsoDTO;
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

    @GetMapping("/get-all")
    public ResponseEntity<List<CorsoResponseDTO>> getAll() {
        return ResponseEntity.ok(corsoService.getAll());
    }

    @GetMapping("/{corsoId}")
    public ResponseEntity<CorsoResponseDTO> getById(
            @PathVariable Long corsoId
    ) {
        return ResponseEntity.ok(corsoService.getById(corsoId));
    }

    @PostMapping("")
    public ResponseEntity<CorsoResponseDTO> insert(
            @Valid @RequestBody CorsoDTO corsoDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(corsoService.insert(corsoDTO));
    }

    @PutMapping("/{corsoId}")
    public ResponseEntity<CorsoResponseDTO> update(
            @Valid @RequestBody CorsoDTO corsoDTO,
            @PathVariable Long corsoId
    ) {
        return ResponseEntity.ok(corsoService.update(corsoDTO, corsoId));
    }

    @DeleteMapping("/{corsoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long corsoId
    ) {
        corsoService.delete(corsoId);
        return ResponseEntity.ok().build();
    }
}
