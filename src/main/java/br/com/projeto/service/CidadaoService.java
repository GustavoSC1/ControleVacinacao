package br.com.projeto.service;

import br.com.projeto.dao.CidadaoDao;
import br.com.projeto.entities.Cidadao;

public class CidadaoService {
	
	private CidadaoDao cidadaoDao;
	
	public CidadaoService(CidadaoDao cidadaoDao) {
		this.cidadaoDao = cidadaoDao;
	}
	
	public void insert(Cidadao funcionario) {
		cidadaoDao.insert(funcionario);
	}
	
	public Cidadao findById(int id) {		
		return cidadaoDao.findById(id);
	}

}
