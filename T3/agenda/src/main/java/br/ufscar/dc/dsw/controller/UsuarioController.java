package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IConsultaService consultaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Usuario usuario) {
		return "usuario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuarios", usuarioService.buscarTodos());
		return "usuario/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "usuario/cadastro";
		}

		usuarioService.salvar(usuario);
		attr.addFlashAttribute("sucess", "Usuario inserido com sucesso");
		return "redirect:/usuarios/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", usuarioService.buscarPorId(id));
		return "usuario/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "usuario/cadastro";
		}

		usuarioService.salvar(usuario);
		attr.addFlashAttribute("sucess", "Usuario editado com sucesso.");
		return "redirect:/usuarios/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		usuarioService.excluir(id);
		attr.addFlashAttribute("sucess", "Usuario excluído com sucesso.");
		return "redirect:/usuarios/listar";
	}

	@ModelAttribute("consultas")
	public List<Consulta> listaConsultas() {
		return consultaService.buscarTodos();
	}
}
