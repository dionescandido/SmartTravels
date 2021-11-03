package br.com.view;

import java.util.Date;
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
import br.com.model.Funcionario;
import br.com.model.Solicitacao;
import br.com.model.Viagem;

@ManagedBean(name = "ViagemView")
@ViewScoped
public class ViagemView {

	private int cod;
	private Double km_inicial;
	private Double km_final;
	private Date dat_inicial;
	private Date dat_final;
	private Solicitacao solicitacao;
	private List<Object> listaViagem;
	private List<Viagem> ultimaviagem;
	private Viagem viagemselect;
	private Funcionario funcionarioslect;
	private Solicitacao solicitacaoselect;
	
	
	
	
	

	// Botao Limpar dados
	public void BtnLimpar() {

		this.funcionarioslect = null;
		this.dat_final = null;
		this.dat_inicial = null;
		this.solicitacao = null;
		this.km_final = null;
		this.km_inicial = null;
		this.cod = 0;

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.viagemselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione uma viagem !", null);

			} else {

				Solicitacao s1 = new Solicitacao();

				s1.setCod(this.solicitacao.getCod());
				s1.setStatus("Aguardando");
				s1.setCliente(this.solicitacao.getCliente());
				s1.setDatinicial(this.solicitacao.getDatinicial());
				s1.setMotivo(this.solicitacao.getMotivo());
				s1.setParecer(this.solicitacao.getParecer());
				s1.setUsuariosEditor(this.solicitacao.getUsuariosEditor());
				s1.setVeiculo(this.solicitacao.getVeiculo());
				s1.setUsuariosolicitante(this.solicitacao.getUsuariosolicitante());
				s1.setDatfinal(this.solicitacao.getDatfinal());
				s1.setDatinicial(this.solicitacao.getDatinicial());

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(viagemselect);
				mc.Editar(s1);

				this.funcionarioslect = null;
				this.dat_final = null;
				this.dat_inicial = null;
				this.solicitacao = null;
				this.km_final = null;
				this.km_inicial = null;
				this.cod = 0;

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Viagem Excluido com sucesso !", null);

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, " Viagem Não pode ser Excluida com !",
					"Possui despesas vinculadas");

			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna Funcionario do dialogo
	public void FuncionarioSelecionado(SelectEvent event) {

		Funcionario m1 = (Funcionario) event.getObject();
		this.funcionarioslect = m1;

	}

	// Retorna Solicitacao do dialogo
	public void SolicitacaoSelecionado(SelectEvent event) {

		Solicitacao m1 = (Solicitacao) event.getObject();
		this.solicitacao = m1;
		this.dat_inicial = m1.getDatinicial();
		this.dat_final = m1.getDatfinal();

	}

	// Retorna Viagem do dialogo
	public void ViagemSelecionado(SelectEvent event) {

		Viagem m1 = (Viagem) event.getObject();
		this.viagemselect = m1;
		this.cod = m1.getCod();
		this.dat_final = m1.getDat_final();
		this.dat_inicial = m1.getDat_inicial();
		this.funcionarioslect = m1.getMotorista();
		this.km_final = m1.getKm_final();
		this.km_inicial = m1.getKm_inicial();
		this.solicitacao = m1.getSolicitacao();

	}

