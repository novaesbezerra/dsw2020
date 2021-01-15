package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@CrossOrigin
@RestController()
public class PacienteController {

	@Autowired
	private IPacienteService service;

	@Autowired
	private IUsuarioService userService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Paciente paciente, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				paciente.setId(((Integer) id).longValue());
			} else {
				paciente.setId((Long) id);
			}
		}

		paciente.setNome((String) json.get("nome"));
		paciente.setCpf((String) json.get("cpf"));
		paciente.setSenha((String) json.get("senha"));
		paciente.setTelefone((String) json.get("telefone"));
		paciente.setSexo((String) json.get("sexo"));
		paciente.setNascimento((String) json.get("nascimento"));
		paciente.setEmail((String) json.get("email"));
	}

	// GET http://localhost:8080/pacientes/
	@GetMapping(path = "/pacientes")
	public ResponseEntity<List<Paciente>> lista() {
		List<Paciente> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	  // GET http://localhost:8081/pacientes/{id}
	 @GetMapping(path = "/pacientes/{id}")
	 public ResponseEntity<Paciente> lista(@PathVariable("id") long id) {
	 	Optional <Paciente> paciente = service.buscarPorIdPaciente(id);
	 	if (paciente == null) {
	 		return ResponseEntity.notFound().build();
	 	}
	 	return ResponseEntity.ok(paciente.get());
	 }

	// POST http://localhost:8080/pacientes
	@PostMapping(path = "/pacientes")
	@ResponseBody
	public ResponseEntity<Paciente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Paciente paciente = new Paciente();
				parse(paciente, json);
				service.salvar(paciente);
				return ResponseEntity.ok(paciente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	//PUT http://localhost:8080/pacientes/{id}
	@PutMapping(path = "/pacientes/{id}")
	public ResponseEntity<Paciente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Optional<Paciente> paciente = service.buscarPorIdPaciente(id);
				if (paciente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(paciente.get(), json);
					service.salvar(paciente.get());
					return ResponseEntity.ok(paciente.get());
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	// DELETE http://localhost:8080/pacientes/{id}
	@DeleteMapping(path = "/pacientes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Optional<Paciente> paciente = service.buscarPorIdPaciente(id);
		if (paciente == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
