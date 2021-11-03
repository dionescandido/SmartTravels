package br.com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private String numero;
	private String bandeira;
	private String datvencimento;
	
	//get e set
	
	
	public int getCod() {
		return cod;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public String getDatvencimento() {
		return datvencimento;
	}
	public void setDatvencimento(String datvencimento2) {
		this.datvencimento = datvencimento2;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}

	

}
