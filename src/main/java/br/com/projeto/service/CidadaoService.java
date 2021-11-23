package br.com.projeto.service;

import br.com.projeto.dao.CidadaoDao;
import br.com.projeto.entities.Cidadao;
import br.com.projeto.entities.Lote;
import br.com.projeto.exception.BusinessException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class CidadaoService {
	
	private CidadaoDao cidadaoDao;
        private Validator validator;
	
	public CidadaoService(CidadaoDao cidadaoDao) {
		this.cidadaoDao = cidadaoDao;
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	public void insert(Cidadao cidadao) {
	Set<ConstraintViolation<Cidadao>> violations = validator.validate(cidadao);
		
	if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Cidadao> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage() + " ");
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
            
        if(cidadaoDao.existsByCpf(cidadao.getCpf())){
            throw new BusinessException("CPF já cadastrado");
        }
        
        cidadaoDao.insert(cidadao);
	}
	
	public Cidadao findById(int id) {		
		return cidadaoDao.findById(id);
	}
        
        

}
