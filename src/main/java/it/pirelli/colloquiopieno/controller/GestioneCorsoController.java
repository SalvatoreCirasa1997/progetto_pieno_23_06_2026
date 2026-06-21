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

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<GestioneCorsoResponseDTO>> getAll() {
        return ResponseEntity.ok(gestioneCorsoService.getAll());
    }

    @PostMapping(value = "/get-by-filter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<GestioneCorsoResponseDTO>> getByFilter(
            @Valid @RequestBody GestioneCorsoFilterDTO gestioneCorsoFilterDTO
    ) {
        return ResponseEntity.ok(gestioneCorsoService.getByFilter(gestioneCorsoFilterDTO));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Void> delete(
            @RequestParam(required = false) Long utenteId,
            @RequestParam(required = false) Long corsoId
    ) {
        if(utenteId == null && corsoId == null){
            throw new IllegalArgumentException("Almeno uno dei parametri utenteId o corsoId deve essere fornito");
        }
        gestioneCorsoService.delete(utenteId, corsoId);
        return ResponseEntity.ok().build();
    }
}
