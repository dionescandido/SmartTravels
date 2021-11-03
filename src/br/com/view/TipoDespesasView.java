package br.com.view;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

import br.com.controller.ConectaBancoController;
import br.com.model.TipoDespesas;

@ManagedBean(name = "TipoDespesasView")
@ViewScoped
public class TipoDespesasView {

	private int cod;
	private String descricao;
	private List<Object> listaTipoDespesas;
	private TipoDespesas tipoDespesasselect;

	// Botao Limpar dados
	public void BtnLimpar() {

		this.descricao = "";

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.descricao == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, informe uma descriçaõ !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(tipoDespesasselect);

				this.descricao = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Despesa " + this.descricao + " Excluido com sucesso !", null);

			}

		} catch (Exception e) {

			System.out.println("Erro ao gravar !!!!" + e);
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					" Erro ao excluir a Despesa " + this.tipoDespesasselect.getDescricao(), null);

		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna municipio do dialogo
	public void tipoDespesasSelecionado(SelectEvent event) {

		TipoDespesas m1 = (TipoDespesas) event.getObject();
		this.tipoDespesasselect = m1;
		this.cod = m1.getCod();
		this.descricao = m1.getDescricao();

	}

	public void btnSalvarAction(ActionEvent actionEvent) {

		FacesMessage message = null;

		try {

			if (this.descricao == "") {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencher o campo !", null);

			} else {

				TipoDespesas m1 = new TipoDespesas();
				m1.setCod(this.cod);
				m1.setDescricao(this.descricao);

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);

				this.descricao = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Despesa Salvo com sucesso!", null);

			}
			
		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao salvar a Despesa " + this.tipoDespesasselect.getDescricao(), null);

			System.out.println("Erro ao gravar !!!!" + e);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		TipoDespesas m1 = new TipoDespesas();

		this.listaTipoDespesas = new ConectaBancoController().Listar(m1);

	}

	// get e set

	public int getCod() {
		return cod;
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

	public List<Object> getListaTipoDespesas() {
		return listaTipoDespesas;
	}

	public void setListaTipoDespesas(List<Object> listaTipoDespesas) {
		this.listaTipoDespesas = listaTipoDespesas;
	}

	public TipoDespesas getTipoDespesasselect() {
		return tipoDespesasselect;
	}

	public void setTipoDespesasselect(TipoDespesas tipoDespesasselect) {
		this.tipoDespesasselect = tipoDespesasselect;
	}

}
