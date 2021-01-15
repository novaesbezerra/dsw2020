package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {

	@Autowired
	MedicoDAO dao;

	public void salvar(Medico medico) {
		dao.save(medico);
	}

	public void excluir(String crm) {
		dao.deleteByCrm(crm);
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);
	}

    @Override
    @Transactional(readOnly = true)
    public Optional <Medico> buscarPorIdMedico(Long id) {
        return dao.findById(id);
    }
    
	@Transactional(readOnly = true)
	public Medico buscarPorCrm(String crm) {
		return dao.findByCrm(crm);
	}

	@Transactional(readOnly = true)
	public Optional <Medico> buscarPorEspecialidade(String especialidade) {
		return dao.findByEspecialidade(especialidade);
	}

	@Transactional(readOnly = true)
	public List<Medico> buscarTodos() {
		return dao.findAll();
	}
}
