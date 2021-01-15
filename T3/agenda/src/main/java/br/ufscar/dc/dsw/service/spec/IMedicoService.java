package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import java.util.Optional;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;

public interface IMedicoService {

	Optional <Medico> buscarPorIdMedico(Long id);

	Medico buscarPorCrm(String crm);

	List<Medico> buscarTodos();

	void salvar(Medico medico);

	void excluir(String crm);
	
	void excluir(Long id);

}
