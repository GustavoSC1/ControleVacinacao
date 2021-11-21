package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public List<Vacinacao> findByCidadao(int id) {				
		String jpql = "select v from Vacinacao v where cidadao_id = :idCidadao";
		TypedQuery<Vacinacao> typedQuery = entityManager
						.createQuery(jpql, Vacinacao.class)
						.setParameter("idCidadao", id);
		
		List<Vacinacao>	vacinacoes = typedQuery.getResultList();	
		return vacinacoes;
	}

}
