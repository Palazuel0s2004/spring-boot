package com.snpp.agenda_pediatrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.snpp.agenda_pediatrica.crud.VacunaCrudRepository;
import com.snpp.agenda_pediatrica.entity.Vacuna;

@Repository
public class VacunaRepository {

    private final VacunaCrudRepository crudRepository;

    public VacunaRepository(VacunaCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Vacuna save(Vacuna vacuna) {
        return crudRepository.save(vacuna);
    }

    public List<Vacuna> saveAll(List<Vacuna> vacunas) {
        return (List<Vacuna>) crudRepository.saveAll(vacunas);
    }

    public Optional<Vacuna> findById(Long id) {
        return crudRepository.findById(id);
    }

    public List<Vacuna> findAll() {
        return (List<Vacuna>) crudRepository.findAll();
    }
}