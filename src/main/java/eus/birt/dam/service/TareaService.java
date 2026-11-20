package eus.birt.dam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import eus.birt.dam.entity.Tarea;
import eus.birt.dam.entity.Proyecto;
import eus.birt.dam.repository.TareaRepository;

@Service
public class TareaService {

	private final TareaRepository repository;
	private final ProyectoService proyectoService;

	public TareaService(TareaRepository repository, ProyectoService proyectoService) {
		this.repository = repository;
		this.proyectoService = proyectoService;
	}

	public List<Tarea> listar() {
		return repository.findAll();
	}

	public Tarea buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada"));
	}

	@Transactional
	public void guardar(Long id, Tarea datos, Long proyectoId) {
		Proyecto proyecto = proyectoService.buscar(proyectoId);
		Tarea tarea = id == null ? new Tarea() : buscar(id);

		tarea.setNombre(datos.getNombre());
		tarea.setDescripcion(datos.getDescripcion());
		tarea.setCompletada(datos.isCompletada());
		tarea.setProyecto(proyecto);

		repository.save(tarea);
	}

	@Transactional
	public void eliminar(Long id) {
		Tarea tarea = buscar(id);

		tarea.setProyecto(null);
		repository.delete(tarea);
	}
}