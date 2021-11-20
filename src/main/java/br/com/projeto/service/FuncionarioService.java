package br.com.projeto.service;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;

import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.entities.Funcionario;
import br.com.projeto.exception.BusinessException;

public class FuncionarioService {
	
	private FuncionarioDao funcionarioDao;
	
	private Validator validator;
	
	public FuncionarioService(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	public void insert(Funcionario funcionario) {
		
		Set<ConstraintViolation<Funcionario>> violations = validator.validate(funcionario);
		
		if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Funcionario> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage() + " ");
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
		
		if(funcionarioDao.existsByCpf(funcionario.getCpf())) {
			throw new BusinessException("CPF já cadastrado");
		}
		
		funcionarioDao.insert(funcionario);
	}
	
	public Funcionario findById(int id) {		
		return funcionarioDao.findById(id);
	}

}
