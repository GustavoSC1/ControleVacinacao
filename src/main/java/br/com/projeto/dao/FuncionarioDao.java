package br.com.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.projeto.entities.Funcionario;

public class FuncionarioDao {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public void insert(Funcionario funcionario) {
		entityManagerFactory = Persistence.createEntityManagerFactory("VacinacaoPU");
		entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(funcionario);
		entityManager.getTransaction().commit();
 
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public Funcionario findById(int id) {		
		entityManagerFactory = Persistence.createEntityManagerFactory("VacinacaoPU");
		entityManager = entityManagerFactory.createEntityManager();
		
		Funcionario funcionario = entityManager.find(Funcionario.class, id);
		  		 
		entityManager.close();
		entityManagerFactory.close();
		  
		return funcionario;
	}

}
