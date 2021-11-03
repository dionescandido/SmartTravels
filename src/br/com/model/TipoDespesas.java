package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity	
public class TipoDespesas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private String descricao;
	
	
	//get e set
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
