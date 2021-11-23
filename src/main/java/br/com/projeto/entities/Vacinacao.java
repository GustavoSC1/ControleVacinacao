package br.com.projeto.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vacinacao")
public class Vacinacao {
	
	@Id
	private int id;
	
	@NotEmpty(message = "O Nome do posto não pode ser vazio")
	private String estabelecimentoSaude;
	
	@NotEmpty(message = "A dose não pode ser vazia")
	private String dose;
	
	@NotNull(message = "A Data de vacinação não pode ser vazia")
	private LocalDate dataVacinacao;
	
	private LocalDate dataProximaDose;
	
	@ManyToOne
	@JoinColumn(name = "cidadao_id")
	@NotNull(message = "O cidadão não pode ser vazio")
	private Cidadao cidadao;
	
	@ManyToOne
	@JoinColumn(name = "lote_id")
	@NotNull(message = "O lote não pode ser vazio")
	private Lote lote;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	@NotNull(message = "O funcionario não pode ser vazio")
	private Funcionario funcionario;
	
	public Vacinacao() {
		
	}

	public Vacinacao(int id, String estabelecimentoSaude, String dose, LocalDate dataVacinacao,
			LocalDate dataProximaDose, Cidadao cidadao, Lote lote, Funcionario funcionario) {
		super();
		this.id = id;
		this.estabelecimentoSaude = estabelecimentoSaude;
		this.dose = dose;
		this.dataVacinacao = dataVacinacao;
		this.dataProximaDose = dataProximaDose;
		this.cidadao = cidadao;
		this.lote = lote;
		this.funcionario = funcionario;
	}

	public int getId() {
		return id;
	}

	public String getEstabelecimentoSaude() {
		return estabelecimentoSaude;
	}

	public String getDose() {
		return dose;
	}

	public LocalDate getDataVacinacao() {
		return dataVacinacao;
	}

	public LocalDate getDataProximaDose() {
		return dataProximaDose;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public Lote getLote() {
		return lote;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEstabelecimentoSaude(String estabelecimentoSaude) {
		this.estabelecimentoSaude = estabelecimentoSaude;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public void setDataVacinacao(LocalDate dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	public void setDataProximaDose(LocalDate dataProximaDose) {
		this.dataProximaDose = dataProximaDose;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
		Vacinacao other = (Vacinacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vacinacao [id=" + id + ", estabelecimentoSaude=" + estabelecimentoSaude + ", dose=" + dose
				+ ", dataVacinacao=" + dataVacinacao + ", dataProximaDose=" + dataProximaDose + ", cidadao=" + cidadao
				+ ", lote=" + lote + ", funcionario=" + funcionario + "]";
	}
	
}
