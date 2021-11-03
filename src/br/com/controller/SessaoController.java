package br.com.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.sessions.Session;

import br.com.model.Usuario;

public class SessaoController {
	
	
	private Usuario usuario = new  Usuario();
	private static Session sessao;
	
	
	// Recebe o usuario logado
	public Usuario obterUsuarioLogado () {
		
		 HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpSession session = (HttpSession) request.getSession();
	        Usuario usuario = (Usuario) session.getAttribute("usuariolog");
	        
	        session.getAttribute("usuariolog");
	        
	        return usuario;
	}

	  // seta o usuario logado 
	public void inserirUsuarioLogado(Usuario cliente, boolean clienteLogado) {
		
	    FacesContext fc = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	    session.setAttribute("usuariolog", usuario);

	    
	}
	
	// abre sessao
	public Session AbrirSessao() {
		
		HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
	
		return (Session) session;
	}
	
	
	public void RedirecionaPage() {
		

			
	}
	
	
	public FacesContext AbiriInstancia() {
		
		  FacesContext fc = FacesContext.getCurrentInstance();
		
		  return fc;
	}
	
	

	
	
	//get e set
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static Session getSessao() {
		return sessao;
	}

	public static void setSessao(Session sessao) {
		SessaoController.sessao = sessao;
	}
	
	
	
	

}
