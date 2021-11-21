package br.com.projeto.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lote")
public class Lote {
	
	@Id
	private int id;
	private String nomeVacina;
	private String instituicao;
	private String numeroLote;
	private LocalDate dataFabricacao;
	private LocalDate dataValidade;
	
	public Lote() {
		
	}

	public Lote(int id, String nomeVacina, String instituicao, String numeroLote, LocalDate dataFabricacao,
			LocalDate dataValidade) {
		super();
		this.id = id;
		this.nomeVacina = nomeVacina;
		this.instituicao = instituicao;
		this.numeroLote = numeroLote;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
	}

	public int getId() {
		return id;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
