package algebra.spring_boot.controller;

import algebra.spring_boot.dto.CreatePolaznikDto;
import algebra.spring_boot.dto.UpdatePolaznikDto;
import algebra.spring_boot.model.Polaznik;
import algebra.spring_boot.service.PolaznikService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/polaznici")
public class PolaznikController {

    private final PolaznikService polaznikService;

    public PolaznikController(PolaznikService polaznikService) {
        this.polaznikService = polaznikService;
    }

    @GetMapping
    public ResponseEntity<List<Polaznik>> fetchAll() {
        List<Polaznik> polaznici = polaznikService.fetchAll();
        List<String> polaznikNamesFromForLoop = new ArrayList<>();
        for (Polaznik polaznik : polaznici) {
            polaznikNamesFromForLoop.add(polaznik.getIme());
        }
        List<String> polaznikNames = polaznici.stream()
                .map(Polaznik::getIme)
                .toList();
        return ResponseEntity.status(200).body(polaznici);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Polaznik> findById(@PathVariable Long id) {
        Optional<Polaznik> polaznik = polaznikService.findById(id);
        return polaznik.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<Polaznik> create(@Valid @RequestBody CreatePolaznikDto dto) {
        Polaznik polaznik = polaznikService.create(dto);
        return ResponseEntity.status(201).body(polaznik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Polaznik> update(@PathVariable Long id, @Valid @RequestBody UpdatePolaznikDto dto) {
        Polaznik polaznik = polaznikService.update(id, dto);
        return ResponseEntity.status(200).body(polaznik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        polaznikService.delete(id);
        return ResponseEntity.status(204).build();
    }
}