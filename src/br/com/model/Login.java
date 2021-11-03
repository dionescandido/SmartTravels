package br.com.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Login implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod;
	private Usuario usuarioLogado;
	@Temporal(TemporalType.DATE)
	private Date dataLogin;
	
	
	
	//get e set
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public Date getDataLogin() {
		return dataLogin;
	}
	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	

	
	
	

}
