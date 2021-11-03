package br.com.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import br.com.controller.ConectaBancoController;
import br.com.model.Municipio;

@ManagedBean(name = "MunicipioView")
@ViewScoped
public class MunicipioView {

	private int cod;
	private String nome;
	private List<Municipio> listaMunicipio;
	private Municipio municipioselect;



	// Botao Limpar dados
	public void BtnLimpar() {

		this.nome = "";

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.municipioselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione um municipio !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(municipioselect);

				this.nome = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Municipio " + this.municipioselect.getNome() + " Excluido com sucesso !", null);

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Municipio " + this.municipioselect.getNome() + " não pode ser excluido",
					"Possui algum vinculo no sistema");
			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna municipio do dialogo
	public void MunicipioSelecionado(SelectEvent event) {

		Municipio m1 = (Municipio) event.getObject();
		this.municipioselect = m1;
		this.cod = m1.getCod();
		this.nome = m1.getNome();

	}

	public void btnSalvarAction(ActionEvent actionEvent) {

		FacesMessage message = null;

		try {

			if (this.nome == "") {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Preencha o nome do municipio !", null);

			} else {

				Municipio m1 = new Municipio();
				m1.setCod(this.cod);
				m1.setNome(this.nome);

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);

				this.nome = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipio Salvo com sucesso!" , null);

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao salvar o municipio " + this.municipioselect.getNome(),
					" Entrar em contato com o administrador do sistema");

			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		Municipio m1 = new Municipio();

		this.listaMunicipio = new ConectaBancoController().ListarMunicipio(m1);

	}

	// get e set

	public int getCod() {
		return cod;
	}

	public List<Municipio> getListaMunicipio() {
		return listaMunicipio;
	}

	public void setListaMunicipio(List<Municipio> listaMunicipio) {
		this.listaMunicipio = listaMunicipio;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Municipio getMunicipioselect() {
		return municipioselect;
	}

	public void setMunicipioselect(Municipio municipioselect) {
		this.municipioselect = municipioselect;
	}

}
