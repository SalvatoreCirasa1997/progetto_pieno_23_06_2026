package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.AulaRequestDTO;
import it.pirelli.colloquiopieno.dto.AulaResponseDTO;
import it.pirelli.colloquiopieno.service.AulaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<AulaResponseDTO>> getAll(){
        return ResponseEntity.ok(aulaService.getAll());
    }

    @GetMapping(value = "/get-by-id/{id}", produces = "application/json")
    public ResponseEntity<AulaResponseDTO> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(aulaService.getById(id));
    }

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AulaResponseDTO> insert(
            @Valid @RequestBody AulaRequestDTO aulaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaService.insert(aulaRequestDTO));
    }

    @PutMapping(value = "/{idAula}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AulaResponseDTO> update(
            @PathVariable("idAula") Long idAula,
            @Valid @RequestBody AulaRequestDTO aulaRequestDTO
    ){
        return ResponseEntity.ok(aulaService.update(idAula, aulaRequestDTO));
    }

    @DeleteMapping(value = "/{idAula}")
    public ResponseEntity<Void> delete(
            @PathVariable("idAula") Long idAula
    ){
        aulaService.delete(idAula);
        return ResponseEntity.ok().build();
    }
}
