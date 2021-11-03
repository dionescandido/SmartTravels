package br.com.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import br.com.controller.ConectaBancoController;
import br.com.model.Solicitacao;


@ManagedBean(name = "SolicitacaoAutorizadoView")
@ViewScoped
public class SolicitacaoAutorizadoView {

	private List<Solicitacao> listaSolicitacaoAutorizado;

	@PostConstruct
	public void BtnSolicitacaoAutorizado() {

		Solicitacao s1 = new Solicitacao();
		this.listaSolicitacaoAutorizado = new ConectaBancoController().ListarSolicitacaoAutorizado(s1);

	}

	// get e set
	public List<Solicitacao> getListaSolicitacaoAutorizado() {
		return listaSolicitacaoAutorizado;
	}

	public void setListaSolicitacaoAutorizado(List<Solicitacao> listaSolicitacaoAutorizado) {
		this.listaSolicitacaoAutorizado = listaSolicitacaoAutorizado;
	}

}
