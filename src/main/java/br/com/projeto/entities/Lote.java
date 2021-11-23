package br.com.projeto.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="lote")
public class Lote {
	
	@Id
	private int id;
        
        @NotEmpty(message = "O Nome da vacina não pode ser vazio")
	private String nomeVacina;
        
        @NotEmpty(message = "O Nome da instituição não pode ser vazio")
	private String instituicao;
        
        @NotEmpty(message = "O Número do Lote não pode ser vazio")
	private String numeroLote;
        
        @NotNull(message = "A Data de Fabricação não pode ser vazia")
	private LocalDate dataFabricacao;
        
        @NotNull(message = "A Data de Validade não pode ser vazia")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lote other = (Lote) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lote [id=" + id + ", nomeVacina=" + nomeVacina + ", instituicao=" + instituicao + ", numeroLote="
				+ numeroLote + ", dataFabricacao=" + dataFabricacao + ", dataValidade=" + dataValidade + "]";
	}	
	
}
