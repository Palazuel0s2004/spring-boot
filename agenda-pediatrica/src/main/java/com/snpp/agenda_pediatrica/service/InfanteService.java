package com.snpp.agenda_pediatrica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snpp.agenda_pediatrica.domain.EsquemaDosis;
import com.snpp.agenda_pediatrica.domain.EsquemaVacunacionPAI;
import com.snpp.agenda_pediatrica.entity.Infante;
import com.snpp.agenda_pediatrica.entity.Vacuna;
import com.snpp.agenda_pediatrica.repository.InfanteRepository;
import com.snpp.agenda_pediatrica.repository.VacunaRepository;
import jakarta.transaction.Transactional;

@Service
public class InfanteService {
	
	private final InfanteRepository infanteRepository;
	private final VacunaRepository vacunaRepository;
	
	public InfanteService(InfanteRepository infanteRepository, VacunaRepository vacunaRepository) {
		this.infanteRepository = infanteRepository;
		this.vacunaRepository = vacunaRepository;
	}
	
	@Transactional
	public Infante registrarInfante(Infante infante) {
		
		Infante infanteGuardado = infanteRepository.save(infante);

        List<Vacuna> vacunasGeneradas = EsquemaVacunacionPAI.ESQUEMA.stream()
                .map(dosis -> crearVacunaDesdeEsquema(dosis, infanteGuardado))
                .toList();

        vacunaRepository.saveAll(vacunasGeneradas);
        infanteGuardado.setVacunas(vacunasGeneradas);
        return infanteGuardado;
	}
	
	private Vacuna crearVacunaDesdeEsquema(EsquemaDosis dosis, Infante infante) {
        Vacuna vacuna = new Vacuna();
        vacuna.setNombre(dosis.nombre());
        vacuna.setDescripcion(dosis.descripcion());
        vacuna.setFechaEstimada(infante.getFechaNacimiento().plusDays(dosis.diasDesdeNacimiento()));
        vacuna.setInfante(infante);
        return vacuna;
	}
	
	public List<Infante> listarInfantes() {
		return infanteRepository.findAll();
	}
	
	public Infante buscarPorId(Long id) {
		return infanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Infante no encontrado: " + id));
	}
	
	public void eliminarInfante(Long id) {
        infanteRepository.deleteById(id);
	}
}
