package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.validation.Valid;
import java.util.List;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RestController()
public class ConsultaController {

	@Autowired
	private IConsultaService consultaService;

	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IPacienteService pacienteService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Consulta consulta, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				consulta.setId(((Integer) id).longValue());
			} else {
				consulta.setId((Long) id);
			}
		}

		consulta.setHora((String) json.get("hora"));
		consulta.setData((String) json.get("data"));
	}

	// GET http://localhost:8081/consultas/
	@GetMapping(path = "/consultas")
	public ResponseEntity<List<Consulta>> lista() {
		List<Consulta> lista = consultaService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	  // GET http://localhost:8081/consultas/{id}
	 @GetMapping(path = "/consultas/{id}")
	 public ResponseEntity<Consulta> lista(@PathVariable("id") long id) {
	 	Consulta consulta = consultaService.buscarPorId(id);
	 	if (consulta == null) {
	 		return ResponseEntity.notFound().build();
	 	}
	 	return ResponseEntity.ok(consulta);
	 }

	// GET http://localhost:8081/consultas/medicos/{id}
	@GetMapping(path = "/consultas/medicos/{id}")
	public ResponseEntity<List<Consulta>> lista_por_medico(@PathVariable("id") Long id) {
		Optional <Medico> medico = medicoService.buscarPorIdMedico(id);
		List<Consulta> lista = consultaService.buscarPorMedico(medico.get());
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	// GET http://localhost:8081/consultas/pacientes/{id}
	@GetMapping(path = "/consultas/pacientes/{id}")
	public ResponseEntity<List<Consulta>> lista_por_paciente(@PathVariable("id") Long id) {
		Optional <Paciente> paciente = pacienteService.buscarPorIdPaciente(id);
		List<Consulta> lista = consultaService.buscarPorPaciente(paciente.get());
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	// DELETE http://localhost:8081/consultas/{id}
	@DeleteMapping(path = "/consultas/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Consulta consulta = consultaService.buscarPorId(id);
		if (consulta == null) {
			return ResponseEntity.notFound().build();
		} else {
			consultaService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}

	// POST http://localhost:8081/consultas
	@PostMapping(path = "/consultas")
	@ResponseBody
	public ResponseEntity<Consulta> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Consulta consulta = new Consulta();
				parse(consulta, json);
				consultaService.salvar(consulta);
				return ResponseEntity.ok(consulta);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	//PUT http://localhost:8081/consultas/{id}
	@PutMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Consulta consulta = consultaService.buscarPorId(id);
				if (consulta == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(consulta, json);
					consultaService.salvar(consulta);
					return ResponseEntity.ok(consulta);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
}
