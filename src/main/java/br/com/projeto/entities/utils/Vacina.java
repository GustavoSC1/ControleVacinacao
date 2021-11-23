package br.com.projeto.entities.utils;

public class Vacina {
	
	private String nomeVacina;
	private Integer numeroVacinados;
	
	public Vacina() {
		
	}
	
	public Vacina(String nomeVacina, Integer numeroVacinados) {
		super();
		this.nomeVacina = nomeVacina;
		this.numeroVacinados = numeroVacinados;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public Integer getNumeroVacinados() {
		return numeroVacinados;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public void setNumeroVacinados(Integer numeroVacinados) {
		this.numeroVacinados = numeroVacinados;
	}

	@Override
	public String toString() {
		return "Vacina [nomeVacina=" + nomeVacina + ", numeroVacinados=" + numeroVacinados + "]";
	}
		
}
