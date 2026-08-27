package com.snpp.POS.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.snpp.POS.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByDocumento(String documento);
	List<Cliente> findByApellidoContainingIgnoreCase(String apellido);

}
