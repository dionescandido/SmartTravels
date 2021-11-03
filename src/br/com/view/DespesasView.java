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
import br.com.model.Despesas;
import br.com.model.Funcionario;
import br.com.model.TipoDespesas;
import br.com.model.Usuario;
import br.com.model.Viagem;

@ManagedBean(name = "DespesasView")
@ViewScoped
public class DespesasView extends LoginView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod;
	private TipoDespesas tipodespesas;
	private Double valor;
	private String numNota;
	private Viagem viagem;
	private Despesas despesaSelect;
	private Funcionario funcionarioselect;
	private List<Despesas> lstDespesas;
	private Cartao cartaoSelect;
	private String tipopagamento;

	// Botao Limpar dados
	public void BtnLimpar() {

		this.valor = null;
		this.tipodespesas = null;
		this.numNota = "";
		this.viagem = null;
		this.funcionarioselect = null;
		this.cod = 0;
		this.cartaoSelect = null;
		this.tipopagamento = "";

	}

	// Lista todas as solicitações por usuario
	public void btnBuscarAction(ActionEvent actionEvent) {

		Despesas m1 = new Despesas();

		this.lstDespesas = new ConectaBancoController().ListarDespesasUsuarioo(m1);

	}

	// Botao Exlcuir
	public void btnExcluir() {

		try {

			FacesMessage message = null;

			if (this.despesaSelect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, selecione uma despesa !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(despesaSelect);

				this.despesaSelect = null;
				this.valor = null;
				this.tipodespesas = null;
			    this.cartaoSelect = null;
				this.numNota = "";
				this.viagem = null;
				this.funcionarioselect = null;
				this.tipopagamento = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Despesa Excluida com sucesso !", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// Retorna despesas do dialogo
	public void despesasSelecionado(SelectEvent event) {

		Despesas m1 = (Despesas) event.getObject();
		this.despesaSelect = m1;
		this.cod = m1.getCod();
		this.numNota = m1.getNumNota();
		this.tipodespesas = m1.getTipodespesas();
		this.valor = m1.getValor();
		this.viagem = m1.getViagem();
		this.cartaoSelect = m1.getCartao();
		this.tipopagamento = m1.getTipopagamento();

	}

	// botao salvar
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.tipodespesas == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencher os campos obrigatorios !",
						null);

			} else {

				Despesas m1 = new Despesas();

				Viagem v1 = new Viagem();
				v1.setCod(this.viagem.getCod());

				m1.setViagem(v1);

				TipoDespesas t1 = new TipoDespesas();
				t1.setCod(this.tipodespesas.getCod());

				m1.setTipodespesas(t1);

				Funcionario f1 = new Funcionario();
				f1.setCod(this.funcionarioselect.getCod());
				
				m1.setFuncionario(f1);
				
				Cartao c1 = new Cartao();
				c1.setCod(this.cartaoSelect.getCod());
				
				m1.setCartao(c1);

				m1.setCod(this.cod);
				m1.setNumNota(this.numNota);
				m1.setValor(this.valor);
				 Usuario u2 = (Usuario)  hs.getAttribute("usuariolog");
				m1.setUsuario(u2);
				m1.setTipopagamento(this.tipopagamento);
				

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);

				this.valor = null;
				this.tipodespesas = null;
				this.cartaoSelect = null;
				this.numNota = "";
				this.viagem = null;
				this.funcionarioselect = null;
				this.tipopagamento = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// Retorna Funcionario do dialogo
	public void FuncionarioSelecionado(SelectEvent event) {

		Funcionario m1 = (Funcionario) event.getObject();
		this.funcionarioselect = m1;

	}
	
	
	// Retorna cartao do dialogo
		public void CartaoSelecionado(SelectEvent event) {

			Cartao m1 = (Cartao) event.getObject();
			this.cartaoSelect = m1;

		}
	

	// Retorna tipo de Despesas do dialogo
	public void TipoDespesasSelecionado(SelectEvent event) {

		TipoDespesas m1 = (TipoDespesas) event.getObject();
		this.tipodespesas = m1;

	}

	// Retorna Despesas do dialogo
	public void DespesasSelecionado(SelectEvent event) {

		Despesas m1 = (Despesas) event.getObject();
		this.despesaSelect = m1;
		this.cod = m1.getCod();
		this.funcionarioselect = m1.getFuncionario();
		this.cartaoSelect = m1.getCartao();
		this.numNota = m1.getNumNota();
		this.tipodespesas = m1.getTipodespesas();
		this.valor = m1.getValor();
		this.viagem = m1.getViagem();
		this.tipopagamento = m1.getTipopagamento();

	}

	// Retorna Viagem do dialogo
	public void ViagemSelecionado(SelectEvent event) {

		Viagem m1 = (Viagem) event.getObject();
		this.viagem = m1;

	}

	// fecha o dialogo e retorna um objeto
	public void selecionarViagem(Viagem m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("SelecaoDespesas", opcoes, null);
	}

	public void selecionar(Despesas m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// get e set

	public int getCod() {
		return cod;
	}

	public String getTipopagamento() {
		return tipopagamento;
	}

	public void setTipopagamento(String tipopagamento) {
		this.tipopagamento = tipopagamento;
	}

	public Cartao getCartaoSelect() {
		
		if (cartaoSelect == null) {
			cartaoSelect = new Cartao();
		}

		return cartaoSelect;
	}

	public void setCartaoSelect(Cartao cartaoSelect) {
		this.cartaoSelect = cartaoSelect;
	}

	public Funcionario getFuncionarioselect() {

		if (funcionarioselect == null) {
			funcionarioselect = new Funcionario();
		}

		return funcionarioselect;
	}

	public void setFuncionarioselect(Funcionario funcionarioselect) {
		this.funcionarioselect = funcionarioselect;
	}

	public Despesas getDespesaSelect() {
		return despesaSelect;
	}

	public void setDespesaSelect(Despesas despesaSelect) {
		this.despesaSelect = despesaSelect;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public TipoDespesas getTipodespesas() {

		if (tipodespesas == null) {
			tipodespesas = new TipoDespesas();
		}

		return tipodespesas;
	}

	public void setTipodespesas(TipoDespesas tipodespesas) {
		this.tipodespesas = tipodespesas;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNumNota() {
		return numNota;
	}

	public void setNumNota(String numNota) {
		this.numNota = numNota;
	}


	public Viagem getViagem() {

		if (viagem == null) {
			viagem = new Viagem();
		}

		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public List<Despesas> getLstDespesas() {
		return lstDespesas;
	}

	public void setLstDespesas(List<Despesas> lstDespesas) {
		this.lstDespesas = lstDespesas;
	}

}
