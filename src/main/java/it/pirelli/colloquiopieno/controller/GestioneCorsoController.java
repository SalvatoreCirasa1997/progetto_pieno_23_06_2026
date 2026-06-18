package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.GestioneCorsoFilterDTO;
import it.pirelli.colloquiopieno.dto.GestioneCorsoResponseDTO;
import it.pirelli.colloquiopieno.service.GestioneCorsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestione-corso")
@RequiredArgsConstructor
public class GestioneCorsoController {

    private final GestioneCorsoService gestioneCorsoService;

    @GetMapping("/get-all")
    public ResponseEntity<List<GestioneCorsoResponseDTO>> getAll() {
        return ResponseEntity.ok(gestioneCorsoService.getAll());
    }

    @PostMapping("/get-by-filter")
    public ResponseEntity<List<GestioneCorsoResponseDTO>> getByFilter(
            @Valid @RequestBody GestioneCorsoFilterDTO gestioneCorsoFilterDTO
    ) {
        return ResponseEntity.ok(gestioneCorsoService.getByFilter(gestioneCorsoFilterDTO));
    }

    @GetMapping("/get-by-id/{gestioneCorsoId}")
    public ResponseEntity<GestioneCorsoResponseDTO> getById(
            @PathVariable Long gestioneCorsoId
    ) {
        return ResponseEntity.ok(gestioneCorsoService.getById(gestioneCorsoId));
    }

    @DeleteMapping("/{gestioneCorsoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long gestioneCorsoId
    ) {
        gestioneCorsoService.delete(gestioneCorsoId);
        return ResponseEntity.ok().build();
    }
}
