package br.com.projeto.service;

import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.entities.Funcionario;

public class FuncionarioService {
	
	private FuncionarioDao funcionarioDao;
	
	public FuncionarioService(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}
	
	public void insert(Funcionario funcionario) {
		funcionarioDao.insert(funcionario);
	}
	
	public Funcionario findById(int id) {		
		return funcionarioDao.findById(id);
	}

}
