package br.com.projeto.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.entities.Funcionario;

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
		Funcionario funcionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		Mockito.when(funcionarioDao.findById(funcionario.getId())).thenReturn(funcionario);		
		Mockito.doNothing().when(funcionarioDao).insert(funcionario);
		
		funcionarioService.insert(funcionario);
		
		funcionario = funcionarioDao.findById(funcionario.getId());
		
		Assertions.assertEquals(funcionario.getId(), 1);
		Assertions.assertEquals(funcionario.getNome(), "Luciana Clara Bernardes");
		Assertions.assertEquals(funcionario.getCpf(), "51722662751");
		Assertions.assertEquals(funcionario.getConselhoRegional(), "763461");
	}
	
	
		
}
