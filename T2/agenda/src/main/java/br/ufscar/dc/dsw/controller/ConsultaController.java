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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Consulta consulta) {
		return "consulta/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("consultas",service.buscarTodos());
		return "consulta/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "consulta/cadastro";
		}

		service.salvar(consulta);
		attr.addFlashAttribute("sucess", "Consulta inserida com sucesso.");
		return "redirect:/consultas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("consulta", service.buscarPorId(id));
		return "consulta/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "consulta/cadastro";
		}

		service.salvar(consulta);
		attr.addFlashAttribute("sucess", "Consulta editada com sucesso.");
		return "redirect:/consultas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "Consulta excluída com sucesso.");
		return listar(model);
	}
}
