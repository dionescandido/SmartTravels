package br.com.view;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import br.com.controller.ConectaBancoController;
import br.com.model.Departamento;
import br.com.model.Funcionario;


@ManagedBean(name = "FuncionarioView")
@ViewScoped
public class FuncionarioView {

	private int cod;
	private Funcionario funcionarioselect;
	private Departamento departamentoselect;
	private List<Object> listaFuncionario;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String sexo;
	private String cpf;
	private String cnh;
	private Date datnasc;

	
	
	// Botao Limpar dados
	public void BtnLimpar() {

		this.departamentoselect = null;
		this.cnh = "";
		this.cpf = "";
		this.datnasc = null;
		this.email = "";
		this.endereco = "";
		this.nome = "";
		this.sexo = "";
		this.telefone = "";
	

	}

	// Botao Exlcuir
	public void btnExcluir() {

		FacesMessage message = null;
		
		try {

			if (this.funcionarioselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecione um Funcionario !", null);

			} else {

				ConectaBancoController mc = new ConectaBancoController();
				mc.Excluir(funcionarioselect);

				this.departamentoselect = null;
				this.cnh = "";
				this.cpf = "";
				this.datnasc = null;
				this.email = "";
				this.endereco = "";
				this.nome = "";
				this.sexo = "";
				this.telefone = "";
		

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Funcionario Excluido com sucesso !", null);

			}

		} catch (Exception e) {
			System.out.println("Erro ao Excluir !!!!" + e);

			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao excluir o Funcionario " + this.funcionarioselect.getNome() + " !", "Possui vinculo no sistema");

		}
		
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// Retorna departamento do dialogo
	public void DepartamentoSelecionado(SelectEvent event) {

		Departamento m1 = (Departamento) event.getObject();
		this.departamentoselect = m1;

	}

	// Retorna Funcionario do dialogo
	public void FuncionarioSelecionado(SelectEvent event) {

		Funcionario m1 = (Funcionario) event.getObject();
		this.funcionarioselect = m1;
		this.nome = m1.getNome();
		this.cpf = m1.getCpf();
		this.cnh = m1.getCnh();
		this.datnasc = m1.getDatnasc();
		this.departamentoselect = m1.getDepartamento();
		this.email = m1.getEmail();
		this.endereco = m1.getEndereco();
		this.sexo = m1.getSexo();
		this.telefone = m1.getTelefone();
		this.cod = m1.getCod();

	}

	// Buscar os Funcionarios
	public void btnBuscarAction(ActionEvent actionEvent) {

		Funcionario m1 = new Funcionario();

		this.listaFuncionario = new ConectaBancoController().Listar(m1);

	}

	// salvar funcionario
	public void btnSalvarAction(ActionEvent actionEvent) {

		try {

			FacesMessage message = null;

			if (this.nome == null || this.cpf == "" || this.departamentoselect == null) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, Selecionar uma pessoa !", null);

			} else {

				Funcionario f1 = new Funcionario();

				Departamento d1 = new Departamento();
				d1.setCod(this.departamentoselect.getCod());

				f1.setDepartamento(d1);
				f1.setCnh(this.cnh);
				f1.setCpf(this.cpf);
				f1.setDatnasc(this.datnasc);
				f1.setEmail(this.email);
				f1.setEndereco(this.endereco);
				f1.setSexo(this.sexo);
				f1.setTelefone(this.telefone);
				f1.setNome(this.nome);
				f1.setCod(this.cod);

				ConectaBancoController fc = new ConectaBancoController();
				fc.Editar(f1);

				this.departamentoselect = null;
				this.cnh = "";
				this.cpf = "";
				this.datnasc = null;
				this.email = "";
				this.endereco = "";
				this.nome = "";
				this.sexo = "";
				this.telefone = "";
			

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", null);

			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			System.out.println("Erro ao gravar !!!!" + e);
		}
	}

	// get e set

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Date getDatnasc() {
		return datnasc;
	}

	public void setDatnasc(Date datnasc) {
		this.datnasc = datnasc;
	}

	public List<Object> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Object> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Funcionario getFuncionarioselect() {

		if (funcionarioselect == null) {
			funcionarioselect = new Funcionario();
		}

		return funcionarioselect;
	}

	public void setFuncionarioselect(Funcionario funcionarioselect) {
		this.funcionarioselect = funcionarioselect;
	}

	// Para objeto nao gerar nulo no JSF

	public Departamento getDepartamentoselect() {

		if (departamentoselect == null) {
			departamentoselect = new Departamento();
		}

		return departamentoselect;
	}

	// Para objeto nao gerar nulo no JSF
	public void setDepartamentoselect(Departamento departamentoselect) {
		this.departamentoselect = departamentoselect;
	}

}
