package br.com.view;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

import br.com.controller.ConectaBancoController;
import br.com.model.Veiculo;

@ManagedBean(name = "VeiculoView")
@ViewScoped
public class VeiculoView {

	private int cod;
	private String modelo;
	private String placa;
	private String cor;
	private String renavam;
	private String ano;
	private List<Object> listaVeiculo;
	private Veiculo veiculoselect;

	// Botao Limpar dados
	public void BtnLimpar() {

		this.modelo = "";
		this.ano = "";
		this.cor = "";
		this.placa = "";
		this.renavam = "";

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.veiculoselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione um veiculo !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(veiculoselect);

				this.modelo = "";
				this.ano = "";
				this.cor = "";
				this.placa = "";
				this.renavam = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Veiculo " + this.veiculoselect.getModelo(),
						" Excluido com sucesso !");

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao excluir o veiculo " + this.veiculoselect.getModelo(), "possui vinculo no sistema");

			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna veiculo do dialogo
	public void VeiculoSelecionado(SelectEvent event) {

		Veiculo m1 = (Veiculo) event.getObject();
		this.veiculoselect = m1;
		this.cod = m1.getCod();
		this.modelo = m1.getModelo();
		this.cor = m1.getCor();
		this.ano = m1.getAno();
		this.placa = m1.getPlaca();
		this.renavam = m1.getRenavam();


	}

	public void btnSalvarAction(ActionEvent actionEvent) {

		FacesMessage message = null;

		try {

			if (this.modelo == "" || this.placa == "" ) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Preencha a o modelo e a placa !", null);

			} else {

				Veiculo m1 = new Veiculo();
				m1.setCod(this.cod);
				m1.setModelo(this.modelo);
				m1.setAno(this.ano);
				m1.setCor(this.cor);
				m1.setPlaca(this.placa);
				m1.setRenavam(this.renavam);

				ConectaBancoController mc = new ConectaBancoController();
				mc.Editar(m1);

				this.modelo = "";
				this.ano = "";
				this.cor = "";
				this.placa = "";
				this.renavam = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Veiculo " + this.modelo + " Salvo com sucesso!",
						null);

			}

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao salvar o Veiculo " + this.modelo,
					"Entra em contato com o administrador do sistema");
			System.out.println("Erro ao gravar !!!!" + e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		Veiculo m1 = new Veiculo();

		this.listaVeiculo = new ConectaBancoController().Listar(m1);

	}

	// get e set

	public int getCod() {
		return cod;
	}

	public List<Object> getListaVeiculo() {
		return listaVeiculo;
	}

	public void setListaVeiculo(List<Object> listaVeiculo) {
		this.listaVeiculo = listaVeiculo;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Veiculo getVeiculoselect() {
		return veiculoselect;
	}

	public void setVeiculoselect(Veiculo veiculoselect) {
		this.veiculoselect = veiculoselect;
	}

}
