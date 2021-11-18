package br.com.projeto.dao;

import javax.persistence.EntityManager;

import br.com.projeto.entities.Funcionario;

public class FuncionarioDao {	
	private EntityManager entityManager;
	
	public FuncionarioDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(Funcionario funcionario) {
		entityManager.getTransaction().begin();
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();
	}
	
	public Funcionario findById(int id) {				
		Funcionario funcionario = entityManager.find(Funcionario.class, id);
		return funcionario;
	}

}
