package br.com.Rel;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.controller.ConectaBancoController;
import br.com.model.Cliente;
import br.com.model.Funcionario;
import br.com.model.Motivo;
import br.com.model.Solicitacao;
import br.com.model.Veiculo;
import br.com.model.Viagem;

@ManagedBean(name = "ViagemRel")
@ViewScoped
public class ViagensRel {

	private String nomef;
	private String nome;
	private String modelo;
	private Veiculo veiculo;
	private Funcionario funcionario;
	private Date data;
	private Motivo motivo;
	private String status;
	private Cliente cliente;
	private Date dat_inicial;

	
	private Date dat_final;

	private List<Solicitacao> lstsolicit;
	private List<Viagem> lstviagem;
	private Solicitacao s1 = new Solicitacao();
	private Viagem v1 = new Viagem();

	private int selecaoComboMotivo;
	private List<SelectItem> lstCmbMotivo;
	
	
	
	// Botao Limpar dados
		public void BtnLimpar() {

			this.modelo = null;
			this.dat_final = null;
			this.dat_inicial = null;
			this.nome = null;
			this.nomef = null;
			this.selecaoComboMotivo = 0;
			this.status = null;
			this.veiculo = null;
			this.cliente = null;
			this.funcionario = null;
			

		}
	

	// carrega a lista de solicitações para impressão

	public void btnBuscarAction() {

		this.lstviagem = new ConectaBancoController().ListarViagens(v1, dat_inicial, dat_final);

	}

	// Retorna veiculo do dialogo
	public void VeiculoSelecionado(SelectEvent event) {

		Veiculo m1 = (Veiculo) event.getObject();
		this.veiculo = m1;
		this.modelo = m1.getModelo();

	}

	// recebe Funcionario do dialogo
	public void FuncionarioSelecionado(SelectEvent event) {

		Funcionario f1 = (Funcionario) event.getObject();
		this.funcionario = f1;
		this.nomef = f1.getNome();

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

	// retorna do dialogo o objeto
	public void selecionar(Veiculo m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	FacesMessage message = null;
	
	// Botao imprimir
	public void btnImprimir() {	
		
		FacesMessage message = null;
		
					
		if(this.dat_inicial == null || this.dat_final == null) {
			
			
			
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obrigatorio !!", "Preencher um periodo "));
		    
			

			
		}else {
				
			btnBuscarAction();

		}

				ConectaBancoController con = new ConectaBancoController();
				con.GerarRelatorio(lstviagem, veiculo, cliente, funcionario, selecaoComboMotivo);

		
		
		
	}
		
	

	// gete e set

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Viagem getV1() {
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

	// gera o combo de lista de Motivo
	public List<SelectItem> getLstCmbMotivo() {
		Motivo m1 = new Motivo();
		List<Motivo> lstMotivo = new ConectaBancoController().ListarMotivo(m1);
		this.lstCmbMotivo = new ArrayList<SelectItem>();

		lstCmbMotivo = new ArrayList<SelectItem>();
		lstCmbMotivo.add(new SelectItem("0", ""));

		for (int i = 0; i < lstMotivo.size(); i++)
			lstCmbMotivo.add(new SelectItem(lstMotivo.get(i).getCod(), lstMotivo.get(i).getDescricao()));

		return lstCmbMotivo;
	}

	public void setLstCmbMotivo(List<SelectItem> lstCmbMotivo) {
		this.lstCmbMotivo = lstCmbMotivo;
	}

	public List<Viagem> getLstviagem() {
		return lstviagem;
	}

	public void setLstviagem(List<Viagem> lstviagem) {
		this.lstviagem = lstviagem;
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
		return cliente;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Solicitacao getS1() {
		return s1;
	}

	public void setS1(Solicitacao s1) {
		this.s1 = s1;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Solicitacao> getLstsolicit() {
		return lstsolicit;
	}

	public void setLstsolicit(List<Solicitacao> lstsolicit) {
		this.lstsolicit = lstsolicit;
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
