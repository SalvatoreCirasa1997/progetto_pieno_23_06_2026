package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.UtenteDTO;
import it.pirelli.colloquiopieno.dto.UtenteFilterDTO;
import it.pirelli.colloquiopieno.dto.UtenteResponseDTO;
import it.pirelli.colloquiopieno.service.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService utenteService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UtenteResponseDTO>> getAll() {
        return ResponseEntity.ok(utenteService.getAll());
    }

    @GetMapping("/{utenteId}")
    public ResponseEntity<List<UtenteResponseDTO>> getById(
            @PathVariable Long utenteId
    ) {
        return ResponseEntity.ok(utenteService.getById(utenteId));
    }

    @PostMapping("/find-by-filter")
    public ResponseEntity<List<UtenteResponseDTO>> getByFilter(
            @Valid @RequestBody UtenteFilterDTO utenteFilterDTO
    ) {
        return ResponseEntity.ok(utenteService.getByFilter(utenteFilterDTO));
    }

    @PostMapping("")
    public ResponseEntity<UtenteResponseDTO> insert(
            @Valid @RequestBody UtenteDTO utenteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utenteService.insert(utenteDTO));
    }

    @PutMapping("/{utenteId}")
    public ResponseEntity<UtenteResponseDTO> update(
            @Valid @RequestBody UtenteDTO utenteDTO,
            @PathVariable Long utenteId
    ) {
        return ResponseEntity.ok(utenteService.update(utenteDTO, utenteId));
    }

    @DeleteMapping("/{utenteId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long utenteId
    ) {
        utenteService.delete(utenteId);
        return ResponseEntity.ok().build();
    }
}
