package br.com.projeto.dao;

import javax.persistence.EntityManager;

import br.com.projeto.entities.Vacinacao;

public class VacinacaoDao {
private EntityManager entityManager;
	
	public VacinacaoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(Vacinacao vacinacao) {
		entityManager.getTransaction().begin();
		entityManager.persist(vacinacao);
		entityManager.getTransaction().commit();
	}
	
	public Vacinacao findById(int id) {				
		Vacinacao vacinacao = entityManager.find(Vacinacao.class, id);
		return vacinacao;
	}

}
