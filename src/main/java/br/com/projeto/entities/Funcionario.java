package br.com.projeto.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="funcionario")
public class Funcionario {
	
	@Id
	private int id;
	
	@NotEmpty(message = "O Nome não pode ser vazio")
	private String nome;
	
	@NotNull(message = "A Data de Nascimento não pode ser vazia")
	private LocalDate dataNascimento;
	
	@NotEmpty(message = "O CPF não pode ser vazio")
	private String cpf;
	
	@NotNull(message = "O Sexo não pode ser vazio")
	private char sexo;
	
	@NotEmpty(message = "O Endereço não pode ser vazio")
	private String endereco;
	
	@NotEmpty(message = "O Conselho Regional não pode ser vazio")
	private String conselhoRegional;
	
	@NotEmpty(message = "A Senha não pode ser vazia")
	private String senha;
	
	@OneToMany(mappedBy = "funcionario") 
	private List<Vacinacao> vacinacoes = new ArrayList<>();
	
	public Funcionario() {
		
	}

	public Funcionario(int id, String nome, LocalDate dataNascimento, String cpf, char sexo, String endereco,
			String conselhoRegional, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		this.conselhoRegional = conselhoRegional;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getConselhoRegional() {
		return conselhoRegional;
	}

	public String getSenha() {
		return senha;
	}

	public List<Vacinacao> getVacinacoes() {
		return vacinacoes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setConselhoRegional(String conselhoRegional) {
		this.conselhoRegional = conselhoRegional;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setVacinacoes(List<Vacinacao> vacinacoes) {
		this.vacinacoes = vacinacoes;
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
		Funcionario other = (Funcionario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf
				+ ", sexo=" + sexo + ", endereco=" + endereco + ", conselhoRegional=" + conselhoRegional + ", senha="
				+ senha + ", vacinacoes=" + vacinacoes + "]";
	}
		
}
