package br.com.projeto.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projeto.dao.LoteDao;
import br.com.projeto.entities.Lote;
import br.com.projeto.exception.BusinessException;
import javax.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
public class LoteServiceTest {
	
	private LoteService loteService;
	
	@Mock
	private LoteDao loteDao;
	
	@BeforeEach
	public void setUp() {
		this.loteService = new LoteService(loteDao);
	}
	
	@Test
	public void deveInserirNovoLote() {
		// Cenario
		Lote lote  = new Lote(1, "Coronavac", "Instituto Butantan", "J202106025", LocalDate.of(2021, 10, 23), LocalDate.of(2021, 12, 23));
		Mockito.when(loteDao.findById(lote.getId())).thenReturn(lote);		
		Mockito.doNothing().when(loteDao).insert(lote);
		
		// Execução
		loteService.insert(lote);
		
		lote = loteService.findById(lote.getId());
		
		// Verificação
		Assertions.assertEquals(lote.getId(), 1);
		Assertions.assertEquals(lote.getNomeVacina(), "Coronavac");
		Assertions.assertEquals(lote.getInstituicao(), "Instituto Butantan");
		Assertions.assertEquals(lote.getNumeroLote(), "J202106025");
	}
        
        @Test
	public void naoDeveInserirComNumeroLoteDuplicado() {
		// Cenário
		Lote lote = new Lote(2, "Coronavac", "Instituto Butantan", "J202106025", LocalDate.of(2021, 10, 23), LocalDate.of(2021, 12, 23));
		Mockito.when(loteDao.existsByNumeroLote(lote.getNumeroLote())).thenReturn(true);	
		String mensagemEsperada = "Lote já cadastrado";
		
		// Execução
		Exception exception = Assertions.assertThrows(BusinessException.class, () -> loteService.insert(lote));	
				 
		String mensagemAtual = exception.getMessage();
		
		Assertions.assertTrue(mensagemAtual.contains(mensagemEsperada));
		
	}
        
        @Test
	public void naoDeveInserirNumeroLoteComDadosInsuficientes() {
		// Execução
		Exception exception = Assertions.assertThrows(ConstraintViolationException.class, () -> loteService.insert(new Lote()));	
		String mensagemAtual = exception.getMessage();

		Assertions.assertTrue(mensagemAtual.contains("O Nome da vacina não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O Nome da instituição não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O Número do Lote não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("A Data de Fabricação não pode ser vazia"));
                Assertions.assertTrue(mensagemAtual.contains("A Data de Validade não pode ser vazia"));
	}

}
