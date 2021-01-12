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

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Medico medico) {
		return "medico/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medicos",service.buscarTodos());
		return "medico/lista";
	}
	
	@GetMapping("/consulta")
	public String consultar(ModelMap model) {
		model.addAttribute("medicos",service.buscarTodos());
		return "medico/consulta";
	}
	
	@GetMapping("/especialidades")
	public String especialidades(ModelMap model) {
		model.addAttribute("medicos",service.buscarTodos());
		return "medico/especialidades";
	}


	@PostMapping("/salvar")
	public String salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "medico/cadastro";
		}

		service.salvar(medico);
		attr.addFlashAttribute("sucess", "Medico(a) inserido(a) com sucesso.");
		return "redirect:/medicos/listar";
	}

	@GetMapping("/editar/{crm}")
	public String preEditar(@PathVariable("crm") String crm, ModelMap model) {
		model.addAttribute("medico", service.buscarPorCrm(crm));
		return "medico/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "medico/cadastro";
		}

		service.salvar(medico);
		attr.addFlashAttribute("sucess", "Medico(a) editado(a) com sucesso.");
		return "redirect:/medicos/listar";
	}

	@GetMapping("/excluir/{crm}")
	public String excluir(@PathVariable("crm") String crm, ModelMap model) {
		service.excluir(crm);
		model.addAttribute("sucess", "Medico(a) excluído(a) com sucesso.");
		return listar(model);
	}
}
