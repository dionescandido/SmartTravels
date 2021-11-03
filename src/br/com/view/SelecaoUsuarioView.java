package br.com.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.controller.ConectaBancoController;
import br.com.model.Motivo;
import br.com.model.Usuario;

@ManagedBean(name = "SelecaoUsuarioView")
@ViewScoped
public class SelecaoUsuarioView {

	private int cod;
	private String user;
	private String senha;
	private String conf_senha;
	private String email;
	private String nome;
	private int permicao = -1;
	private Usuario usuarioSelect;
	private int admin = -2;
	private Motivo motivoselect;
	private List<Object> listaUsuario;

	// Botao Limpar dados
	public void BtnLimpar() {

		this.email = "";
		this.nome = "";
		this.senha = "";
		this.user = "";
		this.conf_senha = "";
		this.admin = 4;
		this.permicao = 4;

	}

	// Retorna lista
	@PostConstruct
	public void btnBuscarAction(ActionEvent actionEvent) {

		Usuario m1 = new Usuario();

		this.listaUsuario = new ConectaBancoController().Listar(m1);

	}

	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("SelecaoUsuario", opcoes, null);
	}

	// retornar usuario do dialogo
	public void selecionar(Usuario m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// Salvar Usuarrio
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.user == null || this.senha == null || this.email == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencher o campo !", null);

			} else {

				if (this.senha.equals(conf_senha)) {

					Usuario u1 = new Usuario();
					u1.setUser(this.user);
					u1.setSenha(this.senha);
					u1.setEmail(this.email);
					u1.setNome(this.nome);
					u1.setConf_senha(this.conf_senha);

					ConectaBancoController uc = new ConectaBancoController();
					uc.Salvar(u1);

					this.email = "";
					this.nome = "";
					this.senha = "";
					this.user = "";
					this.conf_senha = "";

					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

				} else {

					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Senha diferente da confirmação !", null);

				}
			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// Botão voltar
	public void btnVoltarAction(ActionEvent actionEvent) {

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro no processamento. \n" + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	// Botao Editar
	public void btnEditar(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			this.usuarioSelect.setPermicao(this.permicao);
			this.usuarioSelect.setAdmin(this.admin);

			ConectaBancoController mc = new ConectaBancoController();
			mc.Editar(this.usuarioSelect);

			this.user = "";
			this.nome = "";
			this.permicao = -1;
			this.admin = -1;

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Usuario Editado com sucesso !", null);

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// Retorna Usuario do dialogo
	public void UserSelecionado(SelectEvent event) {

		Usuario m1 = (Usuario) event.getObject();
		this.usuarioSelect = m1;
		this.cod = m1.getCod();
		this.user = m1.getUser();
		this.nome = m1.getNome();
		this.permicao = m1.getPermicao();
		this.admin = m1.getAdmin();
	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.usuarioSelect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, selecione um usuario !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(usuarioSelect);

				this.user = "";
				this.permicao = -1;
				this.admin = -1;

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Excluido com sucesso !", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Não é posssivel excluir usuario " + this.user,
					" ! o mesmo possui alguma movimentação no sistema ");

			System.out.println("Erro ao Excluir !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// get e set

	public String getNome() {
		return nome;
	}

	public int getPermicao() {
		return permicao;
	}

	public void setPermicao(int permicao) {
		this.permicao = permicao;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public Motivo getMotivoselect() {
		return motivoselect;
	}

	public void setMotivoselect(Motivo motivoselect) {
		this.motivoselect = motivoselect;
	}

	public List<Object> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Object> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuarioSelect() {

		return usuarioSelect;
	}

	public void setUsuarioSelect(Usuario usuarioSelect) {
		this.usuarioSelect = usuarioSelect;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConf_senha() {
		return conf_senha;
	}

	public void setConf_senha(String conf_senha) {
		this.conf_senha = conf_senha;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
