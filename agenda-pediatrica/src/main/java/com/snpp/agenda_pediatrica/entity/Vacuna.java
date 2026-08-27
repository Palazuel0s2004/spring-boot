package com.snpp.agenda_pediatrica.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vacunas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacuna {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaEstimada() {
		return fechaEstimada;
	}

	public void setFechaEstimada(LocalDateTime fechaEstimada) {
		this.fechaEstimada = fechaEstimada;
	}

	public LocalDateTime getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(LocalDateTime fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Infante getInfante() {
		return infante;
	}

	public void setInfante(Infante infante) {
		this.infante = infante;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vacuna")
	private Long id;
	private String nombre;
	private String descripcion;
	
	@Column(name = "fecha_estimada")
	private LocalDateTime fechaEstimada;
	
	@Column(name = "fecha_Aplicacion")
	private LocalDateTime fechaAplicacion;
	
	@ManyToOne
	@JoinColumn(name = "id_infante")
	@JsonBackReference
	private Infante infante;

}
