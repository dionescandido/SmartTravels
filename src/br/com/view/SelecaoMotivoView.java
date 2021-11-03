package br.com.view;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import br.com.model.Motivo;



@ManagedBean(name = "SelecaoMotivoView")
@ViewScoped
public class SelecaoMotivoView {
	
	
	// abrir dialogo
		public void abrirDialogo() {
			Map<String, Object> opcoes = new HashMap<String, Object>();
			opcoes.put("modal", true);
			opcoes.put("resizable", false);
			opcoes.put("contentHeight", 470);
			
			 PrimeFaces.current().dialog().openDynamic("SelecaoMotivo", opcoes, null);
		}
	
	
	public void selecionar(Motivo m1) {
		 PrimeFaces.current().dialog().closeDynamic(m1);
	}

}
