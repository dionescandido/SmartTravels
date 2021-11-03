package br.com.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;




import br.com.controller.ConectaBancoController;
import br.com.model.Solicitacao;
import br.com.model.Usuario;

@ManagedBean(name = "SolicitacaoFiltradoView")
@ViewScoped


public class SolicitacaoFiltradoView  extends LoginView {

	private static final long serialVersionUID = 1L;
	private List<Solicitacao> listaSolicitacaoFiltrado;

	@PostConstruct
	public void SolicitacaoFiltrada() {
		
	
		
		 Usuario u2 = (Usuario)  hs.getAttribute("usuariolog");

		
		Solicitacao s1 = new Solicitacao();
		this.listaSolicitacaoFiltrado = new ConectaBancoController().ListarSolicitacao(s1,u2);
	
       
	    
	    System.out.println("......................................................................................");

	    System.out.println(hs.getId());
		
	    System.out.println("......................................................................................");

		System.out.println(u2.getNome());
		
	}

	

	

	// get e set

	public List<Solicitacao> getListaSolicitacaoFiltrado() {
		return listaSolicitacaoFiltrado;
	}

	public void setListaSolicitacaoFiltrado(List<Solicitacao> listaSolicitacaoFiltrado) {
		this.listaSolicitacaoFiltrado = listaSolicitacaoFiltrado;
	}
	

}
