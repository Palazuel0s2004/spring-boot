package com.snpp.agenda_pediatrica.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.snpp.agenda_pediatrica.entity.Vacuna;
import com.snpp.agenda_pediatrica.service.VacunaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/vacunas")
@Tag(name = "Vacunas")
public class VacunaController {

    private final VacunaService vacunaService;

    public VacunaController(VacunaService vacunaService) {
        this.vacunaService = vacunaService;
    }

    @GetMapping
    public ResponseEntity<List<Vacuna>> listar() {
        return ResponseEntity.ok(vacunaService.listarTodas());
    }

    @PutMapping("/{id}/aplicar")
    @Operation(summary = "Marcar dosis como aplicada")
    public ResponseEntity<Vacuna> aplicar(@PathVariable Long id,
            @RequestParam(required = false) LocalDateTime fecha) {
        LocalDateTime fechaAplicacion = (fecha != null) ? fecha : LocalDateTime.now();
        return ResponseEntity.ok(vacunaService.aplicarVacuna(id, fechaAplicacion));
    }
}