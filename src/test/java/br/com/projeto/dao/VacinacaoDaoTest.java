package br.com.projeto.dao;

import java.time.LocalDate;
import java.util.List;

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
import br.com.projeto.entities.Funcionario;
import br.com.projeto.entities.Lote;
import br.com.projeto.entities.Vacinacao;
import br.com.projeto.entities.utils.Relatorio;
import br.com.projeto.entities.utils.Vacina;

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
	
	@Test
	public void consultarVacinasDeUmCidadao() {
		// Cenario
		Funcionario funcionario = new Funcionario(5, "Sara Benedita Liz Rezende", LocalDate.of(1990, 8, 24), "01731464738", 'F', "Rua Nha Jorda, 441 - Itapecerica da Serra/SP", "757632", "12345678");
		Cidadao cidadao = new Cidadao(10, "Márcia Antonella Almeida", LocalDate.of(1979, 3, 13), "01165163560", 'F', "Rua Jacaraú, 983 - João Pessoa/PB", "797564876757091");
		Lote lote  = new Lote(6, "Coronavac", "Instituto Butantan", "J202110017", LocalDate.of(2021, 7, 19), LocalDate.of(2021, 9, 17));
		Lote lote2  = new Lote(7, "Coronavac", "Instituto Butantan", "J202107050", LocalDate.of(2021, 10, 2), LocalDate.of(2021, 12, 2));
		
		funcionarioDao.insert(funcionario);
		cidadaoDao.insert(cidadao);
		loteDao.insert(lote);
		loteDao.insert(lote2);
		
		Vacinacao vacinacao = new Vacinacao(2, "Posto de Saúde", "Primeira", LocalDate.of(2021, 8, 12), LocalDate.of(2021, 11, 12), cidadao, lote, funcionario);
		Vacinacao vacinacao2 = new Vacinacao(3, "Posto de Saúde", "Segunda", LocalDate.of(2021, 11, 12), null, cidadao, lote2, funcionario);
		
		// Execução
		vacinacaoDao.insert(vacinacao);
		vacinacaoDao.insert(vacinacao2);
		
		List<Vacinacao> vacinacoes = vacinacaoDao.findByCidadao(cidadao.getId());
		
		// Verificação
		Assertions.assertEquals(vacinacoes.size(), 2);
		
		Assertions.assertEquals(vacinacoes.get(0).getId(), 2);
		Assertions.assertEquals(vacinacoes.get(0).getEstabelecimentoSaude(), "Posto de Saúde");
		Assertions.assertEquals(vacinacoes.get(0).getDose(), "Primeira");
		Assertions.assertEquals(vacinacoes.get(0).getFuncionario(), funcionario);
		Assertions.assertEquals(vacinacoes.get(0).getCidadao(), cidadao);
		Assertions.assertEquals(vacinacoes.get(0).getLote(), lote);
		
		Assertions.assertEquals(vacinacoes.get(1).getId(), 3);
		Assertions.assertEquals(vacinacoes.get(1).getEstabelecimentoSaude(), "Posto de Saúde");
		Assertions.assertEquals(vacinacoes.get(1).getDose(), "Segunda");
		Assertions.assertEquals(vacinacoes.get(1).getFuncionario(), funcionario);
		Assertions.assertEquals(vacinacoes.get(1).getCidadao(), cidadao);
		Assertions.assertEquals(vacinacoes.get(1).getLote(), lote2);	
	}
	
	@Test
	public void deveGerarRelatorioVacinacao() {
		// Cenario
		/*
		entityManager.getTransaction().begin();
		String jpql = "delete from Vacinacao v";
		entityManager.createQuery(jpql).executeUpdate();
		entityManager.getTransaction().commit();
		*/
		Funcionario funcionario = new Funcionario(25, "Sara Benedita Liz Rezende", LocalDate.of(1990, 8, 24), "01731464738", 'F', "Rua Nha Jorda, 441 - Itapecerica da Serra/SP", "757632", "12345678");
		Cidadao cidadao = new Cidadao(25, "Márcia Antonella Almeida", LocalDate.of(1979, 3, 13), "01165163560", 'F', "Rua Jacaraú, 983 - João Pessoa/PB", "797564876757091");
		Cidadao cidadao2 = new Cidadao(26, "Cláudio Luiz Duarte", LocalDate.of(1966, 5, 6), "96449911689", 'M', "Rua Luiz Carrilho, 850 - Natal/RN", "778454876752652");
		Cidadao cidadao3 = new Cidadao(27, "Sara Cláudia Lorena Lopes", LocalDate.of(1949, 3, 4), "29776200109", 'F', "Rua do Paissandu, 443 - Caruaru/PE", "79798566754222");
		
		Lote lote  = new Lote(25, "Coronavac", "Instituto Butantan", "J202110017", LocalDate.of(2021, 7, 19), LocalDate.of(2021, 9, 19));
		Lote lote2  = new Lote(26, "Coronavac", "Instituto Butantan", "J202107050", LocalDate.of(2021, 10, 2), LocalDate.of(2021, 12, 2));
		
		Lote lote3  = new Lote(27, "AstraZeneca", "Fiocruz", "J202110017", LocalDate.of(2021, 6, 20), LocalDate.of(2021, 8, 20));
		Lote lote4  = new Lote(28, "AstraZeneca", "Fiocruz", "J202107050", LocalDate.of(2021, 9, 6), LocalDate.of(2021, 11, 6));
		
		Lote lote5  = new Lote(29, "Janssen", "Janssen", "J202110017", LocalDate.of(2021, 6, 5), LocalDate.of(2021, 8, 5));
		
		funcionarioDao.insert(funcionario);
		cidadaoDao.insert(cidadao);
		cidadaoDao.insert(cidadao2);
		cidadaoDao.insert(cidadao3);
		loteDao.insert(lote);
		loteDao.insert(lote2);
		loteDao.insert(lote3);
		loteDao.insert(lote4);
		loteDao.insert(lote5);
		
		Vacinacao vacinacao = new Vacinacao(25, "Posto de Saúde", "Primeira", LocalDate.of(2021, 8, 12), LocalDate.of(2021, 11, 12), cidadao, lote, funcionario);
		Vacinacao vacinacao2 = new Vacinacao(26, "Posto de Saúde", "Segunda", LocalDate.of(2021, 8, 12), null, cidadao, lote2, funcionario);
	
		Vacinacao vacinacao3 = new Vacinacao(27, "Posto de Saúde", "Primeira", LocalDate.of(2021, 7, 1), LocalDate.of(2021, 11, 12), cidadao2, lote3, funcionario);
		Vacinacao vacinacao4 = new Vacinacao(28, "Posto de Saúde", "Segunda", LocalDate.of(2021, 9, 15), null, cidadao2, lote4, funcionario);
	
		Vacinacao vacinacao5 = new Vacinacao(29, "Posto de Saúde", "Unica", LocalDate.of(2021, 6, 15), LocalDate.of(2021, 11, 12), cidadao3, lote5, funcionario);
		
		// Execução
		vacinacaoDao.insert(vacinacao);
		vacinacaoDao.insert(vacinacao2);
		vacinacaoDao.insert(vacinacao3);
		vacinacaoDao.insert(vacinacao4);
		vacinacaoDao.insert(vacinacao5);
		
		Relatorio relatorio = vacinacaoDao.getVaccinationReport();
		
		// Verificação
		System.out.println(relatorio);
		Assertions.assertEquals(relatorio.getVacinas().size(), 4);
		Assertions.assertEquals(relatorio.getDoses().size(), 3);
		
		for(Vacina v:relatorio.getVacinas()) {
			if(v.getNomeVacina() == "Coronavac") {
				Assertions.assertEquals(v.getNumeroVacinados(), 2);
			}else if(v.getNomeVacina() == "AstraZeneca") {
				Assertions.assertEquals(v.getNumeroVacinados(), 2);
			}else if(v.getNomeVacina() == "Pfizer") {
				Assertions.assertEquals(v.getNumeroVacinados(), 0);
			}else if(v.getNomeVacina() == "Janssen") {
				Assertions.assertEquals(v.getNumeroVacinados(), 1);
			}
		}
		
		for(Vacina v:relatorio.getVacinas()) {
			if(v.getNomeVacina() == "Primeira") {
				Assertions.assertEquals(v.getNumeroVacinados(), 2);
			}else if(v.getNomeVacina() == "Segunda") {
				Assertions.assertEquals(v.getNumeroVacinados(), 2);
			}else if(v.getNomeVacina() == "Unica") {
				Assertions.assertEquals(v.getNumeroVacinados(), 1);
			}
		}
	}
}
