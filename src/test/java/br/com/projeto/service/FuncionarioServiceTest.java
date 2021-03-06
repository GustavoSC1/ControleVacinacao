package br.com.projeto.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.validation.ConstraintViolationException;

import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.entities.Funcionario;
import br.com.projeto.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {
	
	private FuncionarioService funcionarioService;
	
	@Mock
	private FuncionarioDao funcionarioDao;
	
	@BeforeEach
	public void setUp() {
		this.funcionarioService = new FuncionarioService(funcionarioDao);
	}
	
	@Test
	public void deveInserirNovoFuncionario() {
		// Cenario
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		Mockito.when(funcionarioDao.findById(funcionario.getId())).thenReturn(funcionario);		
		Mockito.doNothing().when(funcionarioDao).insert(funcionario);
		
		// Execução
		funcionarioService.insert(funcionario);
		
		funcionario = funcionarioService.findById(funcionario.getId());
		
		// Verificação
		Assertions.assertEquals(funcionario.getId(), 1);
		Assertions.assertEquals(funcionario.getNome(), "Luciana Clara Bernardes");
		Assertions.assertEquals(funcionario.getCpf(), "51722662751");
		Assertions.assertEquals(funcionario.getConselhoRegional(), "763461");
	}
	
	@Test
	public void naoDeveInserirFuncionarioComCpfDuplicado() {
		// Cenário
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		Mockito.when(funcionarioDao.existsByCpf(funcionario.getCpf())).thenReturn(true);	
		String mensagemEsperada = "CPF já cadastrado";
		
		// Execução
		Exception exception = Assertions.assertThrows(BusinessException.class, () -> funcionarioService.insert(funcionario));	
				 
		String mensagemAtual = exception.getMessage();
		
		Assertions.assertTrue(mensagemAtual.contains(mensagemEsperada));
		
	}
	
	@Test
	public void naoDeveInserirFuncionarioComDadosInsuficientes() {
		// Execução
		Exception exception = Assertions.assertThrows(ConstraintViolationException.class, () -> funcionarioService.insert(new Funcionario()));	
		String mensagemAtual = exception.getMessage();

		Assertions.assertTrue(mensagemAtual.contains("O Nome não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O Endereço não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("A Senha não pode ser vazia"));
		Assertions.assertTrue(mensagemAtual.contains("O Conselho Regional não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("O CPF não pode ser vazio"));
		Assertions.assertTrue(mensagemAtual.contains("A Data de Nascimento não pode ser vazia"));
	}
	
	@Test
	public void realizaLoginSeCpfESenhaEstiveremCorretos() {
		// Cenario
		String cpf = "51722662759";
		String senha = "12345678";
		Mockito.when(funcionarioDao.login(cpf, senha)).thenReturn(true);	
		
		// Execução
		boolean loginSucesso = funcionarioService.login(cpf, senha);
		
		// Verificação
		Assertions.assertTrue(loginSucesso);	
	}
		
}
