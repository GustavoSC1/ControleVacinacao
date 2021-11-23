package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.projeto.entities.Vacinacao;
import br.com.projeto.entities.utils.Dose;
import br.com.projeto.entities.utils.Relatorio;
import br.com.projeto.entities.utils.Vacina;

public class VacinacaoDao {
private EntityManager entityManager;
	
	public VacinacaoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(Vacinacao vacinacao) {
		entityManager.getTransaction().begin();
		entityManager.persist(vacinacao);
		entityManager.getTransaction().commit();
	}
	
	public Vacinacao findById(int id) {				
		Vacinacao vacinacao = entityManager.find(Vacinacao.class, id);
		return vacinacao;
	}
	
	public List<Vacinacao> findByCidadao(int id) {				
		String jpql = "select v from Vacinacao v where cidadao_id = :idCidadao";
		TypedQuery<Vacinacao> typedQuery = entityManager
						.createQuery(jpql, Vacinacao.class)
						.setParameter("idCidadao", id);
		
		List<Vacinacao>	vacinacoes = typedQuery.getResultList();	
		return vacinacoes;
	}
	
	public Relatorio getVaccinationReport() {
		String jpql = "select v from Vacinacao v";
		TypedQuery<Vacinacao> typedQuery = entityManager
				.createQuery(jpql, Vacinacao.class);
		List<Vacinacao>	vacinacoes = typedQuery.getResultList();
		
		Vacina coronavac = new Vacina("Coronavac", 0);
		Vacina pfizer = new Vacina("Pfizer", 0);
		Vacina astraZeneca = new Vacina("AstraZeneca", 0);
		Vacina janssen = new Vacina("Janssen", 0);
		Dose primeira = new Dose("Primeira", 0);
		Dose segunda = new Dose("Segunda", 0);
		Dose unica = new Dose("Unica", 0);
		
		for(Vacinacao v: vacinacoes) {
			if(v.getLote().getNomeVacina() == "Coronavac") {
				coronavac.setNumeroVacinados(coronavac.getNumeroVacinados() + 1);
			}else if(v.getLote().getNomeVacina() == "Pfizer"){
				pfizer.setNumeroVacinados(pfizer.getNumeroVacinados() + 1);
			}else if(v.getLote().getNomeVacina() == "AstraZeneca"){
				astraZeneca.setNumeroVacinados(astraZeneca.getNumeroVacinados() + 1);
			}else if(v.getLote().getNomeVacina() == "Janssen"){
				janssen.setNumeroVacinados(janssen.getNumeroVacinados() + 1);
			}
			
			if(v.getDose() == "Primeira") {
				primeira.setNumeroVacinados(primeira.getNumeroVacinados() + 1);
			}else if(v.getDose() == "Segunda") {
				segunda.setNumeroVacinados(segunda.getNumeroVacinados() + 1);
			}else if(v.getDose() == "Unica") {
				unica.setNumeroVacinados(unica.getNumeroVacinados() + 1);
			}
		}
		
		Relatorio relatorio =  new Relatorio();
		relatorio.getVacinas().add(coronavac);
		relatorio.getVacinas().add(pfizer);
		relatorio.getVacinas().add(astraZeneca);
		relatorio.getVacinas().add(janssen);
		relatorio.getDoses().add(primeira);
		relatorio.getDoses().add(segunda);
		relatorio.getDoses().add(unica);
		
		return relatorio;
	}

}
