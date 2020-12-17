package br.ufscar.dc.dsw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Consulta;

public class MedicoDAO extends UsuarioDAO {

	public List<Consulta> findbyMedico(Medico medico) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String jpql = "SELECT c FROM Consulta c where c.medico = :medico";
		TypedQuery<Consulta> q = em.createQuery(jpql, Consulta.class);
		q.setParameter("medico", medico);
		List<Consulta> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}
}
