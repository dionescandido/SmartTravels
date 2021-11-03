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
import br.com.model.Cartao;



@ManagedBean(name = "CartaoView")
@ViewScoped
public class CartaoView {

	private int cod;
	private String numero;
	private String bandeira;
	private String datvencimento;
	private Cartao cartaoselect;
	private List<Object> listcartao;
	
	
	// Limpar campos
		public void BtnLimpar() {

			this.cod = 0;
			this.numero = null;
			this.bandeira = "";
			this.datvencimento = "";
			this.cartaoselect = null;
		

		}
	
	

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.cartaoselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione um Cliente !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(cartaoselect);

				this.cartaoselect = null;
				this.bandeira = "";
				this.numero = "";
				this.datvencimento = null;

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Cliente Excluido com sucesso !", null);

			}

		} catch (Exception e) {
			System.out.println("Erro ao Excluir !!!!" + e);

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao excluir o Cliente",
					"Possui vinculo no sistema");

		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}


	
	// Retorna Cartão do dialogo
	public void CartaoSelecionado(SelectEvent event) {

		Cartao m1 = (Cartao) event.getObject();
		this.cartaoselect = m1;
		this.cod = m1.getCod();
		this.bandeira = m1.getBandeira();
		this.datvencimento = m1.getDatvencimento();
		this.numero = m1.getNumero();

	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		Cartao m1 = new Cartao();

		this.listcartao = new ConectaBancoController().Listar(m1);

	}

	// salvar funcionario
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.datvencimento == null || this.bandeira == "" || this.numero == "") {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Preencher todos os campos obrigatorios !",
						null);

			} else {

				Cartao c1 = new Cartao();

				c1.setBandeira(this.bandeira);
				c1.setDatvencimento(this.datvencimento);
				c1.setNumero(this.numero);

				ConectaBancoController fc = new ConectaBancoController();
				fc.Editar(c1);

				this.bandeira = "";
				this.datvencimento = null;
				this.numero = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("SelecaoCartao", opcoes, null);
	}

	public void selecionar(Cartao m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// get e set

	public int getCod() {
		return cod;
	}

	public Cartao getCartaoselect() {
		return cartaoselect;
	}

	public void setCartaoselect(Cartao cartaoselect) {
		this.cartaoselect = cartaoselect;
	}


	public List<Object> getListcartao() {
		return listcartao;
	}

	public void setListcartao(List<Object> listcartao) {
		this.listcartao = listcartao;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getDatvencimento() {
		return datvencimento;
	}

	public void setDatvencimento(String datvencimento) {
		this.datvencimento = datvencimento;
	}


}
