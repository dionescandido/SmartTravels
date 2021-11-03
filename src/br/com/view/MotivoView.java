package br.com.view;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

import br.com.controller.ConectaBancoController;
import br.com.model.Motivo;

@ManagedBean(name = "MotivoView")
@ViewScoped
public class MotivoView {

	private int cod;
	private String descricao;
	private List<Object> listaMotivo;
	private Motivo motivoselect;

	
	
	// Botao Limpar dados
	public void BtnLimpar() {

		this.descricao = "";

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.descricao == "") {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, informe uma descriçaõ !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(motivoselect);

				this.descricao = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Motivo " + this.motivoselect.getDescricao(),
						"Excluido com sucesso!!");

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao excluir o Motivo " + this.motivoselect.getDescricao() + " !", "Possui vinculo no sistema");

			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna municipio do dialogo
	public void motivoSelecionado(SelectEvent event) {

		Motivo m1 = (Motivo) event.getObject();
		this.motivoselect = m1;
		this.cod = m1.getCod();
		this.descricao = m1.getDescricao();

	}

	public void btnSalvarAction(ActionEvent actionEvent) {

		FacesMessage message = null;

		try {

			if (this.descricao == "") {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencher o campo !", null);

			} else {

				Motivo m1 = new Motivo();
				m1.setCod(this.cod);
				m1.setDescricao(this.descricao);

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);

				this.descricao = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Motivo Salvo com sucesso!", null);

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao excluir o Motivo " + this.motivoselect.getDescricao(), null);

			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		Motivo m1 = new Motivo();

		this.listaMotivo = new ConectaBancoController().Listar(m1);

	}

	// get e set

	public int getCod() {
		return cod;
	}

	public List<Object> getListaMotivo() {
		return listaMotivo;
	}

	public void setListaMotivo(List<Object> listaMotivo) {
		this.listaMotivo = listaMotivo;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Motivo getMotivoselect() {
		return motivoselect;
	}

	public void setMotivoselect(Motivo motivoselect) {
		this.motivoselect = motivoselect;
	}

}
