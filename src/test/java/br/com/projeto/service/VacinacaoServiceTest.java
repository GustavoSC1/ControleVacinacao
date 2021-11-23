package br.com.projeto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projeto.dao.VacinacaoDao;
import br.com.projeto.entities.Cidadao;
import br.com.projeto.entities.Funcionario;
import br.com.projeto.entities.Lote;
import br.com.projeto.entities.Vacinacao;
import br.com.projeto.entities.utils.Dose;
import br.com.projeto.entities.utils.Relatorio;
import br.com.projeto.entities.utils.Vacina;

@ExtendWith(MockitoExtension.class)
public class VacinacaoServiceTest {
	
	private VacinacaoService vacinacaoService;
	
	@Mock
	private VacinacaoDao vacinacaoDao;
	
	@BeforeEach
	public void setUp() {
		this.vacinacaoService = new VacinacaoService(vacinacaoDao);
	}
	
	@Test
	public void deveInserirNovaVacinacao() {
		// Cenario
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		Cidadao cidadao = new Cidadao(1, "Julio Renan Galvão", LocalDate.of(1990, 4, 21), "52789792917", 'M', "Rua Rubi, 169 - Guarapari/ES", "797564326757645");
		Lote lote  = new Lote(1, "Coronavac", "Instituto Butantan", "J202106025", LocalDate.of(2021, 10, 23), LocalDate.of(2021, 12, 23));
		
		Vacinacao vacinacao = new Vacinacao(1, "Posto de Saúde", "Primeira", LocalDate.of(2021, 10, 27), LocalDate.of(2021, 12, 15), cidadao, lote, funcionario);
		
		Mockito.when(vacinacaoDao.findById(cidadao.getId())).thenReturn(vacinacao);		
		Mockito.doNothing().when(vacinacaoDao).insert(vacinacao);
		
		// Execução
		vacinacaoService.insert(vacinacao);
		
		vacinacao = vacinacaoService.findById(vacinacao.getId());
		
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
		
		Vacinacao vacinacao = new Vacinacao(2, "Posto de Saúde", "Primeira", LocalDate.of(2021, 8, 12), LocalDate.of(2021, 11, 12), cidadao, lote, funcionario);
		Vacinacao vacinacao2 = new Vacinacao(3, "Posto de Saúde", "Segunda", LocalDate.of(2021, 11, 12), null, cidadao, lote2, funcionario);
		
		List<Vacinacao> vacinacoes = new ArrayList<>();
		
		vacinacoes.add(vacinacao);
		vacinacoes.add(vacinacao2);
		
		Mockito.when(vacinacaoDao.findByCidadao(cidadao.getId())).thenReturn(vacinacoes);	
		
		// Execução
		vacinacoes = vacinacaoService.findByCidadao(cidadao.getId());
		
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
	public void naoDeveInserirVacinacaoComDadosInsuficientes() {
		// Execução
		Exception exception = Assertions.assertThrows(ConstraintViolationException.class, () -> vacinacaoService.insert(new Vacinacao()));		
		String mensagemAtual = exception.getMessage();

		Assertions.assertTrue(mensagemAtual.contains("O Nome do posto não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("A dose não pode ser vazia"));
		Assertions.assertTrue(mensagemAtual.contains("A Data de vacinação não pode ser vazia"));
		Assertions.assertTrue(mensagemAtual.contains("O cidadão não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O lote não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O funcionario não pode ser vazio"));
	}
	
	@Test
	public void deveGerarRelatorioVacinacao() {
		// Cenario
		Vacina vacina = new Vacina("Coronavac", 2);
		Vacina vacina2 = new Vacina("Pfizer", 0);
		Vacina vacina3 = new Vacina("AstraZeneca", 2);
		Vacina vacina4 = new Vacina("Janssen", 1);
		Dose dose = new Dose("Primeira", 2);
		Dose dose2 = new Dose("Segunda", 2);
		Dose dose3 = new Dose("Unica", 1);
				
		Relatorio relatorio = new Relatorio();
		relatorio.getVacinas().add(vacina);
		relatorio.getVacinas().add(vacina2);
		relatorio.getVacinas().add(vacina3);
		relatorio.getVacinas().add(vacina4);
		relatorio.getDoses().add(dose);
		relatorio.getDoses().add(dose2);
		relatorio.getDoses().add(dose3);
		
		Mockito.when(vacinacaoDao.getVaccinationReport()).thenReturn(relatorio);
		
		// Execução
		Relatorio relatorioResultado = vacinacaoService.getVaccinationReport();
		
		// Verificação
		Assertions.assertEquals(relatorio.getVacinas().size(), 4);
		Assertions.assertEquals(relatorio.getDoses().size(), 3);
		
		for(Vacina v:relatorioResultado.getVacinas()) {
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
		
		for(Vacina v:relatorioResultado.getVacinas()) {
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
