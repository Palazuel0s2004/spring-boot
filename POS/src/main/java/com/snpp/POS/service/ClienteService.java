package com.snpp.POS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snpp.POS.entity.Cliente;
import com.snpp.POS.repository.ClienteRepository;
import lombok.*;

@Service
@RequiredArgsConstructor
public class ClienteService {
	
	
	private final ClienteRepository clienteRepository;
	
	
	public ClienteService(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	public Cliente obtenerPorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
	}
	
	public Cliente actualizar(Long id, Cliente datos) {
		Cliente cliente = obtenerPorId(id);
        cliente.setNombre(datos.getNombre());
        cliente.setApellido(datos.getApellido());
        cliente.setDocumento(datos.getDocumento());
        cliente.setTelefono(datos.getTelefono());
        cliente.setEmail(datos.getEmail());
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

	public Cliente guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
