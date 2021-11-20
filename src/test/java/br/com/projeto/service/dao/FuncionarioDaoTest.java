package br.com.projeto.service.dao;

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

public class FuncionarioDaoTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private FuncionarioDao funcionarioDao;
	
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
	}
	
	@AfterEach
	public void tearDown() {
		entityManager.close();
	}
	
	@Test
	public void deveInserirNovoFuncionario() {
		// Cenario
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		
		// Execução
		funcionarioDao.insert(funcionario);

		funcionario = funcionarioDao.findById(funcionario.getId());
		
		// Verificação
		Assertions.assertEquals(funcionario.getId(), 1);
		Assertions.assertEquals(funcionario.getNome(), "Luciana Clara Bernardes");
		Assertions.assertEquals(funcionario.getCpf(), "51722662751");
		Assertions.assertEquals(funcionario.getConselhoRegional(), "763461");
	}
	
	@Test
	public void retornaTrueSeCpfExiste() {
		// Cenario
		String cpf = "51722662759";
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), cpf, 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		funcionarioDao.insert(funcionario);
		
		// Execução
		boolean exists = funcionarioDao.existsByCpf(cpf);
		
		// Verificação
		Assertions.assertTrue(exists);	
	}
	
	@Test
	public void retornaFalseSeCpfNaoExiste() {
		// Cenario
		String cpf = "51722662752";
		
		// Execução
		boolean exists = funcionarioDao.existsByCpf(cpf);
		
		System.out.println("Resultado: " + exists);
		// Verificação
		Assertions.assertFalse(exists);	
	}

}
