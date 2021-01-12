package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Consulta;

@SuppressWarnings("unchecked")
public interface ConsultaDAO extends CrudRepository<Consulta, Long>{

	Consulta findById(long id);

	List<Consulta> findAll();

	Consulta save(Consulta consulta);

	void deleteById(Long id);
}