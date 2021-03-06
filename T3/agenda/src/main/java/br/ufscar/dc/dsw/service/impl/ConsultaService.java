package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

	@Autowired
	ConsultaDAO daoConsulta;

	@Autowired
	MedicoDAO daoMedico;

	@Autowired
	PacienteDAO daoPaciente;

	public void salvar(Consulta consulta) {
		daoConsulta.save(consulta);
	}

	public void excluir(Long id) {
		daoConsulta.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Consulta buscarPorId(Long id) {
		return daoConsulta.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPorMedico(Medico medico) {
		return medico.getConsultas();
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPorPaciente(Paciente paciente) {
		return paciente.getConsultas();
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarTodos() {
		return daoConsulta.findAll();
	}
}
