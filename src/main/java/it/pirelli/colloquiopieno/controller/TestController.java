package it.pirelli.colloquiopieno.controller;

import it.pirelli.colloquiopieno.dto.TestDTO;
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

    @GetMapping("/get-all")
    public ResponseEntity<List<TestDTO>> getAll() {
        return ResponseEntity.ok(testService.getAll());
    }

    @GetMapping("/get-by-id/{testId}")
    public ResponseEntity<TestDTO> getById(
            @PathVariable Long testId
    ) {
        return ResponseEntity.ok(testService.getById(testId));
    }

    @PostMapping("")
    public ResponseEntity<TestDTO> create(
            @Valid @RequestBody TestDTO testDTO
    ) {
        return ResponseEntity.ok(testService.create(testDTO));
    }

    @PutMapping("/{testId}")
    public ResponseEntity<TestDTO> update(
            @PathVariable Long testId,
            @Valid @RequestBody TestDTO testDTO
    ) {
        return ResponseEntity.ok(testService.update(testId, testDTO));
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long testId
    ) {
        testService.delete(testId);
        return ResponseEntity.ok().build();
    }
}
