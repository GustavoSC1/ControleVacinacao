package br.com.projeto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

}
