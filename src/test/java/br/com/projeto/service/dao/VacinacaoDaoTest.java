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

import br.com.projeto.dao.CidadaoDao;
import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.dao.LoteDao;
import br.com.projeto.dao.VacinacaoDao;
import br.com.projeto.entities.Cidadao;
import br.com.projeto.entities.Funcionario;
import br.com.projeto.entities.Lote;
import br.com.projeto.entities.Vacinacao;

public class VacinacaoDaoTest {

	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private VacinacaoDao vacinacaoDao;
	private FuncionarioDao funcionarioDao;
	private CidadaoDao cidadaoDao;
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
		vacinacaoDao = new VacinacaoDao(entityManager);
		funcionarioDao = new FuncionarioDao(entityManager);
		cidadaoDao = new CidadaoDao(entityManager);
		loteDao = new LoteDao(entityManager);
	}
	
	@AfterEach
	public void tearDown() {
		entityManager.close();
	}
	
	@Test
	public void deveInserirNovaVacinacao() {
		// Cenario
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		Cidadao cidadao = new Cidadao(1, "Julio Renan Galvão", LocalDate.of(1990, 4, 21), "52789792917", 'M', "Rua Rubi, 169 - Guarapari/ES", "797564326757645");
		Lote lote  = new Lote(1, "Coronavac", "Instituto Butantan", "J202106025", LocalDate.of(2021, 10, 23), LocalDate.of(2021, 12, 23));
		
		funcionarioDao.insert(funcionario);
		cidadaoDao.insert(cidadao);
		loteDao.insert(lote);
				
		Vacinacao vacinacao = new Vacinacao(1, "Posto de Saúde", "Primeira", LocalDate.of(2021, 10, 27), LocalDate.of(2021, 12, 15), cidadao, lote, funcionario);
		
		// Execução
		vacinacaoDao.insert(vacinacao);
		
		vacinacao = vacinacaoDao.findById(vacinacao.getId());
		
		// Verificação
		Assertions.assertEquals(vacinacao.getId(), 1);
		Assertions.assertEquals(vacinacao.getEstabelecimentoSaude(), "Posto de Saúde");
		Assertions.assertEquals(vacinacao.getDose(), "Primeira");
		Assertions.assertEquals(vacinacao.getFuncionario(), funcionario);
		Assertions.assertEquals(vacinacao.getCidadao(), cidadao);
		Assertions.assertEquals(vacinacao.getLote(), lote);
	}
}
