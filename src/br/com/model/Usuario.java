package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private String user;
	private String senha;
	private String email;
	private String conf_senha;
	private String nome;
	private int permicao;
	private int admin;
	private Funcionario funcionario;

	// get e set
	
	
	
	public String getNome() {
		return nome;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public int getAdmin() {
		return admin;
	}


	public void setAdmin(int admin) {
		this.admin = admin;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPermicao() {
		return permicao;
	}

	public void setPermicao(int permicao) {
		this.permicao = permicao;
	}

	public String getConf_senha() {
		return conf_senha;
	}

	public void setConf_senha(String conf_senha) {
		this.conf_senha = conf_senha;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}