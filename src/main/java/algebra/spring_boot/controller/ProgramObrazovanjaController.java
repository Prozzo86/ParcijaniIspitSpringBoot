package algebra.spring_boot.controller;

import algebra.spring_boot.dto.CreateProgramObrazovanjaDto;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.service.ProgramObrazovanjaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programi")
public class ProgramObrazovanjaController {

    private final ProgramObrazovanjaService programService;

    public ProgramObrazovanjaController(ProgramObrazovanjaService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<List<ProgramObrazovanja>> fetchAll() {
        List<ProgramObrazovanja> programi = programService.findAll();
        return ResponseEntity.status(200).body(programi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramObrazovanja> findById(@PathVariable Long id) {
        Optional<ProgramObrazovanja> program = programService.findById(id);
        return program.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<ProgramObrazovanja> create(@Valid @RequestBody CreateProgramObrazovanjaDto dto) {
        ProgramObrazovanja program = programService.create(dto);
        return ResponseEntity.status(201).body(program);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        programService.delete(id);
        return ResponseEntity.status(204).build();
    }
}