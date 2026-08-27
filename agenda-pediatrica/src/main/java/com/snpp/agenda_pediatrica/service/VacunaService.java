package com.snpp.agenda_pediatrica.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.snpp.agenda_pediatrica.entity.Vacuna;
import com.snpp.agenda_pediatrica.repository.VacunaRepository;
import jakarta.transaction.Transactional;

@Service
public class VacunaService {

    private final VacunaRepository vacunaRepository;

    public VacunaService(VacunaRepository vacunaRepository) {
        this.vacunaRepository = vacunaRepository;
    }

    public List<Vacuna> listarTodas() {
        return vacunaRepository.findAll();
    }

    @Transactional
    public Vacuna aplicarVacuna(Long idVacuna, LocalDateTime fechaAplicacion) {
        Vacuna vacuna = vacunaRepository.findById(idVacuna)
                .orElseThrow(() -> new IllegalArgumentException("Vacuna no encontrada: " + idVacuna));
        vacuna.setFechaAplicacion(fechaAplicacion);
        return vacunaRepository.save(vacuna);
    }
}