package eus.birt.dam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import eus.birt.dam.entity.Proyecto;
import eus.birt.dam.repository.ProyectoRepository;

@Service
public class ProyectoService {

	private final ProyectoRepository repository;

	public ProyectoService(ProyectoRepository repository) {
		this.repository = repository;
	}

	public List<Proyecto> listar() {
		return repository.findAll();
	}

	public Proyecto buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado"));
	}

	@Transactional
	public void guardar(Long id, Proyecto datos) {
		Proyecto proyecto = id == null ? new Proyecto() : buscar(id);

		proyecto.setNombre(datos.getNombre());
		proyecto.setDescripcion(datos.getDescripcion());

		repository.save(proyecto);
	}

	@Transactional
	public void eliminar(Long id) {
		repository.delete(buscar(id));
	}
}