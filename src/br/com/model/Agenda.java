package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity	
public class Agenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private Solicitacao solicitacao;
	
	
	
	//get e set

	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}
	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}


}
