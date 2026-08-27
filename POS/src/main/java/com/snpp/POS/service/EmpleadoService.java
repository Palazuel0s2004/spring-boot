package com.snpp.POS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snpp.POS.entity.Empleado;
import com.snpp.POS.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    @Autowired
    private  EmpleadoRepository empleadoRepository; 

    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));
    }

    public List<Empleado> listarPorCargo(String cargo) {
        return empleadoRepository.findByCargo(cargo);
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizar(Long id, Empleado datos) {
        Empleado empleado = obtenerPorId(id);
        empleado.setNombre(datos.getNombre());
        empleado.setApellido(datos.getApellido());
        empleado.setCargo(datos.getCargo());
        empleado.setSalario(datos.getSalario());
        empleado.setFechaContratacion(datos.getFechaContratacion());
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

}