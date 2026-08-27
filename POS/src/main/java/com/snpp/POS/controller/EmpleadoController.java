package com.snpp.POS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.snpp.POS.entity.Empleado;
import com.snpp.POS.service.EmpleadoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listar();
    }

    @GetMapping("/{id}")
    public Empleado obtener(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id);
    }

    @GetMapping("/cargo/{cargo}")
    public List<Empleado> listarPorCargo(@PathVariable String cargo) {
        return empleadoService.listarPorCargo(cargo);
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }
}