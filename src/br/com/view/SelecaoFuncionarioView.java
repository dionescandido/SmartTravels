package br.com.view;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import br.com.model.Funcionario;



@ManagedBean(name = "SelecaoFuncionarioView")
@ViewScoped
public class SelecaoFuncionarioView {
	
	
	// abrir dialogo
		public void abrirDialogo() {
			Map<String, Object> opcoes = new HashMap<String, Object>();
			opcoes.put("modal", true);
			opcoes.put("resizable", false);
			opcoes.put("contentHeight", 470);
			
			 PrimeFaces.current().dialog().openDynamic("SelecaoFuncionario", opcoes, null);
		}
	
	
	public void selecionar(Funcionario m1) {
		 PrimeFaces.current().dialog().closeDynamic(m1);
	}

}
