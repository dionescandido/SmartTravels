package br.com.Rel;


import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import br.com.controller.ConectaBancoController;
import br.com.model.Cliente;
import br.com.model.Despesas;
import br.com.model.Funcionario;
import br.com.model.Motivo;
import br.com.model.TipoDespesas;
import br.com.model.Viagem;

@ManagedBean(name = "DespesasRel")
@ViewScoped
public class DespesasRel {

	private String nomef;
	private String nome;
	private String modelo;
	private Despesas despesas = new Despesas ();
	private Funcionario funcionario;
	private Date data;
	private Motivo motivo;
	private String status;
	private Cliente cliente;
	private Date dat_inicial;
	private Date dat_final;
	private List<Despesas> lstdespesas;
	private Viagem v1;
	private TipoDespesas tipodespesas;
	private int selecaoComboMotivo;


	// Botao Limpar dados
	public void BtnLimpar() {


		this.dat_final = null;
		this.dat_inicial = null;
		this.nome = null;
		this.nomef = null;
		this.cliente = null;
		this.funcionario = null;
		this.tipodespesas =null;
		this.v1 = null;

	}

	// carrega a lista de solicitações para impressão

	public void btnBuscarAction() {

		this.lstdespesas = new ConectaBancoController().ListarDespesas(despesas, dat_inicial, dat_final);

	}

	// recebe Funcionario do dialogo
	public void FuncionarioSelecionado(SelectEvent event) {

		Funcionario f1 = (Funcionario) event.getObject();
		this.funcionario = f1;
		this.nomef = f1.getNome();

	}

	// Retorna tipo de Despesas do dialogo
	public void TipoDespesasSelecionado(SelectEvent event) {

		TipoDespesas m1 = (TipoDespesas) event.getObject();
		this.tipodespesas = m1;

	}

	// Retorna Viagem do dialogo
	public void ViagemSelecionado(SelectEvent event) {

		Viagem m1 = (Viagem) event.getObject();
		this.v1 = m1;

	}

	// retorna do dialogo o objeto
	public void selecionar(Funcionario f1) {
		PrimeFaces.current().dialog().closeDynamic(f1);
	}

	// recebe Cliente do dialogo
	public void CleinteSelecionado(SelectEvent event) {

		Cliente m1 = (Cliente) event.getObject();
		this.cliente = m1;
		this.nome = m1.getNome();

	}

	// retorna do dialogo o objeto
	public void selecionar(Cliente m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	FacesMessage message = null;

	// Botao imprimir
	public void btnImprimir() {

		FacesMessage message = null;

		if (this.dat_inicial == null || this.dat_final == null) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Obrigatorio !!", "Preencher um periodo "));

		} else {

			btnBuscarAction();

		}
		
		
		if (this.tipodespesas.getCod() == 0) {
			this.tipodespesas = null;
		}
		
		if (this.v1.getCod() == 0) {
			
			this.v1 = null;
		}

		ConectaBancoController con = new ConectaBancoController();
		con.GerarRelatorioDespesas(lstdespesas,cliente,funcionario,tipodespesas,v1);

	}

	// gete e set

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Despesas getDespesas() {
		return despesas;
	}

	public void setDespesas(Despesas despesas) {
		this.despesas = despesas;
	}

	public List<Despesas> getLstdespesas() {
		return lstdespesas;
	}

	public void setLstdespesas(List<Despesas> lstdespesas) {
		this.lstdespesas = lstdespesas;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public Viagem getV1() {
		if (v1 == null) {
			v1 = new Viagem();
		}

		return v1;
	}

	public void setV1(Viagem v1) {
		this.v1 = v1;
	}

	public int getSelecaoComboMotivo() {
		return selecaoComboMotivo;
	}

	public void setSelecaoComboMotivo(int selecaoComboMotivo) {
		this.selecaoComboMotivo = selecaoComboMotivo;
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

	public String getNomef() {
		return nomef;
	}

	public void setNomef(String nomef) {
		this.nomef = nomef;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Motivo getMotivo() {
		if (motivo == null) {
			motivo = new Motivo();
		}

		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}

		return cliente;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDat_inicial() {
		return dat_inicial;
	}

	public void setDat_inicial(Date dat_inicial) {
		this.dat_inicial = dat_inicial;
	}

	public Date getDat_final() {
		return dat_final;
	}

	public void setDat_final(Date dat_final) {
		this.dat_final = dat_final;
	}

}
