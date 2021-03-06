package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService consultaService;
	
	@Autowired
	private IMedicoService medicoService;
	
	@Autowired
	private IPacienteService pacienteService;
	

	@GetMapping("/cadastrar")
	public String cadastrar(Consulta consulta) {
		return "consulta/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("consultas",consultaService.buscarTodos());
		return "consulta/lista";
	}
	
	@GetMapping("/minhasconsultas")
	public String listarMinhas(ModelMap model) {
		model.addAttribute("minhas",consultaService.buscarTodos());
		return "paciente/minhasconsultas";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "consulta/cadastro";
		}

		consultaService.salvar(consulta);
		attr.addFlashAttribute("sucess", "Consulta inserida com sucesso.");
		return "redirect:/consultas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("consulta", consultaService.buscarPorId(id));
		return "consulta/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "consulta/cadastro";
		}

		consultaService.salvar(consulta);
		attr.addFlashAttribute("sucess", "Consulta editada com sucesso.");
		return "redirect:/consultas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		consultaService.excluir(id);
		model.addAttribute("sucess", "Consulta excluída com sucesso.");
		return listar(model);
	}
	
	@ModelAttribute("medicos")
	public List<Medico> listaMedicos(){
		return medicoService.buscarTodos();
	}
	
	@ModelAttribute("pacientes")
	public List<Paciente> listaPacientes(){
		return pacienteService.buscarTodos();
	}
}
