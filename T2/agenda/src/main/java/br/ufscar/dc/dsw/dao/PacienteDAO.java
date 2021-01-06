package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Paciente;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface PacienteDAO extends CrudRepository<Paciente, Long> {

    Paciente save(Paciente paciente);

    List<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    void deleteById(Long id);
}
