package eus.birt.dam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Tarea {

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

	private boolean completada;

	@ManyToOne(optional = false)
	@JoinColumn(name = "proyecto_id", nullable = false)
	private Proyecto proyecto;

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

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		if (this.proyecto == proyecto) {
			return;
		}

		if (this.proyecto != null) {
			this.proyecto.getTareas().remove(this);
		}

		this.proyecto = proyecto;

		if (proyecto != null) {
			proyecto.getTareas().add(this);
		}
	}
}