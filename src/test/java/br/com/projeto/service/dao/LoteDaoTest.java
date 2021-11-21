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

import br.com.projeto.dao.LoteDao;
import br.com.projeto.entities.Lote;

public class LoteDaoTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private LoteDao loteDao;
	
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
		loteDao = new LoteDao(entityManager);
	}
	
	@AfterEach
	public void tearDown() {
		entityManager.close();
	}
	
	@Test
	public void deveInserirNovoLote() {
		// Cenario
		Lote lote  = new Lote(1, "Coronavac", "Instituto Butantan", "J202106025", LocalDate.of(2021, 10, 23), LocalDate.of(2021, 12, 23));
		
		// Execução
		loteDao.insert(lote);

		lote = loteDao.findById(lote.getId());
		
		// Verificação
		Assertions.assertEquals(lote.getId(), 1);
		Assertions.assertEquals(lote.getNomeVacina(), "Coronavac");
		Assertions.assertEquals(lote.getInstituicao(), "Instituto Butantan");
		Assertions.assertEquals(lote.getNumeroLote(), "J202106025");
	}
	
}
