package br.com.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.projeto.entities.Lote;

public class LoteDao {
	private EntityManager entityManager;
	
	public LoteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(Lote lote) {
		entityManager.getTransaction().begin();
		entityManager.persist(lote);
		entityManager.getTransaction().commit();
	}
	
	public Lote findById(int id) {				
		Lote lote = entityManager.find(Lote.class, id);
		return lote;
	}

	public boolean existsByNumeroLote(String numLote) {
		String jpql = "select count(f) > 0 from Lote f where numeroLote = :nLote";
		TypedQuery<Boolean> typedQuery = entityManager.createQuery(jpql, Boolean.class).setParameter("nLote", numLote); 
		boolean exists = typedQuery.getSingleResult();
		return exists;
	}

}
