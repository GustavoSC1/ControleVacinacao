package br.com.projeto.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.entities.Funcionario;

public class FuncionarioServiceTest {
		
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private FuncionarioDao funcionarioDao;
	private FuncionarioService funcionarioService;
	
	@BeforeAll
	public static void setupClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("VacinacaoPU");
	}
	
	@AfterAll
	public static void tearDownClass() {
		entityManagerFactory.close();
	}
	
	@BeforeEach
	public void setup() {
		entityManager = entityManagerFactory.createEntityManager();
		funcionarioDao = new FuncionarioDao(entityManager);
		funcionarioService = new FuncionarioService(funcionarioDao);
	}
	
	@AfterEach
	public void tearDown() {
		entityManager.close();
	}
	
	@Test
	public void deveInserirNovoFuncionario() {
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");

		funcionarioService.insert(funcionario);

		funcionario = funcionarioService.findById(funcionario.getId());

		Assertions.assertEquals(funcionario.getId(), 1);
		Assertions.assertEquals(funcionario.getNome(), "Luciana Clara Bernardes");
		Assertions.assertEquals(funcionario.getCpf(), "51722662751");
		Assertions.assertEquals(funcionario.getConselhoRegional(), "763461");
	}

}
