package br.com.projeto.dao;

import javax.persistence.EntityManager;

import br.com.projeto.entities.Cidadao;

public class CidadaoDao {
	
private EntityManager entityManager;
	
	public CidadaoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(Cidadao cidadao) {
		entityManager.getTransaction().begin();
		entityManager.persist(cidadao);
		entityManager.getTransaction().commit();
	}
	
	public Cidadao findById(int id) {				
		Cidadao cidadao = entityManager.find(Cidadao.class, id);
		return cidadao;
	}

}
