package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.exception.EmailRepetido;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private final PacienteDAO pacienteDAO;

    private final UsuarioDAO usuarioDAO;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PacienteService(PacienteDAO pacienteDAO, UsuarioDAO usuarioDAO, BCryptPasswordEncoder passwordEncoder) {
        this.pacienteDAO = pacienteDAO;
        this.passwordEncoder = passwordEncoder;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        return pacienteDAO.save(paciente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarTodos() {
        return pacienteDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> buscarPorIdPaciente(Long id) {
        return pacienteDAO.findById(id);
    }

    @Override
    public void excluir(Long id) {
        pacienteDAO.deleteById(id);
    }
}
