package br.com.projeto.service;

import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.entities.Funcionario;
import br.com.projeto.exception.BusinessException;

public class FuncionarioService {
	
	private FuncionarioDao funcionarioDao;
	
	public FuncionarioService(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}
	
	public void insert(Funcionario funcionario) {
		
		if(funcionarioDao.existsByCpf(funcionario.getCpf())) {
			throw new BusinessException("CPF já cadastrado");
		}
		
		funcionarioDao.insert(funcionario);
	}
	
	public Funcionario findById(int id) {		
		return funcionarioDao.findById(id);
	}

}
