package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sexo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_sexo;
	private String tipo;

	public int getId_sexo() {
		return id_sexo;
	}

	public void setId_sexo(int id_sexo) {
		this.id_sexo = id_sexo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
