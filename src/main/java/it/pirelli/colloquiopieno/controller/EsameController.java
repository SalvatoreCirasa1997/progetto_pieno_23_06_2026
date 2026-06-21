package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.EsameRequestDTO;
import it.pirelli.colloquiopieno.dto.EsameResponseDTO;
import it.pirelli.colloquiopieno.service.EsameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esame")
@RequiredArgsConstructor
public class EsameController {

    private final EsameService esameService;

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<EsameResponseDTO>> getAll(){
        return ResponseEntity.ok(esameService.getAll());
    }

    @GetMapping(value = "/get-by-id/{id}", produces = "application/json")
    public ResponseEntity<EsameResponseDTO> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(esameService.getById(id));
    }

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EsameResponseDTO> insert(
            @Valid @RequestBody EsameRequestDTO EsameRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(esameService.insert(EsameRequestDTO));
    }

    @PutMapping(value = "/{idEsame}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EsameResponseDTO> update(
            @PathVariable("idEsame") Long idEsame,
            @Valid @RequestBody EsameRequestDTO EsameRequestDTO
    ){
        return ResponseEntity.ok(esameService.update(idEsame, EsameRequestDTO));
    }

    @DeleteMapping(value = "/{idEsame}")
    public ResponseEntity<Void> delete(
            @PathVariable("idEsame") Long idEsame
    ){
        esameService.delete(idEsame);
        return ResponseEntity.ok().build();
    }
}
