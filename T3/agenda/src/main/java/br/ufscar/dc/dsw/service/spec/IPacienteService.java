package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Paciente;

public interface IPacienteService {

	Paciente buscarPorCpf(String cpf);

	List<Paciente> buscarTodos();

	void salvar(Paciente paciente);

	void excluir(String cpf);
}