	public void btnSalvarAction(ActionEvent actionEvent) {
		

		try {

			FacesMessage message = null;
			
			
			Viagem v1 = new Viagem();
			
			//lista a ultima viagem no banco
			ConectaBancoController cb = new ConectaBancoController();
			cb.ListarUltimaViagen(v1);
			
			this.ultimaviagem = new ConectaBancoController().ListarUltimaViagen(v1);
			
			v1 = ultimaviagem.isEmpty() ? v1 : ultimaviagem.get(0); 
			
			//caso seja a primeira viagem e setado o km anterior zerado
			if (v1.getKm_final() == null) {
				v1.setKm_final(00.00);
			}
	
			// verifica se o km final da ultima viagem é maior que o km atual
			if (this.km_inicial < v1.getKm_final()) {
				
				
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"O KM final da ultima viagem e maior que o seu KM inicial !!!", null);
				
			}else {
			

			if (this.dat_inicial.after(this.dat_final)) {
				
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"A data final não pode ser retroativa em relação a data inicial !! !", null);

			} else {
				
				if ( this.km_inicial > this.km_final) {
					
					message = new FacesMessage(FacesMessage.SEVERITY_WARN,
							"O KM inicial não pode ser maior que o KM final !", null);
					
				}else {
					
				if (this.funcionarioslect == null || this.solicitacao == null) {

					message = new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Por favor, preencher os campos obrigatorios !", null);

				} else {

					Viagem m1 = new Viagem();

					m1.setCod(this.getCod());
					m1.setDat_final(this.dat_final);
					m1.setDat_inicial(this.dat_inicial);
					m1.setKm_final(this.km_final);
					m1.setKm_inicial(this.km_inicial);

					Solicitacao s1 = new Solicitacao();

					s1.setCod(this.solicitacao.getCod());
					s1.setStatus("Finalizado");
					s1.setCliente(this.solicitacao.getCliente());
					s1.setDatinicial(this.solicitacao.getDatinicial());
					s1.setDatfinal(this.solicitacao.getDatfinal());
					s1.setMotivo(this.solicitacao.getMotivo());
					s1.setParecer(this.solicitacao.getParecer());
					s1.setUsuariosEditor(this.solicitacao.getUsuariosEditor());
					s1.setVeiculo(this.solicitacao.getVeiculo());
					s1.setUsuariosolicitante(this.solicitacao.getUsuariosolicitante());
					s1.setDatsolict(this.solicitacao.getDatsolict());

					m1.setSolicitacao(s1);

					Funcionario p1 = new Funcionario();
					p1.setCod(this.funcionarioslect.getCod());

					m1.setMotorista(p1);

					ConectaBancoController mc = new ConectaBancoController();

					mc.Editar(s1);
					mc.Editar(m1);

					this.funcionarioslect = null;
					this.dat_final = null;
					this.dat_inicial = null;
					this.solicitacao = null;
					this.km_final = null;
					this.km_inicial = null;
					this.cod = 0;

					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

				}

			}
			}
			}
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// metodo para listar viagem
	public void btnBuscarAction(ActionEvent actionEvent) {

		Viagem m1 = new Viagem();

		this.listaViagem = new ConectaBancoController().Listar(m1);

	}

	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("SelecaoViagem", opcoes, null);
	}

	// fecha o dialogo e retorna um objeto
	public void selecionarsolicitacao(Solicitacao m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// fecha o dialogo e retorna um objeto
	public void selecionarFuncionario(Funcionario m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// get e set

	public int getCod() {
		return cod;
	}

	public List<Object> getListaViagem() {
		return listaViagem;
	}

	public void setListaViagem(List<Object> listaViagem) {
		this.listaViagem = listaViagem;
	}

	public Funcionario getFuncionarioslect() {

		if (funcionarioslect == null) {
			funcionarioslect = new Funcionario();
		}

		return funcionarioslect;
	}

	public void setFuncionarioslect(Funcionario funcionarioslect) {
		this.funcionarioslect = funcionarioslect;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Double getKm_inicial() {
		return km_inicial;
	}

	public void setKm_inicial(Double km_inicial) {
		this.km_inicial = km_inicial;
	}

	public Double getKm_final() {
		return km_final;
	}

	public void setKm_final(Double km_final) {
		this.km_final = km_final;
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

	public Solicitacao getSolicitacao() {

		if (solicitacao == null) {
			solicitacao = new Solicitacao();
		}

		return solicitacao;
	}

	public List<Viagem> getUltimaviagem() {
		return ultimaviagem;
	}

	public void setUltimaviagem(List<Viagem> ultimaviagem) {
		this.ultimaviagem = ultimaviagem;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Viagem getViagemselect() {
		return viagemselect;
	}

	public void setViagemselect(Viagem viagemselect) {
		this.viagemselect = viagemselect;
	}



	public Solicitacao getSolicitacaoselect() {
		return solicitacaoselect;
	}



	public void setSolicitacaoselect(Solicitacao solicitacaoselect) {
		this.solicitacaoselect = solicitacaoselect;
	}

}
