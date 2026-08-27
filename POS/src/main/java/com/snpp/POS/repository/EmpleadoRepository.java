package com.snpp.POS.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.snpp.POS.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
	List<Empleado> findByCargo(String cargo);

}
