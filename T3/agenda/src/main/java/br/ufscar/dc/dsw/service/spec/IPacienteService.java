package br.ufscar.dc.dsw.service.spec;

//import br.ufscar.dc.dsw.promonstraorest.domain.dto.CreatePacienteDTO;

import java.util.List;
import java.util.Optional;

import br.ufscar.dc.dsw.domain.Paciente;

public interface IPacienteService {

	Optional<Paciente> buscarPorIdPaciente(Long id);

	List<Paciente> buscarTodos();

	Paciente salvar(Paciente paciente);

	void excluir(Long id);
}
