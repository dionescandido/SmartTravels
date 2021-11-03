package br.com.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

import br.com.controller.ConectaBancoController;
import br.com.model.Departamento;


@ManagedBean(name = "DepartamentoView")
@ViewScoped
public class DepartamentoView {

	private int cod;
	private String nome;
	private List<Object> listaDepartamento;
	private Departamento departamentoselect;

	// Botao Limpar dados
	public void BtnLimpar() {
		
		this.nome = "";

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;
		
		try {

			if (this.departamentoselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione um municipio !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(departamentoselect);
				
				this.nome = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento "+this.departamentoselect.getNome()+" Excluido com sucesso !", null);

			}

			

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o departamento "+this.departamentoselect.getNome() +"!!", "Possui vinculo no sistema");

		}
		
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna municipio do dialogo
	public void DepartamentoSelecionado(SelectEvent event) {

		Departamento m1 = (Departamento) event.getObject();
		this.departamentoselect = m1;
		this.cod = m1.getCod();
		this.nome = m1.getNome();

	}

	public void btnSalvarAction(ActionEvent actionEvent) {

		FacesMessage message = null;
		
		try {

			if (this.nome == "") {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencher o campo !", null);

			} else {

				Departamento m1 = new Departamento();
				m1.setCod(this.cod);
				m1.setNome(this.nome);

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);
				
				this.nome = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento "+this.nome+" salvo com sucesso!", null);

			}

		} catch (Exception e) {
			
			System.out.println("Erro ao gravar !!!!" + e);
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Departamento "+this.nome +" !!", null);

		}
		
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		Departamento m1 = new Departamento();

		this.listaDepartamento = new ConectaBancoController().Listar(m1);

	}
	
	// get e set
	
	
	public int getCod() {
		return cod;
	}

	public List<Object> getListaDepartamento() {
		return listaDepartamento;
	}

	public void setListaDepartamento(List<Object> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamentoselect() {
		return departamentoselect;
	}

	public void setDepartamentoselect(Departamento departamentoselect) {
		this.departamentoselect = departamentoselect;
	}





}
