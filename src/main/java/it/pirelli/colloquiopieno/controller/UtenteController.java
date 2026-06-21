package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.UtenteRequestDTO;
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

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<UtenteResponseDTO>> getAll() {
        return ResponseEntity.ok(utenteService.getAll());
    }

    @GetMapping(value = "/{utenteId}", produces = "application/json")
    public ResponseEntity<UtenteResponseDTO> getById(
            @PathVariable Long utenteId
    ) {
        return ResponseEntity.ok(utenteService.getById(utenteId));
    }

    @PostMapping(value = "/find-by-filter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<UtenteResponseDTO>> getByFilter(
            @Valid @RequestBody UtenteFilterDTO utenteFilterDTO
    ) {
        return ResponseEntity.ok(utenteService.getByFilter(utenteFilterDTO));
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UtenteResponseDTO> insert(
            @Valid @RequestBody UtenteRequestDTO utenteRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utenteService.insert(utenteRequestDTO));
    }

    @PutMapping(value = "/{utenteId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UtenteResponseDTO> update(
            @Valid @RequestBody UtenteRequestDTO utenteRequestDTO,
            @PathVariable Long utenteId
    ) {
        return ResponseEntity.ok(utenteService.update(utenteRequestDTO, utenteId));
    }

    @DeleteMapping(value = "/{utenteId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long utenteId
    ) {
        utenteService.delete(utenteId);
        return ResponseEntity.ok().build();
    }
}
