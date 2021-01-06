package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface UsuarioDAO extends CrudRepository<Usuario, Long>{

	Usuario findById(long id);

	List<Usuario> findAll();

	Usuario save(Usuario usuario);

	void deleteById(Long id);
}