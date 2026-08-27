package com.snpp.POS.repository;

import com.snpp.POS.entity.TipoMovimiento;
import com.snpp.POS.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // --- Query methods derivados ---
    List<Venta> findByTipo(TipoMovimiento tipo);

    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Venta> findByEmpleadoIdAndTipo(Long empleadoId, TipoMovimiento tipo);

    List<Venta> findByClienteId(Long clienteId);

    List<Venta> findByTipoAndFechaBetweenOrderByFechaDesc(
            TipoMovimiento tipo, LocalDateTime inicio, LocalDateTime fin);

    // --- Reporte: Total por tipo de movimiento en un rango de fechas ---
    @Query("SELECT COALESCE(SUM(v.monto), 0) FROM Venta v " +
           "WHERE v.tipo = :tipo AND v.fecha BETWEEN :inicio AND :fin")
    BigDecimal totalPorTipoYFecha(@Param("tipo") TipoMovimiento tipo,
                                  @Param("inicio") LocalDateTime inicio,
                                  @Param("fin") LocalDateTime fin);

    // --- Reporte: Balance de caja (ventas + ingresos - egresos) ---
    @Query("SELECT COALESCE(SUM(CASE WHEN v.tipo = 'EGRESO' THEN -v.monto ELSE v.monto END), 0) " +
           "FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularBalance(@Param("inicio") LocalDateTime inicio,
                               @Param("fin") LocalDateTime fin);

    // --- Reporte: Productos más vendidos ---
    @Query("SELECT v.producto.nombre, SUM(v.cantidad) AS totalVendidos " +
           "FROM Venta v WHERE v.tipo = 'VENTA' AND v.producto IS NOT NULL " +
           "GROUP BY v.producto.nombre ORDER BY totalVendidos DESC")
    List<Object[]> productosMasVendidos();

    // --- Reporte: Total de ventas por empleado ---
    @Query("SELECT v.empleado.nombre, v.empleado.apellido, COALESCE(SUM(v.monto), 0) " +
           "FROM Venta v WHERE v.tipo = 'VENTA' " +
           "GROUP BY v.empleado.id, v.empleado.nombre, v.empleado.apellido")
    List<Object[]> totalVentasPorEmpleado();
    
   // --- Balance general sin rango de fechas ---
    @Query("SELECT COALESCE(SUM(CASE WHEN v.tipo = 'EGRESO' THEN -v.monto ELSE v.monto END), 0) FROM Venta v")
    BigDecimal calcularBalanceGeneral();

   // --- Total por método de pago ---
    @Query("SELECT v.metodoPago, COALESCE(SUM(v.monto), 0) FROM Venta v WHERE v.tipo = 'VENTA' AND v.metodoPago IS NOT NULL GROUP BY v.metodoPago")
    List<Object[]> totalPorMetodoPago();

}