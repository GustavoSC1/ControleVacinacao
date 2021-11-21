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

}
