package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Consulta;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface PacienteDAO extends CrudRepository<Paciente, Long> {

    Paciente save(Paciente paciente);

    List<Paciente> findAll();
    
//    List<Consultas> getConsultas();

    Paciente findByCpf(String cpf);

    void deleteByCpf(String cpf);
}
