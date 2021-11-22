package br.com.projeto.service;

import br.com.projeto.dao.LoteDao;
import br.com.projeto.entities.Lote;
import br.com.projeto.exception.BusinessException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class LoteService {
	
	private LoteDao loteDao;
	
        private Validator validator;
        
	public LoteService(LoteDao loteDao) {
		this.loteDao = loteDao;
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	public void insert(Lote lote) {
        Set<ConstraintViolation<Lote>> violations = validator.validate(lote);
		
	if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Lote> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage() + " ");
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
            
        if(loteDao.existsByNumeroLote(lote.getNumeroLote())){
            throw new BusinessException("Lote já cadastrado");
        }
        
        loteDao.insert(lote);
        
	}
	
	public Lote findById(int id) {		
		return loteDao.findById(id);
	}

}
