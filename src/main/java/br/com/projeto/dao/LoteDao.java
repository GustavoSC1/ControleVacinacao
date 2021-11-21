package br.com.projeto.dao;

import javax.persistence.EntityManager;

import br.com.projeto.entities.Lote;

public class LoteDao {
	private EntityManager entityManager;
	
	public LoteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(Lote funcionario) {
		entityManager.getTransaction().begin();
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();
	}
	
	public Lote findById(int id) {				
		Lote lote = entityManager.find(Lote.class, id);
		return lote;
	}
}
