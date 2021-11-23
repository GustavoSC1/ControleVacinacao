package br.com.projeto.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.projeto.dao.VacinacaoDao;
import br.com.projeto.entities.Vacinacao;

public class VacinacaoService {
	
private VacinacaoDao vacinacaoDao;

private Validator validator;
	
	public VacinacaoService(VacinacaoDao vacinacaoDao) {
		this.vacinacaoDao = vacinacaoDao;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	public void insert(Vacinacao vacinacao) {
		Set<ConstraintViolation<Vacinacao>> violations = validator.validate(vacinacao);
		
		if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Vacinacao> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage() + " ");
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
		vacinacaoDao.insert(vacinacao);
	}
	
	public Vacinacao findById(int id) {		
		return vacinacaoDao.findById(id);
	}
	
	public List<Vacinacao> findByCidadao(int id) {		
		return vacinacaoDao.findByCidadao(id);
	}

}
