package br.com.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.controller.ConectaBancoController;
import br.com.model.Cliente;
import br.com.model.Solicitacao;
import br.com.model.Viagem;

@ManagedBean(name = "GraficoView")
@ViewScoped
public class GraficoView {

	private BarChartModel barra;
	private ConectaBancoController con = new ConectaBancoController();
	private List<Solicitacao> lstSolicitacao;
	private Solicitacao solicitacao = new Solicitacao();
	private Cliente cliente = new Cliente();
	private Object[] x;
	private Viagem viagem = new Viagem();
	private List<Viagem> lstViagem;
	private int a;
	private List<Solicitacao> lstQTDSolicitacao;
	private int z = 0;

	@PostConstruct
	public void graficoo() {

		try {

			this.lstSolicitacao = con.ListarSolicitacaoporCliente(solicitacao);
			
			

			x = con.ListarQtdSolicitacao(solicitacao).toArray();
			
			a = lstSolicitacao.size();

			barra = new BarChartModel();	
			
					
			for (int i = 0; i <= a - 1; i++) {

				solicitacao = lstSolicitacao.get(i);
				
				ChartSeries bar = new ChartSeries();

				bar.set(solicitacao.getStatus(), (Number) x[i]);
				
				bar.setLabel(solicitacao.getCliente().getNome());
				
							
				barra.addSeries(bar);

			}

			lstQTDSolicitacao = con.ListarQtdSolicitacaoFinalizado(solicitacao);
			
		
			barra.setTitle("Clientes");
			barra.setAnimate(true);
			barra.setLegendPosition("ne");

			Axis xAxis = barra.getAxis(AxisType.X);
			xAxis.setLabel("2020");

			Axis yAxis = barra.getAxis(AxisType.Y);
			yAxis.setLabel("Total de Viagens");
			yAxis.setMin(0);
			yAxis.setMax(lstQTDSolicitacao.size());
	
			

		} catch (

		Exception e) {
			// TODO: handle exception

		}
	}

	// get e set

	public List<Viagem> getLstViagem() {
		return lstViagem;
	}


	public List<Solicitacao> getLstQTDSolicitacao() {
		return lstQTDSolicitacao;
	}

	public void setLstQTDSolicitacao(List<Solicitacao> lstQTDSolicitacao) {
		this.lstQTDSolicitacao = lstQTDSolicitacao;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setLstViagem(List<Viagem> lstViagem) {
		this.lstViagem = lstViagem;
	}

	public BarChartModel getBarra() {
		return barra;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Object[] getX() {
		return x;
	}

	public void setX(Object[] x) {
		this.x = x;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setBarra(BarChartModel barra) {
		this.barra = barra;
	}

	public ConectaBancoController getCon() {
		return con;
	}

	public void setCon(ConectaBancoController con) {
		this.con = con;
	}

	public List<Solicitacao> getLstSolicitacao() {
		return lstSolicitacao;
	}

	public void setLstSolicitacao(List<Solicitacao> lstSolicitacao) {
		this.lstSolicitacao = lstSolicitacao;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

}