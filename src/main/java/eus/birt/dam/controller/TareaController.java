package eus.birt.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import eus.birt.dam.entity.Tarea;
import eus.birt.dam.entity.Proyecto;
import eus.birt.dam.service.TareaService;
import eus.birt.dam.service.ProyectoService;

@Controller
@RequestMapping("/tareas")
public class TareaController {

	private final TareaService service;
	private final ProyectoService proyectoService;

	public TareaController(TareaService service, ProyectoService proyectoService) {
		this.service = service;
		this.proyectoService = proyectoService;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("tarea", new Tarea());
		return prepararPagina(model, null, "");
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Tarea tarea = service.buscar(id);

		model.addAttribute("tarea", tarea);

		return prepararPagina(model, id, tarea.getProyecto().getId().toString());
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("tarea") Tarea tarea, BindingResult errores,
			@RequestParam(required = false) Long registroId, @RequestParam(defaultValue = "") String proyectoId,
			Model model) {

		Proyecto proyecto = proyectoService.listar().stream().filter(p -> p.getId().toString().equals(proyectoId))
				.findFirst().orElse(null);

		if (proyecto == null) {
			errores.reject("proyecto.invalido", "Debes seleccionar un proyecto existente.");
		}

		if (errores.hasErrors()) {
			return prepararPagina(model, registroId, proyectoId);
		}

		service.guardar(registroId, tarea, proyecto.getId());

		return "redirect:/tareas";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return "redirect:/tareas";
	}

	private String prepararPagina(Model model, Long id, String proyectoId) {

		model.addAttribute("tareas", service.listar());
		model.addAttribute("proyectos", proyectoService.listar());
		model.addAttribute("registroId", id);
		model.addAttribute("proyectoId", proyectoId);

		return "tareas";
	}
}