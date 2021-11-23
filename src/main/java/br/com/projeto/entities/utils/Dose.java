package br.com.projeto.entities.utils;

public class Dose {
	
	private String nomeDose;
	private Integer numeroVacinados;
	
	public Dose() {
		
	}
	
	public Dose(String nomeDose, Integer numeroVacinados) {
		super();
		this.nomeDose = nomeDose;
		this.numeroVacinados = numeroVacinados;
	}

	public String getNomeDose() {
		return nomeDose;		
	}
	
	public Integer getNumeroVacinados() {
		return numeroVacinados;
	}
	
	public void setNomeDose(String nomeDose) {
		this.nomeDose = nomeDose;
	}
	
	public void setNumeroVacinados(Integer numeroVacinados) {
		this.numeroVacinados = numeroVacinados;
	}

	@Override
	public String toString() {
		return "Dose [nomeDose=" + nomeDose + ", numeroVacinados=" + numeroVacinados + "]";
	}
	
}
