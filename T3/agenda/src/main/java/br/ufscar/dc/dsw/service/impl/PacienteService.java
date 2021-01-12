package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;

import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {

	@Autowired
	PacienteDAO dao;

	public void salvar(Paciente paciente) {
		dao.save(paciente);
	}

	public void excluir(String cpf) {
		dao.deleteByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public Paciente buscarPorCpf(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public List<Paciente> buscarTodos() {
		return dao.findAll();
	}
}
