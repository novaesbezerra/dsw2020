package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.dto.CreatePacienteDTO;
import br.ufscar.dc.dsw.domain.dto.EditPacienteDTO;
import java.util.List;
import java.util.Optional;
import br.ufscar.dc.dsw.exception.EmailRepetido;

import br.ufscar.dc.dsw.domain.Paciente;

public interface IPacienteService {

	Optional<Paciente> buscarPorIdPaciente(Long id);

	List<Paciente> buscarTodos();

	Paciente update(EditPacienteDTO paciente);

    Paciente create(CreatePacienteDTO paciente) throws EmailRepetido;

	Paciente salvar(Paciente paciente);

	void excluir(Long id);
}
