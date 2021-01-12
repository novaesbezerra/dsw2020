package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;


public interface IConsultaService {

	Consulta buscarPorId(Long id);
	
//	List<Consulta> buscarPorPaciente(Paciente paciente);

	List<Consulta> buscarTodos();

	void salvar(Consulta consulta);

	void excluir(Long id);
}
