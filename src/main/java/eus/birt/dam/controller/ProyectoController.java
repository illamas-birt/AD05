package eus.birt.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import eus.birt.dam.entity.Proyecto;
import eus.birt.dam.service.ProyectoService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

	private final ProyectoService service;

	public ProyectoController(ProyectoService service) {
		this.service = service;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("proyecto", new Proyecto());
		return prepararPagina(model, null);
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("proyecto", service.buscar(id));
		return prepararPagina(model, id);
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("proyecto") Proyecto proyecto, BindingResult errores,
			@RequestParam(required = false) Long registroId, Model model) {

		if (errores.hasErrors()) {
			return prepararPagina(model, registroId);
		}

		service.guardar(registroId, proyecto);

		return "redirect:/proyectos";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return "redirect:/proyectos";
	}

	private String prepararPagina(Model model, Long id) {
		model.addAttribute("proyectos", service.listar());
		model.addAttribute("registroId", id);
		return "proyectos";
	}
}