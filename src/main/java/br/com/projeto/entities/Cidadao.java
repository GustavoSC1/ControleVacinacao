package br.com.projeto.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cidadao")
public class Cidadao {
	
	@Id
	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	private char sexo;
	private String endereco;
	private String cartaoSus;
	
	public Cidadao() {
		
	}

	public Cidadao(int id, String nome, LocalDate dataNascimento, String cpf, char sexo, String endereco,
			String cartaoSus) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		this.cartaoSus = cartaoSus;
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

	public String getCartaoSus() {
		return cartaoSus;
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

	public void setCartaoSus(String cartaoSus) {
		this.cartaoSus = cartaoSus;
	}
	
}