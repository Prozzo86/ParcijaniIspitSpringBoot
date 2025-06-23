package algebra.spring_boot.controller;

import algebra.spring_boot.dto.CreateUpisDto;
import algebra.spring_boot.dto.UpdateProgramObrazovanjaDto;
import algebra.spring_boot.dto.UpdateUpisDto;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import algebra.spring_boot.service.UpisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/upisi")
public class UpisController {

    private final UpisService upisService;

    public UpisController(UpisService upisService) {
        this.upisService = upisService;
    }

    @GetMapping
    public ResponseEntity<List<Upis>> fetchAll() {
        List<Upis> upisi = upisService.findAll();
        return ResponseEntity.status(200).body(upisi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Upis> findById(@PathVariable Long id) {
        Optional<Upis> upis = upisService.findById(id);
        return upis.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Upis> update(@PathVariable Long id, @Valid @RequestBody UpdateUpisDto dto) {
        Upis upis = upisService.update(id, dto);
        return ResponseEntity.status(200).body(upis);
    }

    @PostMapping
    public ResponseEntity<Upis> create(@Valid @RequestBody CreateUpisDto dto) {
        Upis upis = upisService.create(dto);
        return ResponseEntity.status(201).body(upis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        upisService.delete(id);
        return ResponseEntity.status(204).build();
    }
}