package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.TestRequestDTO;
import it.pirelli.colloquiopieno.dto.TestResponseDTO;
import it.pirelli.colloquiopieno.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<List<TestResponseDTO>> getAll() {
        return ResponseEntity.ok(testService.getAll());
    }

    @GetMapping(value = "/get-by-id/{testId}", produces = "application/json")
    public ResponseEntity<TestResponseDTO> getById(
            @PathVariable Long testId
    ) {
        return ResponseEntity.ok(testService.getById(testId));
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TestResponseDTO> create(
            @Valid @RequestBody TestRequestDTO testRequestDTO
    ) {
        return ResponseEntity.ok(testService.create(testRequestDTO));
    }

    @PutMapping(value = "/{testId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TestResponseDTO> update(
            @PathVariable Long testId,
            @Valid @RequestBody TestRequestDTO testRequestDTO
    ) {
        return ResponseEntity.ok(testService.update(testId, testRequestDTO));
    }

    @DeleteMapping(value = "/{testId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long testId
    ) {
        testService.delete(testId);
        return ResponseEntity.ok().build();
    }
}
