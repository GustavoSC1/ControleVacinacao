package br.com.projeto.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projeto.dao.CidadaoDao;
import br.com.projeto.entities.Cidadao;

@ExtendWith(MockitoExtension.class)
public class CidadaoServiceTest {
	
	private CidadaoService cidadaoService;
	
	@Mock
	private CidadaoDao cidadaoDao;
	
	@BeforeEach
	public void setUp() {
		this.cidadaoService = new CidadaoService(cidadaoDao);
	}
	
	@Test
	public void deveInserirNovoCidadao() {
		Cidadao cidadao = new Cidadao(1, "Julio Renan Galvão", LocalDate.of(1990, 4, 21), "52789792917", 'M', "Rua Rubi, 169 - Guarapari/ES", "797564326757645");
		Mockito.when(cidadaoDao.findById(cidadao.getId())).thenReturn(cidadao);		
		Mockito.doNothing().when(cidadaoDao).insert(cidadao);
		
		
		cidadaoService.insert(cidadao);
		
		cidadao = cidadaoService.findById(cidadao.getId());
		
		Assertions.assertEquals(cidadao.getId(), 1);
		Assertions.assertEquals(cidadao.getNome(), "Julio Renan Galvão");
		Assertions.assertEquals(cidadao.getCpf(), "52789792917");
		Assertions.assertEquals(cidadao.getCartaoSus(), "797564326757645");
	}

}
