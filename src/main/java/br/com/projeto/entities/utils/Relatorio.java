package br.com.projeto.entities.utils;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {
	
	private List<Vacina> vacinas = new ArrayList<>();
	
	private List<Dose> doses = new ArrayList<>();
	
	public Relatorio() {
		
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public List<Dose> getDoses() {
		return doses;
	}

	@Override
	public String toString() {
		return "Relatorio [vacinas=" + vacinas + ", doses=" + doses + "]";
	}
	
}
