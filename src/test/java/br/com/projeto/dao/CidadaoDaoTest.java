package br.com.projeto.dao;

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

import br.com.projeto.entities.Cidadao;

public class CidadaoDaoTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private CidadaoDao cidadaoDao;
	
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
		cidadaoDao = new CidadaoDao(entityManager);
	}
	
	@AfterEach
	public void tearDown() {
		entityManager.close();
	}
		
	@Test
	public void deveInserirNovoCidadao() {
		// Cenario
		Cidadao cidadao = new Cidadao(1, "Julio Renan Galvão", LocalDate.of(1990, 4, 21), "52789792917", 'M', "Rua Rubi, 169 - Guarapari/ES", "797564326757645");
		
		// Execução
		cidadaoDao.insert(cidadao);
		
		cidadao = cidadaoDao.findById(cidadao.getId());
		
		// Verificação
		Assertions.assertEquals(cidadao.getId(), 1);
		Assertions.assertEquals(cidadao.getNome(), "Julio Renan Galvão");
		Assertions.assertEquals(cidadao.getCpf(), "52789792917");
		Assertions.assertEquals(cidadao.getCartaoSus(), "797564326757645");
	}
        
        @Test
	public void retornaTrueSeCpfExiste() {
		// Cenario
		String cpf = "51722662759";
		Cidadao cidadao = new Cidadao(11, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), cpf, 'F', "Rua Felicidade, 984 - Rio Branco/AC", "12345678");
		cidadaoDao.insert(cidadao);		
		
		// Execução
		boolean exists = cidadaoDao.existsByCpf(cpf);
		
		// Verificação
		Assertions.assertTrue(exists);	
	}
        
        @Test
	public void retornaFalseSeCpfNaoExiste() {
		// Cenario
		String cpf = "51722662752";
		
		// Execução
		boolean exists = cidadaoDao.existsByCpf(cpf);
		
		System.out.println("Resultado: " + exists);
		// Verificação
		Assertions.assertFalse(exists);	
	}

}