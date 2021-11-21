package br.com.projeto.service;

import br.com.projeto.dao.VacinacaoDao;
import br.com.projeto.entities.Vacinacao;

public class VacinacaoService {
	
private VacinacaoDao vacinacaoDao;
	
	public VacinacaoService(VacinacaoDao vacinacaoDao) {
		this.vacinacaoDao = vacinacaoDao;
	}
	
	public void insert(Vacinacao vacinacao) {
		vacinacaoDao.insert(vacinacao);
	}
	
	public Vacinacao findById(int id) {		
		return vacinacaoDao.findById(id);
	}

}
