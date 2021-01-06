package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Medico;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface MedicoDAO extends CrudRepository<Medico, Long> {

    Medico save(Medico medico);

    List<Medico> findAll();

    Optional<Medico> findById(Long id);

    void deleteById(Long id);
}
