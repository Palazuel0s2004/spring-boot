package com.snpp.POS.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpp.POS.entity.*;
import com.snpp.POS.repository.*;

@Service
@Transactional(readOnly = true)
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ProductoRepository productoRepository;

    public VentaService(
            VentaRepository ventaRepository,
            ClienteRepository clienteRepository,
            EmpleadoRepository empleadoRepository,
            ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Venta registrarVenta(Long clienteId, Long empleadoId, Long productoId, Integer cantidad, String metodoPago) {
        // NUEVO: Desafío 3 - Validar cantidad
        if (cantidad == null || cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
        }

        Cliente cliente = clienteId != null
                ? clienteRepository.findById(clienteId).orElse(null)
                : null;

        BigDecimal monto = producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        Venta venta = new Venta();
        venta.setTipo(TipoMovimiento.VENTA);
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setMonto(monto);
        venta.setMetodoPago(metodoPago); 
        venta.setDescripcion("Venta de " + cantidad + " x " + producto.getNombre());
        venta.setFecha(LocalDateTime.now());

        return ventaRepository.save(venta);
    }

    // CORRECCIÓN: Se implementaron correctamente Ingreso y Egreso
    @Transactional
    public Venta registrarIngreso(Long empleadoId, BigDecimal monto, String descripcion) {
        return registrarMovimientoCaja(empleadoId, monto, descripcion, TipoMovimiento.INGRESO);
    }

    @Transactional
    public Venta registrarEgreso(Long empleadoId, BigDecimal monto, String descripcion) {
        return registrarMovimientoCaja(empleadoId, monto, descripcion, TipoMovimiento.EGRESO);
    }

    private Venta registrarMovimientoCaja(Long empleadoId, BigDecimal monto, String descripcion, TipoMovimiento tipo) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Venta movimiento = new Venta();
        movimiento.setTipo(tipo);
        movimiento.setEmpleado(empleado);
        movimiento.setMonto(monto);
        movimiento.setDescripcion(descripcion);
        movimiento.setFecha(LocalDateTime.now());

        return ventaRepository.save(movimiento);
    }

    // ... (Mantén listarPorTipo, calcularTotalPorTipo, etc. que ya tenías) ...
    public List<Venta> listarPorTipo(TipoMovimiento tipo) { return ventaRepository.findByTipo(tipo); }
    public List<Venta> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) { return ventaRepository.findByFechaBetween(inicio, fin); }
    public BigDecimal calcularTotalPorTipo(TipoMovimiento tipo, LocalDateTime inicio, LocalDateTime fin) { return ventaRepository.totalPorTipoYFecha(tipo, inicio, fin); }
    public BigDecimal calcularBalanceCaja(LocalDateTime inicio, LocalDateTime fin) { return ventaRepository.calcularBalance(inicio, fin); }
    public List<Object[]> obtenerProductosMasVendidos() { return ventaRepository.productosMasVendidos(); }
    public List<Object[]> obtenerTotalVentasPorEmpleado() { return ventaRepository.totalVentasPorEmpleado(); }

    // NUEVO: Desafíos 2 y 4
    public BigDecimal calcularBalanceGeneral() {
        return ventaRepository.calcularBalanceGeneral();
    }

    public List<Object[]> obtenerTotalPorMetodoPago() {
        return ventaRepository.totalPorMetodoPago();
    }
}