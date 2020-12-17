package br.ufscar.dc.dsw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends GenericDAO<Consulta> {

	@Override
	public Consulta find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Consulta consulta = em.find(Consulta.class, id);
		tx.commit();
		em.close();
		return consulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consulta> findAll() {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT c FROM Consulta c");
		List<Consulta> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}
	/*
	public List<Professor> findbyDepartamento(Departamento departamento) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String jpql = "SELECT p FROM Professor p where p.departamento = :departamento";
		TypedQuery<Professor> q = em.createQuery(jpql, Professor.class);
		q.setParameter("departamento", departamento);
		List<Professor> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}
*/
	@Override
	public void save(Consulta consulta) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(consulta);
		tx.commit();
		em.close();
	}

	@Override
	public void update(Consulta consulta) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(consulta);
		tx.commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Consulta consulta = em.getReference(Consulta.class, id);
		tx.begin();
		em.remove(consulta);
		tx.commit();
		em.close();
	}
}
