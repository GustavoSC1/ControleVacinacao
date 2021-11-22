package br.com.projeto.dao;

import javax.persistence.EntityManager;

import br.com.projeto.entities.Cidadao;
import javax.persistence.TypedQuery;

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
        
        public boolean existsByCpf(String cpf) {
		String jpql = "select count(f) > 0 from Cidadao f where cpf = :cpfCidadao";
		TypedQuery<Boolean> typedQuery = entityManager.createQuery(jpql, Boolean.class).setParameter("cpfCidadao", cpf); 
		boolean exists = typedQuery.getSingleResult();
		return exists;
	}

}
