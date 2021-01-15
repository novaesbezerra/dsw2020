package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RestController()
public class MedicoController {

	@Autowired
	private IMedicoService service;

	@Autowired
	private IUsuarioService userService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Medico medico, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				medico.setId(((Integer) id).longValue());
			} else {
				medico.setId((Long) id);
			}
		}

		medico.setNome((String) json.get("nome"));
		medico.setCrm((String) json.get("crm"));
	}
	
	// GET http://localhost:8081/medicos/
		@GetMapping(path = "/medicos")
		public ResponseEntity<List<Medico>> lista() {
			List<Medico> lista = service.buscarTodos();
			if (lista.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(lista);
		}
		
		// DELETE http://localhost:8080/pacientes/{id}
		@DeleteMapping(path = "/medicos/{id}")
		public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

			Optional<Medico> medico = service.buscarPorIdMedico(id);
			if (medico == null) {
				return ResponseEntity.notFound().build();
			} else {
				service.excluir(id);
				return ResponseEntity.noContent().build();
			}
		}
		
		// POST http://localhost:8081/pacientes
		@PostMapping(path = "/medicos")
		@ResponseBody
		public ResponseEntity<Medico> cria(@RequestBody JSONObject json) {
			try {
				if (isJSONValid(json.toString())) {
					Medico medico = new Medico();
					parse(medico, json);
					service.salvar(medico);
					return ResponseEntity.ok(medico);
				} else {
					return ResponseEntity.badRequest().body(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
			}
		}
		
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
