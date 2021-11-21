package br.com.projeto.service;

import br.com.projeto.dao.LoteDao;
import br.com.projeto.entities.Lote;

public class LoteService {
	
	private LoteDao loteDao;
	
	public LoteService(LoteDao loteDao) {
		this.loteDao = loteDao;
	}
	
	public void insert(Lote lote) {
		loteDao.insert(lote);
	}
	
	public Lote findById(int id) {		
		return loteDao.findById(id);
	}

}
