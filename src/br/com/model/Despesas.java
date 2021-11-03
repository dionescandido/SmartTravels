package br.com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity	
public class Despesas {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int cod;
		private TipoDespesas tipodespesas;
		private Double valor;
		private String numNota;
		private Viagem viagem;
		private Funcionario funcionario;
		private Usuario usuario;
		private Cartao cartao;
		private String tipopagamento;
		
	
		
		//get e set
		
		public int getCod() {
			return cod;
		}
		
		public String getTipopagamento() {
			return tipopagamento;
		}

		public void setTipopagamento(String tipopagamento) {
			this.tipopagamento = tipopagamento;
		}

		public Cartao getCartao() {
			return cartao;
		}

		public void setCartao(Cartao cartao) {
			this.cartao = cartao;
		}

		public void setCod(int cod) {
			this.cod = cod;
		}
		public TipoDespesas getTipodespesas() {
			return tipodespesas;
		}
		public void setTipodespesas(TipoDespesas tipodespesas) {
			this.tipodespesas = tipodespesas;
		}
		
		public Double getValor() {
			return valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
		
		public String getNumNota() {
			return numNota;
		}
		public void setNumNota(String numNota) {
			this.numNota = numNota;
		}

		public Viagem getViagem() {
			return viagem;
		}
		public void setViagem(Viagem viagem) {
			this.viagem = viagem;
		}
		public Funcionario getFuncionario() {
			return funcionario;
		}
		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
		}
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		
		
		
	}
	

