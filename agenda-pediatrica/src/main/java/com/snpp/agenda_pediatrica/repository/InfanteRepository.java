package com.snpp.agenda_pediatrica.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.snpp.agenda_pediatrica.crud.InfanteCrudRepository;
import com.snpp.agenda_pediatrica.entity.Infante;

@Repository
public class InfanteRepository {

    private final InfanteCrudRepository crudRepository;

    public InfanteRepository(InfanteCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Infante save(Infante infante) {
        return crudRepository.save(infante);
    }

    public Optional<Infante> findById(Long id) {
        return crudRepository.findById(id);
    }

    public List<Infante> findAll() {
        return (List<Infante>) crudRepository.findAll();
    }

    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }
}