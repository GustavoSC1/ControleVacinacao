package br.com.projeto.service;

import java.time.LocalDate;

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

}
