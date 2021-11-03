package br.com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Funcionario extends Pessoa {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private Departamento departamento;
	private Usuario usuario;
	private String sexo;
    private String cpf;
	private String cnh;
	@Temporal(TemporalType.DATE)
    private Date datnasc;

	
	//get e set
	
	public String getSexo() {
		return sexo;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Date getDatnasc() {
		return datnasc;
	}

	public void setDatnasc(Date datnasc) {
		this.datnasc = datnasc;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
