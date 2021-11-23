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
import br.com.projeto.exception.BusinessException;
import javax.validation.ConstraintViolationException;

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
		// Cenario
		Cidadao cidadao = new Cidadao(1, "Julio Renan Galvão", LocalDate.of(1990, 4, 21), "52789792917", 'M', "Rua Rubi, 169 - Guarapari/ES", "797564326757645");
		Mockito.when(cidadaoDao.findById(cidadao.getId())).thenReturn(cidadao);		
		Mockito.doNothing().when(cidadaoDao).insert(cidadao);
		
		// Execução
		cidadaoService.insert(cidadao);
		
		cidadao = cidadaoService.findById(cidadao.getId());
		
		// Verificação
		Assertions.assertEquals(cidadao.getId(), 1);
		Assertions.assertEquals(cidadao.getNome(), "Julio Renan Galvão");
		Assertions.assertEquals(cidadao.getCpf(), "52789792917");
		Assertions.assertEquals(cidadao.getCartaoSus(), "797564326757645");
	}
        
        @Test
        public void naoDeveInserirCidadaoComCpfDuplicado() {
        // Cenário
        Cidadao cidadao = new Cidadao(7, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "12345678");
        Mockito.when(cidadaoDao.existsByCpf(cidadao.getCpf())).thenReturn(true);
        String mensagemEsperada = "CPF já cadastrado";

        // Execução
        Exception exception = Assertions.assertThrows(BusinessException.class, () -> cidadaoService.insert(cidadao));

        String mensagemAtual = exception.getMessage();

        Assertions.assertTrue(mensagemAtual.contains(mensagemEsperada));

    }
        
        @Test
	public void naoDeveInserirCidadaoComDadosInsuficientes() {
		// Execução
		Exception exception = Assertions.assertThrows(ConstraintViolationException.class, () -> cidadaoService.insert(new Cidadao()));	
		String mensagemAtual = exception.getMessage();

		Assertions.assertTrue(mensagemAtual.contains("O Nome não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("A Data de Nascimento não pode ser vazia"));
		Assertions.assertTrue(mensagemAtual.contains("O CPF não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O Endereço não pode ser vazio"));
                Assertions.assertTrue(mensagemAtual.contains("O Cartão Sus não pode ser vazio"));
	}
        
    

}
