package br.com.projeto.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.projeto.entities.Funcionario;

public class FuncionarioServiceTest {
	
	private FuncionarioService funcionarioService = new FuncionarioService();
	
	@Test
	public void deveInserirNovoFuncionario() {
		Funcionario newFuncionario = new Funcionario(1, "Luciana Clara Bernardes", LocalDate.of(1980, 9, 23), "51722662751", 'F', "Rua Felicidade, 984 - Rio Branco/AC", "763461", "12345678");
		
		funcionarioService.insert(newFuncionario);
		
		Funcionario funcionario = funcionarioService.findById(newFuncionario.getId());
		
		Assertions.assertEquals(funcionario.getId(), 1);
		Assertions.assertEquals(funcionario.getNome(), "Luciana Clara Bernardes");
		Assertions.assertEquals(funcionario.getCpf(), "51722662751");
		Assertions.assertEquals(funcionario.getConselhoRegional(), "763461");
	}

}
