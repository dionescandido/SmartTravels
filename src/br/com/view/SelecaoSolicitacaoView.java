package br.com.view;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import br.com.model.Solicitacao;

@ManagedBean(name = "SelecaoSolicitacaoView")
@ViewScoped
public class SelecaoSolicitacaoView {

	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("Solicitacao", opcoes, null);

	}	
	
	// abrir dialogo
		public void SelecaoDialogo() {
			Map<String, Object> opcoes = new HashMap<String, Object>();
			opcoes.put("modal", true);
			opcoes.put("resizable", false);
			opcoes.put("contentHeight", 470);

			PrimeFaces.current().dialog().openDynamic("SolicitacaoSelect", opcoes, null);

		}	

	// fechar dialogo
	public void fecharDialogo() {

		PrimeFaces.current().dialog().closeDynamic("Solicitacao");
		
	}

	public void selecionar(Solicitacao m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

}
