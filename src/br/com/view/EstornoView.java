package br.com.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import br.com.controller.ConectaBancoController;
import br.com.model.Despesas;
import br.com.model.Estorno;


@ManagedBean(name = "EstornoView")
@ViewScoped
public class EstornoView {

	private int cod;
	private Double valor;
	private String tipopagamento;
	private Despesas despesas;
	private List<Estorno> lstEstorno;
	private Estorno estornoSelect;



	// Botao Limpar dados
	public void BtnLimpar() {

		this.valor = null;
		this.tipopagamento = null;
		this.despesas = null;
		this.cod = 0;


	}

	// Lista todas as solicitações por usuario
	public void btnBuscarAction(ActionEvent actionEvent) {

		Estorno e1 = new Estorno();

		this.lstEstorno = new ConectaBancoController().ListarEstorno(e1);

	}

	// Botao Exlcuir
	public void btnExcluir() {

		try {

			FacesMessage message = null;

			if (this.estornoSelect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, selecione uma despesa !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(estornoSelect);

				this.estornoSelect = null;
				this.valor = null;
				this.tipopagamento = null;
				this.despesas = null;

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Despesa Excluida com sucesso !", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// Retorna despesas do dialogo
	public void despesasSelecionado(SelectEvent event) {

		Estorno m1 = (Estorno) event.getObject();
		this.estornoSelect = m1;
		this.cod = m1.getCod();
		this.valor = m1.getValor();
		this.despesas = m1.getDespesas();

	}

	// botao salvar
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.despesas == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencher os campos obrigatorios !",
						null);

			} else {

				Estorno m1 = new Estorno();

				Despesas v1 = new Despesas();
				v1.setCod(this.despesas.getCod());

				m1.setDespesas(v1);

				m1.setCod(this.cod);
				m1.setValor(this.valor);
				

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);

				this.valor = null;
				this.tipopagamento = null;
				this.despesas = null;
		

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// Retorna Despesas do dialogo
	public void Despeas(SelectEvent event) {

		Despesas m1 = (Despesas) event.getObject();
		this.despesas = m1;

	}
	
	

	// Retorna Estorno do dialogo
	public void EstornoSelecionado(SelectEvent event) {

		Estorno m1 = (Estorno) event.getObject();
		this.estornoSelect = m1;
		this.cod = m1.getCod();
		this.despesas = m1.getDespesas();
		this.valor = m1.getValor();
	
	

	}


	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("SelecaoEstorno", opcoes, null);
	}

	public void selecionar(Estorno m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	
	
	// get e set
	
	
	public Estorno getEstornoSelect() {
		if (estornoSelect == null) {
			estornoSelect = new Estorno();
		}
		
		return estornoSelect;
	}

	public void setEstornoSelect(Estorno estornoSelect) {
		this.estornoSelect = estornoSelect;
	}
	
	public int getCod() {
		return cod;
	}

	public List<Estorno> getLstEstorno() {
		return lstEstorno;
	}

	public void setLstEstorno(List<Estorno> lstEstorno) {
		this.lstEstorno = lstEstorno;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipopagamento() {
		return tipopagamento;
	}

	public void setTipopagamento(String tipopagamento) {
		this.tipopagamento = tipopagamento;
	}

	public Despesas getDespesas() {
		
		if(despesas == null) {
			despesas = new Despesas();
		}
		
		return despesas;
	}

	public void setDespesas(Despesas despesas) {
		this.despesas = despesas;
	}


	
	
	

	}
