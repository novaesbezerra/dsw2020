package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Paciente paciente) {
		return "paciente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("pacientes",service.buscarTodos());
		return "paciente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "paciente/cadastro";
		}

		service.salvar(paciente);
		attr.addFlashAttribute("sucess", "Paciente inserido com sucesso.");
		return "redirect:/pacientes/listar";
	}

	@GetMapping("/editar/{cpf}")
	public String preEditar(@PathVariable("cpf") String cpf, ModelMap model) {
		model.addAttribute("paciente", service.buscarPorCpf(cpf));
		return "paciente/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "paciente/cadastro";
		}

		service.salvar(paciente);
		attr.addFlashAttribute("sucess", "Paciente editado com sucesso.");
		return "redirect:/pacientes/listar";
	}

	@GetMapping("/excluir/{cpf}")
	public String excluir(@PathVariable("cpf") String cpf, ModelMap model) {
		service.excluir(cpf);
		model.addAttribute("sucess", "Paciente excluído com sucesso.");
		return listar(model);
	}
}
