package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface MedicoDAO extends CrudRepository<Medico, Long> {

    Medico save(Medico medico);

    List<Medico> findAll();
    
    @Override
    Optional<Medico> findById(Long id);

    Medico findByCrm(String crm);

    void deleteByCrm(String crm);
    
    void deleteById(Long id);

}
