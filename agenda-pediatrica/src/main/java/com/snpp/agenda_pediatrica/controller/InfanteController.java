package com.snpp.agenda_pediatrica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpp.agenda_pediatrica.entity.Infante;
import com.snpp.agenda_pediatrica.service.InfanteService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/infantes")
@Tag(name = "Infantes")
public class InfanteController {

    private final InfanteService infanteService;

    public InfanteController(InfanteService infanteService) {
        this.infanteService = infanteService;
    }

    @PostMapping
    @Operation(summary = "Registrar infante y generar su esquema de vacunación")
    public ResponseEntity<Infante> registrar(@RequestBody Infante infante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(infanteService.registrarInfante(infante));
    }

    @GetMapping
    public ResponseEntity<List<Infante>> listar() {
        return ResponseEntity.ok(infanteService.listarInfantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Infante> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(infanteService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        infanteService.eliminarInfante(id);
        return ResponseEntity.noContent().build();
    }
}