package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity	
public class Estorno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private Double valor;
	private Despesas despesas;
	
	
	
	//get e set
	

	public Despesas getDespesas() {
		return despesas;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public void setDespesas(Despesas despesas) {
		this.despesas = despesas;
	}

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

}
