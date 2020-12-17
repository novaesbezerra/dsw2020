package br.ufscar.dc.dsw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>{

	@Override
	public Usuario find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Usuario usuario = em.find(Usuario.class, id);
		tx.commit();
		em.close();
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public List<Usuario> findAll() {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
        Query q = em.createQuery("SELECT u FROM Usuario u");
        List<Usuario> lista = q.getResultList();
        tx.commit();
        em.close();
        return lista;
    }

    @Override
    public void save(Usuario usuario) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(usuario);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Usuario usuario) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(usuario);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Usuario usuario = em.getReference(Usuario.class, id);
        tx.begin();
        em.remove(usuario);
        tx.commit();
        em.close();
    }
}
