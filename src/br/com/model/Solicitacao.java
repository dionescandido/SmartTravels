package br.com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Solicitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private String status;
	private Motivo motivo;
	private Veiculo veiculo;
	private Cliente cliente;
	@Temporal(TemporalType.DATE)
	private Date datsolict;
	@Temporal(TemporalType.DATE)
	private Date datinicial;
	@Temporal(TemporalType.DATE)
	private Date datfinal;
	private String parecer;
	private Usuario usuariosolicitante;
	private Usuario usuariosEditor;
	private Funcionario funcionario;
	
	
	//get e set 


	public String getParecer() {
		return parecer;
	}

	public Funcionario getFuncionarios() {
		return funcionario;
	}

	public void setFuncionarios(Funcionario funcionarios) {
		this.funcionario = funcionarios;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuariosolicitante() {
		return usuariosolicitante;
	}

	public void setUsuariosolicitante(Usuario usuariosolicitante) {
		this.usuariosolicitante = usuariosolicitante;
	}

	public Usuario getUsuariosEditor() {
		return usuariosEditor;
	}

	public void setUsuariosEditor(Usuario usuariosEditor) {
		this.usuariosEditor = usuariosEditor;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDatsolict() {
		return datsolict;
	}

	public void setDatsolict(Date datsolict) {
		this.datsolict = datsolict;
	}

	public Date getDatinicial() {
		return datinicial;
	}

	public void setDatinicial(Date datinicial) {
		this.datinicial = datinicial;
	}

	public Date getDatfinal() {
		return datfinal;
	}

	public void setDatfinal(Date datfinal) {
		this.datfinal = datfinal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}



}
