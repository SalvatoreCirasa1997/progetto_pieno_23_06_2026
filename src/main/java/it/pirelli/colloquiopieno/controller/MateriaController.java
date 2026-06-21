package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.MateriaRequestDTO;
import it.pirelli.colloquiopieno.dto.MateriaResponseDTO;
import it.pirelli.colloquiopieno.service.MateriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<MateriaResponseDTO>> getAll(){
        return ResponseEntity.ok(materiaService.getAll());
    }

    @GetMapping(value = "/get-by-id/{id}", produces = "application/json")
    public ResponseEntity<MateriaResponseDTO> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(materiaService.getById(id));
    }

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<MateriaResponseDTO> insert(
            @Valid @RequestBody MateriaRequestDTO MateriaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.insert(MateriaRequestDTO));
    }

    @PutMapping(value = "/{idMateria}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<MateriaResponseDTO> update(
            @PathVariable("idMateria") Long idMateria,
            @Valid @RequestBody MateriaRequestDTO MateriaRequestDTO
    ){
        return ResponseEntity.ok(materiaService.update(idMateria, MateriaRequestDTO));
    }

    @DeleteMapping(value = "/{idMateria}")
    public ResponseEntity<Void> delete(
            @PathVariable("idMateria") Long idMateria
    ){
        materiaService.delete(idMateria);
        return ResponseEntity.ok().build();
    }
}
