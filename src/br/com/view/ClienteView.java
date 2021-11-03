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
import br.com.model.Cliente;
import br.com.model.Municipio;

@ManagedBean(name = "ClienteView")
@ViewScoped
public class ClienteView {

	private int cod;
	private Cliente clienteselect;
	private Municipio municipioselect;
	private List<Object> listaCliente;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String cnpj;


	// Limpar campos
	public void BtnLimpar() {

		this.municipioselect = null;
		this.municipioselect = null;
		this.email = "";
		this.cnpj = "";
		this.email = "";
		this.endereco = "";
		this.nome = "";
		this.telefone = "";

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;

		try {

			if (this.clienteselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione um Cliente !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(clienteselect);

				this.municipioselect = null;
				this.municipioselect = null;
				this.municipioselect = null;
				this.email = "";
				this.cnpj = "";
				this.email = "";
				this.endereco = "";
				this.nome = "";
				this.telefone = "";

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Cliente Excluido com sucesso !", null);

			}

		} catch (Exception e) {
			System.out.println("Erro ao Excluir !!!!" + e);

			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao excluir o Cliente " + this.clienteselect.getNome() + " !", "Possui vinculo no sistema");

		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna Municipio do dialogo
	public void MunicipioSelecionado(SelectEvent event) {

		Municipio m1 = (Municipio) event.getObject();
		this.municipioselect = m1;

	}

	// Retorna Cliente do dialogo
	public void CleinteSelecionado(SelectEvent event) {

		Cliente m1 = (Cliente) event.getObject();
		this.clienteselect = m1;
		this.cod = m1.getCod();
		this.clienteselect = m1;
		this.cnpj = m1.getCnpj();
		this.email = m1.getEmail();
		this.endereco = m1.getEndereco();
		this.nome = m1.getNome();
		this.telefone = m1.getTelefone();
		this.municipioselect = m1.getMunicipio();
	}

	public void btnBuscarAction(ActionEvent actionEvent) {

		Cliente m1 = new Cliente();

		this.listaCliente = new ConectaBancoController().Listar(m1);

	}

	// salvar funcionario
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.cnpj == "" || this.nome == "" || this.municipioselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Preencher todos os campos obrigatorios !",
						null);

			} else {

				Cliente c1 = new Cliente();

				Municipio d1 = new Municipio();
				d1.setCod(this.municipioselect.getCod());

				c1.setEmail(this.email);
				c1.setEndereco(this.endereco);
				c1.setNome(this.nome);
				c1.setTelefone(this.telefone);
				c1.setCnpj(this.cnpj);
				c1.setCod(this.cod);

				c1.setMunicipio(d1);

				ConectaBancoController fc = new ConectaBancoController();
				fc.Editar(c1);

				this.municipioselect = null;
				this.email = "";
				this.cnpj = "";
				this.email = "";
				this.endereco = "";
				this.nome = "";
				this.telefone = "";
				;

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// abrir dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("SelecaoCliente", opcoes, null);
	}

	public void selecionar(Cliente m1) {
		PrimeFaces.current().dialog().closeDynamic(m1);
	}

	// get e set

	public Cliente getClienteselect() {

		return clienteselect;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Object> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Object> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public void setClienteselect(Cliente clienteselect) {
		this.clienteselect = clienteselect;
	}

	// Para objeto nao gerar nulo no JSF

	public Municipio getMunicipioselect() {

		if (municipioselect == null) {
			municipioselect = new Municipio();
		}

		return municipioselect;
	}

	// Para objeto nao gerar nulo no JSF
	public void setMunicipioelect(Municipio municipioselect) {
		this.municipioselect = municipioselect;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setMunicipioselect(Municipio municipioselect) {
		this.municipioselect = municipioselect;
	}

}
