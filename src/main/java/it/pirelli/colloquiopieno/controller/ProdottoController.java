package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.ProdottoRequestDTO;
import it.pirelli.colloquiopieno.dto.ProdottoResponseDTO;
import it.pirelli.colloquiopieno.service.ProdottoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
@RequiredArgsConstructor
@Slf4j
public class ProdottoController {

    private final ProdottoService prodottoService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdottoResponseDTO>> getAll() {

        log.info("Start: getAll Service");
        List<ProdottoResponseDTO> result = prodottoService.getAll();
        log.info("End: getAll Service");

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdottoResponseDTO> getById(
            @PathVariable(name = "id") Long id
    ) {

        log.info("Start: getById Service");
        ProdottoResponseDTO result = prodottoService.getById(id);
        log.info("End: getById Service");

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdottoResponseDTO> insert(
            @Valid @RequestBody ProdottoRequestDTO prodottoRequestDTO
    ) {

        log.info("Start: insert Service");
        ProdottoResponseDTO result = prodottoService.insert(prodottoRequestDTO);
        log.info("End: insert Service");

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdottoResponseDTO> update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody ProdottoRequestDTO prodottoRequestDTO
    ) {

        log.info("Start: update Service");
        ProdottoResponseDTO result = prodottoService.update(id, prodottoRequestDTO);
        log.info("End: update Service");

        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") Long id
    ) {

        log.info("Start: delete Service");
        prodottoService.delete(id);
        log.info("End: delete Service");

        return ResponseEntity.ok().build();
    }
}
