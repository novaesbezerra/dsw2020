package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Medico;

public interface IMedicoService {

	//Medico buscarPorId(Long id);

	Medico buscarPorCrm(String crm);

	List<Medico> buscarTodos();

	void salvar(Medico medico);

	void excluir(String crm);
}
