package br.com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity	
public class Viagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private Double km_inicial;
	private Double km_final;
	@Temporal(TemporalType.DATE)
	private Date dat_inicial;
	@Temporal(TemporalType.DATE)
	private Date dat_final;
	private Funcionario motorista;
	private Solicitacao solicitacao;
	
	
	
	//get e set
	
	
	public int getCod() {
		return cod;
	}
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Funcionario getMotorista() {
		return motorista;
	}

	public void setMotorista(Funcionario motorista) {
		this.motorista = motorista;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Double getKm_inicial() {
		return km_inicial;
	}

	public void setKm_inicial(Double km_inicial) {
		this.km_inicial = km_inicial;
	}

	public Double getKm_final() {
		return km_final;
	}

	public void setKm_final(Double km_final) {
		this.km_final = km_final;
	}

	public Date getDat_inicial() {
		return dat_inicial;
	}

	public void setDat_inicial(Date dat_inicial) {
		this.dat_inicial = dat_inicial;
	}

	public Date getDat_final() {
		return dat_final;
	}

	public void setDat_final(Date dat_final) {
		this.dat_final = dat_final;
	}




	
	
}
