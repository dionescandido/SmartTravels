package br.com.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import br.com.controller.ConectaBancoController;
import br.com.model.Cliente;
import br.com.model.Funcionario;
import br.com.model.Motivo;
import br.com.model.Solicitacao;
import br.com.model.Usuario;
import br.com.model.Veiculo;

@ManagedBean(name = "SolicitacaoView")
@ViewScoped
public class SolicitacaoView extends LoginView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod;
	private String parecer;
	private Solicitacao solicitacaoselect;
	private List<Object> listaSolicitacao;
	private Boolean linha;
	private Funcionario funcionario;

	private Date datinicial;
	private Date datfinal;
	private HtmlDataTable linhaselect;
	private int index;
	private Usuario usuariosolicitante;
	private Usuario usuariosEditor;

	private int selecaoComboMotivo;
	private List<SelectItem> lstCmbMotivo;

	private int selecaoComboCliente;
	private List<SelectItem> lstCmbCliente;

	private int selecaoComboVeiculo;
	private List<SelectItem> lstCmbVeiculo;

	// Buscar os Solicitacao
	@PostConstruct
	public void btnBuscarAction() {

		Solicitacao s1 = new Solicitacao();
		this.listaSolicitacao = new ConectaBancoController().Listar(s1);

	}

	// Botao Limpar dados
	public void BtnLimpar() {

		this.datinicial = null;
		this.parecer = null;
		this.datfinal = null;
		this.lstCmbCliente = null;
		this.lstCmbMotivo = null;
		this.lstCmbVeiculo = null;
		this.selecaoComboCliente = -1;
		this.selecaoComboMotivo = -1;
		this.selecaoComboVeiculo = -1;

	}

	// Retorna Solicitacao do dialogo
	public void SolicitacaoSelecionado(SelectEvent event) {

		Solicitacao m1 = (Solicitacao) event.getObject();
		this.solicitacaoselect = m1;

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

	// Gera o combo de lista de Veiculo
	public List<SelectItem> getLstCmbVeiculo() {

		Veiculo v1 = new Veiculo();
		List<Veiculo> lstVeiculo = new ConectaBancoController().ListarVeiculo(v1);
		this.lstCmbVeiculo = new ArrayList<SelectItem>();

		lstCmbVeiculo = new ArrayList<SelectItem>();
		lstCmbVeiculo.add(new SelectItem("0", ""));

		for (int i = 0; i < lstVeiculo.size(); i++)
			lstCmbVeiculo.add(new SelectItem(lstVeiculo.get(i).getCod(), lstVeiculo.get(i).getModelo()));

		return lstCmbVeiculo;
	}

	// Gera o combo de lista de Cliente

	Cliente c1 = new Cliente();

	public List<SelectItem> getLstCmbCliente() {
		Cliente c1 = new Cliente();

		List<Cliente> lstCliente = new ConectaBancoController().ListarCliente(c1);
		this.lstCmbCliente = new ArrayList<SelectItem>();

		lstCmbCliente = new ArrayList<SelectItem>();
		lstCmbCliente.add(new SelectItem("0", ""));

		for (int i = 0; i < lstCliente.size(); i++) {
			Cliente c = lstCliente.get(i);
			lstCmbCliente.add(new SelectItem(c.getCod(), c.getNome()));
		}

		return lstCmbCliente;
	}

	// Verificar se possui solicitação na mesma da com o mesmo veiculo
	public Boolean VerificarSolicitacao() {

		Solicitacao s2 = new Solicitacao();
		s2.setDatinicial(this.datinicial);
		s2.setDatfinal(this.datfinal);
		Veiculo v1 = new Veiculo();
		v1.setCod(this.selecaoComboVeiculo);
		s2.setVeiculo(v1);
		ConectaBancoController scc = new ConectaBancoController();
		scc.ListarSolicitacaoPorData(s2, v1);

		return scc.ListarSolicitacaoPorData(s2, v1);
	}

	// Verificar se possui solicitação na mesma da com o mesmo usuario
	public Boolean VerificarSolicitacaoUsuario() {

		Solicitacao s2 = new Solicitacao();
		s2.setDatinicial(this.datinicial);
		s2.setDatfinal(this.datfinal);
		 Usuario u2 = (Usuario)  hs.getAttribute("usuariolog");
		this.usuariosolicitante = u2;
		s2.setUsuariosolicitante(usuariosolicitante);
		ConectaBancoController scc = new ConectaBancoController();
		scc.ListarSolicitacaoPorUsuario(s2);

		return scc.ListarSolicitacaoPorUsuario(s2);
	}

	// Botao salvar solicitação
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.datfinal.before(datinicial)) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Data final não pode ser maior que data inicial",
						"");

			} else {

				if (this.selecaoComboVeiculo == 0 || this.selecaoComboMotivo == 0 || this.selecaoComboCliente == 0
						|| this.datfinal == null || this.datinicial == null) {

					message = new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Por favor, preencher todos os campos obrigatoriamente !", null);

				} else {

					if (VerificarSolicitacao().equals(true)) {

						if (VerificarSolicitacaoUsuario().equals(true)) {

							Solicitacao s1 = new Solicitacao();
							s1.setParecer("Pendente");
							s1.setDatinicial(this.datinicial);
							s1.setDatfinal(this.datfinal);
							 Usuario u2 = (Usuario)  hs.getAttribute("usuariolog");
							s1.setUsuariosolicitante(u2);
							s1.setStatus("Aguardando");
							s1.setDatsolict(hoje);

							Motivo m1 = new Motivo();
							m1.setCod(this.selecaoComboMotivo);

							s1.setMotivo(m1);

							Cliente c1 = new Cliente();
							c1.setCod(this.selecaoComboCliente);

							s1.setCliente(c1);

							Veiculo v1 = new Veiculo();
							v1.setCod(this.selecaoComboVeiculo);

							s1.setVeiculo(v1);

							Usuario u3 = (Usuario)  hs.getAttribute("usuariolog");
							this.usuariosolicitante = u3;
							s1.setUsuariosolicitante(usuariosolicitante);

							this.funcionario = u3.getFuncionario();

							ConectaBancoController sc = new ConectaBancoController();
							sc.Salvar(s1);

							listaSolicitacao = sc.Listar(s1);

							this.datinicial = null;
							this.parecer = null;
							this.datfinal = null;
							this.lstCmbCliente = null;
							this.lstCmbMotivo = null;
							this.lstCmbVeiculo = null;
							this.selecaoComboCliente = -1;
							this.selecaoComboMotivo = -1;
							this.selecaoComboVeiculo = -1;

							message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

						} else {

							message = new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Possui outra solicitação Deferida Com a mesma data ", "Para o mesmo Usuario");

						}

					} else {

						message = new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Possui outra solicitação Deferida Com a mesma data ", "Para o mesmo Veiculo");
					}

				}

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}

	}

	// Botao Exlcuir
	public void btnExcluir(Solicitacao s1) {

		FacesMessage message = null;

		try {

			this.index = listaSolicitacao.indexOf(s1);
			solicitacaoselect = s1;

			if (this.solicitacaoselect.getParecer() == "Deferido") {

				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Voce não pode excluir solicitação que ja foi alterado seu Status !!!", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(solicitacaoselect);

				Solicitacao slc = new Solicitacao();
				ConectaBancoController sc = new ConectaBancoController();
				listaSolicitacao = sc.Listar(slc);

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Solicitação Excluido com sucesso !", null);

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, " Solicitação não pode ser excluida !",
					"Possui vinculo com viagens");

			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void PrepararExcluir(ActionEvent evento) {

		// Pega o objeto selecionado da minha tabela
		this.solicitacaoselect = (Solicitacao) evento.getComponent().getAttributes().get("SolicitacaoSelecionado");

	}

	// Botao Editar
		public void btnEditar(ActionEvent evento) {
			
		
			FacesMessage message = new FacesMessage();
			
			if (solicitacaoselect.getDatfinal().before(solicitacaoselect.getDatinicial()) )  {

				FacesContext.getCurrentInstance().addMessage("consulta:id",new FacesMessage("Data final não pode ser maior que data inicial","Erro ao editar solicitação!!!"));
				
		

			}else {

			try {

				if (this.solicitacaoselect.getStatus().equals("Finalizado") || solicitacaoselect.getParecer().equals("")) {

					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao editar solicitação !",
							"Encontra-se finalizada");
					solicitacaoselect = null;

				} else {

					solicitacaoselect.getMotivo().getDescricao();
					solicitacaoselect.getVeiculo().getModelo();
					solicitacaoselect.getCliente().getNome();
					 Usuario u2 = (Usuario)  hs.getAttribute("usuariolog");
					solicitacaoselect.setUsuariosEditor(u2);
					ConectaBancoController mc = new ConectaBancoController();

					mc.Editar(solicitacaoselect);
					
				

					message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Solicitação Editada com sucesso !!!", null);
					
				}

			} catch (Exception e) {
				System.out.println("Erro ao gravar !!!!" + e);
			}

			FacesContext.getCurrentInstance().addMessage(null, message);
	   	}
			
		}
		
		
		// Botao Autorizar
				public void btnAutorizar(ActionEvent evento) {
					
				
					FacesMessage message = null;

					try {

						if (this.solicitacaoselect.getStatus().equals("Finalizado")) {

							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao editar solicitação !",
									"Encontra-se finalizada");
							solicitacaoselect = null;

						} else {

							solicitacaoselect.getMotivo().getDescricao();
							solicitacaoselect.getVeiculo().getModelo();
							solicitacaoselect.getCliente().getNome();
							 Usuario u2 = (Usuario)  hs.getAttribute("usuariolog");
							solicitacaoselect.setUsuariosEditor(u2);
							ConectaBancoController mc = new ConectaBancoController();

							mc.Editar(solicitacaoselect);
							

							message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Solicitação Editada com sucesso !!!", null);
							
						
							EnviarEmail ev = new EnviarEmail();
							ev.email(solicitacaoselect);
							
							
						}

					} catch (Exception e) {
						System.out.println("Erro ao gravar !!!!" + e);
					}

					FacesContext.getCurrentInstance().addMessage(null, message);
			   	}
					
				
		
		
	// get e set

	public int getSelecaoComboMotivo() {
		return selecaoComboMotivo;
	}

	public Usuario getUsuariosolicitante() {
		return usuariosolicitante;
	}

	public void setUsuariosolicitante(Usuario usuariosolicitante) {
		this.usuariosolicitante = usuariosolicitante;
	}

	public Usuario getUsuariosEditor() {
		return usuariosEditor;
	}

	public void setUsuariosEditor(Usuario usuariosEditor) {
		this.usuariosEditor = usuariosEditor;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Cliente getC1() {
		return c1;
	}

	public void setC1(Cliente c1) {
		this.c1 = c1;
	}

	public List<Object> getListaSolicitacao() {
		return listaSolicitacao;
	}

	public void setListaSolicitacao(List<Object> listaSolicitacao) {
		this.listaSolicitacao = listaSolicitacao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Solicitacao getSolicitacaoselect() {

		if (solicitacaoselect == null) {
			solicitacaoselect = new Solicitacao();
		}

		return solicitacaoselect;
	}

	public void setSolicitacaoselect(Solicitacao solicitacaoselect) {
		this.solicitacaoselect = solicitacaoselect;
	}

	public void setSelecaoComboMotivo(int selecaoComboMotivo) {
		this.selecaoComboMotivo = selecaoComboMotivo;
	}

	public void setLstCmbMotivo(List<SelectItem> lstCmbMotivo) {
		this.lstCmbMotivo = lstCmbMotivo;
	}

	public int getSelecaoComboCliente() {
		return selecaoComboCliente;
	}

	public void setSelecaoComboCliente(int selecaoComboCliente) {
		this.selecaoComboCliente = selecaoComboCliente;
	}

	public void setLstCmbCliente(List<SelectItem> lstCmbCliente) {
		this.lstCmbCliente = lstCmbCliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDatinicial() {
		return datinicial;
	}

	public void setDatinicial(Date datinicial) {
		this.datinicial = datinicial;
	}

	public Date getDatfinal() {
		return datfinal;
	}

	public void setDatfinal(Date datfinal) {
		this.datfinal = datfinal;
	}

	public int getSelecaoComboVeiculo() {
		return selecaoComboVeiculo;
	}

	public void setSelecaoComboVeiculo(int selecaoComboVeiculo) {
		this.selecaoComboVeiculo = selecaoComboVeiculo;
	}

	public void setLstCmbVeiculo(List<SelectItem> lstCmbVeiculo) {
		this.lstCmbVeiculo = lstCmbVeiculo;
	}

	public HtmlDataTable getLinhaselect() {
		return linhaselect;
	}

	public Boolean getLinha() {
		return linha;
	}

	public void setLinha(Boolean linha) {
		this.linha = linha;
	}

	public void setLinhaselect(HtmlDataTable linhaselect) {
		this.linhaselect = linhaselect;
	}

}
