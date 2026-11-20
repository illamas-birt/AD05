package eus.birt.dam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String nombre;

	@Size(max = 500)
	@Column(length = 500)
	private String descripcion;

	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
	private List<Tarea> tareas = new ArrayList<>();

	public Long getId() {
		return id;
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

	public List<Tarea> getTareas() {
		return tareas;
	}
}