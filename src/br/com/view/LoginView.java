package br.com.view;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import br.com.controller.ConectaBancoController;
import br.com.model.Login;
import br.com.model.Usuario;

@ManagedBean(name = "LoginView")
@SessionScoped

public class LoginView implements Serializable {

	private static final long serialVersionUID = 1L;
	private ConectaBancoController usuarioDAO = new ConectaBancoController();
	private Usuario usuario = new Usuario();
	public static Date hoje = new Date();
	public DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	public static String teste = hoje.toString();
	private Usuario userlog = new Usuario();
	protected static HttpSession hs;

	// Limpar o usuario logado ao sair do sistema
	public void sairLogin() {

		usuario = new Usuario();

		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.invalidate();

			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Cadastro
	public void btnCadastroAction(ActionEvent actionEvent) {

		try {

			FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");

		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro no processamento. \n" + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	// Login

	public void envia() {

		try {

			usuario = usuarioDAO.getUsuario(usuario.getUser(), usuario.getSenha());

			if (usuario == null) {
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));

			} else {
				if (usuario.getPermicao() == 0) {

					usuario = new Usuario();

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario não possui permissão de acesso!",
									"entrar em contato com administrador do sistema!"));

				} else {

					FacesContext fc = FacesContext.getCurrentInstance();

					HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);

					session.setAttribute("usuariolog", usuario);

					hs = session;

					userlog = (Usuario) session.getAttribute("usuariolog");

					HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
					HttpServletRequest request = (HttpServletRequest) req;
					HttpSession session1 = (HttpSession) request.getSession(false);
					Usuario u1 = (Usuario) session1.getAttribute("usuariolog");
					session1.getLastAccessedTime();

					fc.getExternalContext().redirect("index.xhtml");

					System.out.println(
							"......................................................................................");

					System.out.println(u1.getNome());

					System.out.println(
							"......................................................................................");

					System.out.println(hs.getId());

					// Salva o ultimo login do usuario
					Date data = new Date();

					Login l1 = new Login();

					l1.setUsuarioLogado(usuario);
					l1.setDataLogin(data);

					ConectaBancoController c1 = new ConectaBancoController();
					c1.Salvar(l1);

					usuario = new Usuario();

					
					// dispara o cronometro para verificação diaria de viagens para enviar email
					CronometroView cv = new CronometroView();
					cv.execute(null);

				}
			}

		} catch (Exception e) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro no processamento. \n" + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	// get e set

	public static HttpSession getHs() {
		return hs;
	}

	public static void setHs(HttpSession hs) {
		LoginView.hs = hs;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getUserlog() {
		return userlog;
	}

	public void setUserlog(Usuario userlog) {
		this.userlog = userlog;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}