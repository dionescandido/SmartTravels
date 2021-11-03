package br.com.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import br.com.model.Cliente;
import br.com.model.Despesas;
import br.com.model.Estorno;
import br.com.model.Funcionario;
import br.com.model.Motivo;
import br.com.model.Municipio;
import br.com.model.Solicitacao;
import br.com.model.TipoDespesas;
import br.com.model.Usuario;
import br.com.model.Veiculo;
import br.com.model.Viagem;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean
@ViewScoped
public class ConectaBancoController {

	private EntityManagerFactory manager = Persistence.createEntityManagerFactory("SmartTravels");

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	Usuario userlog = (Usuario) session.getAttribute("usuariolog");

	// metodo para imprimir relatorio de viagens em grafico
	public void GerarGraficoViagens(List<Viagem> lstviagem) {
		try {

			String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/Reports/RelGraficoViagens.jasper") + "/";

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lstviagem);

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, null, beanCollectionDataSource);

			JasperExportManager.exportReportToPdf(relatorio);
			JasperViewer.viewReport(relatorio, false);

		} catch (Exception erro) {

			erro.printStackTrace();

		}
	}

	// metodo para imprimir relatorio de objeto
	public void GerarRelatorio(List<Viagem> lstviagem, Veiculo v1, Cliente c1, Funcionario f1, int motivo) {
		try {

			String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/Reports/RelMunicipio.jasper") + "/";

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lstviagem);

			HashMap<String, Object> parametro = new HashMap<String, Object>();

			// passando o Parametro
			parametro.put("veiculo", v1);
			parametro.put("cliente", c1);
			parametro.put("funcionario", f1);
			parametro.put("motivo", motivo);

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametro, beanCollectionDataSource);

			JasperExportManager.exportReportToPdf(relatorio);
			JasperViewer.viewReport(relatorio, false);

		} catch (Exception erro) {

			erro.printStackTrace();

		}
	}

	// Metodo para imprimir relatorio de municipio
	public void GerarRelatorioDespesas(List<Despesas> obj, Cliente c1, Funcionario f1, TipoDespesas t1, Viagem v1) {
		try {

			String caminho = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/Reports/RelDespesas.jasper") + "/";

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(obj);

			HashMap<String, Object> parametro = new HashMap<String, Object>();

			// passando o Parametro
			parametro.put("cliente", c1);
			parametro.put("funcionario", f1);
			parametro.put("tipodespesas", t1);
			parametro.put("viagem", v1);

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametro, beanCollectionDataSource);

			JasperExportManager.exportReportToPdf(relatorio);
			JasperViewer.viewReport(relatorio, false);

		} catch (Exception erro) {

			erro.printStackTrace();

		}
	}

	// metodo de validar login
	public Usuario getUsuario(String user, String senha) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SmartTravels");
		EntityManager em = factory.createEntityManager();

		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.user = :user and u.senha = :senha")
					.setParameter("user", user).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

	// Metodo Editar
	public void Editar(Object obj) {
		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.merge(obj);
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}

	// Escluir
	public void Excluir(Object obj) {

		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		if (!entitymanager.contains(obj)) {
			obj = entitymanager.merge(obj);
		}
		entitymanager.remove(obj);
		entitymanager.getTransaction().commit();
		entitymanager.close();

	}

	// Salvar
	public void Salvar(Object obj) {

		EntityManager entitymanager = manager.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(obj);
		entitymanager.getTransaction().commit();
		entitymanager.close();

	}

	// Metodo listar
	public List<Object> Listar(Object obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Object> query = null;

		query = entitymanager.createQuery("select m1 from " + obj.getClass().getName() + " m1 order by  m1.cod desc", Object.class);

		return query.getResultList();

	}

	// Metodo listar
	public List<Estorno> ListarEstorno(Estorno e1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Estorno> query = null;

		query = entitymanager.createQuery("select m1 from Estorno m1", Estorno.class);

		return query.getResultList();

	}

	// Metodo verifica se possui Solicitação com a mesma data para o mesmo veiculo
	public Boolean ListarSolicitacaoPorData(Solicitacao obj, Veiculo obj3) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery(
				"select m1 from Solicitacao m1 where :datai between m1.datinicial and m1.datfinal and :dataf between m1.datinicial and m1.datfinal and m1.parecer = :Deferido and m1.veiculo = :carro",
				Solicitacao.class);
		query.setParameter("datai", obj.getDatinicial());
		query.setParameter("dataf", obj.getDatfinal());
		query.setParameter("carro", obj3);
		query.setParameter("Deferido", "Deferido");

		if (query.getResultList().isEmpty() == true) {

			return true;

		} else {

			return false;

		}

	}

	// Metodo verifica se possui Solicitação com a mesma data para o mesmo Usuario
	public Boolean ListarSolicitacaoPorUsuario(Solicitacao obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery(
				"select m1 from Solicitacao m1 where :datai between m1.datinicial and m1.datfinal and :dataf between m1.datinicial and m1.datfinal and m1.parecer = :Deferido and m1.usuariosolicitante = :usuario",
				Solicitacao.class);
		query.setParameter("datai", obj.getDatinicial());
		query.setParameter("dataf", obj.getDatfinal());
		query.setParameter("Deferido", "Deferido");

		query.setParameter("usuario", userlog);

		if (query.getResultList().isEmpty() == true) {

			return true;

		} else {

			return false;

		}

	}

	// Metodo listar solicitação para relatorio de viagens
	public List<Solicitacao> ListarViagensRel(Solicitacao s1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery("select m1 from Solicitacao m1 where m1.status = :Status", Solicitacao.class);
		query.setParameter("Status", "Finalizado");

		return query.getResultList();

	}

	// Metodo listar viagens para relatorio de viagens
	public List<Viagem> ListarViagens(Viagem s1, Date di, Date df) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Viagem> query = null;

		query = entitymanager.createQuery(
				"select m1 from Viagem m1 where m1.dat_inicial between :datinicial and :datfinal", Viagem.class);

		query.setParameter("datinicial", di);
		query.setParameter("datfinal", df);

		return query.getResultList();

	}

	// Metodo listar viagens para relatorio de viagens

	public List<Viagem> ListarUltimaViagen(Viagem s1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Viagem> query = null;

		query = entitymanager.createQuery("select m1 from Viagem m1 order by m1 desc", Viagem.class);

		return query.getResultList();

	}

	// Metodo listar viagens de viagens
	public List<Viagem> ListaDeViagens(Viagem s1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Viagem> query = null;

		query = entitymanager.createQuery("select m1 from Viagem m1 ", Viagem.class);

		return query.getResultList();

	}

	// Metodo listar viagens de viagens
	public List<Viagem> ListaDeViagensporCliente(int s1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Viagem> query = null;

		query = entitymanager.createQuery("select m1 from Viagem m1 where m1.cod =: codviagem", Viagem.class);
		query.setParameter("codviagem", s1);

		return query.getResultList();

	}

	// Metodo listar e filtra apenas as Solicitacao do usuario logado
	public List<Solicitacao> ListarSolicitacao(Solicitacao obj, Usuario u1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery("select m1 from Solicitacao m1 where m1.usuariosolicitante = :usuario order by  m1.cod desc",
				Solicitacao.class);

		query.setParameter("usuario", u1);

		return query.getResultList();

	}

	// Metodo listar e verificar se possui viagem para disparar email
	public List<Solicitacao> VerificarViagemAmanha(Solicitacao s1)  {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery(
				" select m1 from Solicitacao m1 where m1.parecer = :deferido and m1.datinicial = :dat",
				Solicitacao.class);

		Date dataTeste = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(dataTeste);
		cal.add(Calendar.DATE, 1);
		dataTeste = cal.getTime();

		/*
		 * SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); String
		 * datFormat = formatador.format(dataTeste); System.out.println(datFormat);
		 */

		query.setParameter("dat", dataTeste);
		query.setParameter("deferido", "Deferido");

		
		if (query.getResultList().isEmpty() == true) {

			return null;

		} else {

			return query.getResultList();

		}
		
		
	}

	// Metodo listar e filtra apenas as Solicitacao do usuario logado e autorizado e
	// finalizado
	public List<Solicitacao> ListarSolicitacaoAutorizado(Solicitacao obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery(
				"select m1 from Solicitacao m1 where m1.usuariosolicitante = :usuario and m1.parecer = :deferido and m1.status = :aguardando",
				Solicitacao.class);

		query.setParameter("usuario", userlog);
		query.setParameter("deferido", "Deferido");
		query.setParameter("aguardando", "Aguardando");

		return query.getResultList();

	}

	// Metodo listar e filtra apenas as Solicitacao do usuario logado e autorizado
	public List<Solicitacao> ListarSolicitacaoAgenda(Solicitacao obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery("select m1 from Solicitacao m1 where m1.parecer = :deferido",
				Solicitacao.class);

		query.setParameter("deferido", "Deferido");

		return query.getResultList();

	}

	// metodo para combo box de motivo
	public List<Motivo> ListarMotivo(Motivo m1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Motivo> query = null;

		query = entitymanager.createQuery("select m1 from Motivo m1", Motivo.class);

		return query.getResultList();

	}

	// metodo para combo box de veiculo
	public List<Veiculo> ListarVeiculo(Veiculo m1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Veiculo> query = null;

		query = entitymanager.createQuery("select m1 from Veiculo m1", Veiculo.class);

		return query.getResultList();

	}

	// metodo para combo box de motivo
	public List<Cliente> ListarCliente(Cliente m1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Cliente> query = null;

		query = entitymanager.createQuery("select m1 from Cliente m1", Cliente.class);

		return query.getResultList();

	}

	// metodo para combo box de funcionarios
	public List<Funcionario> Listarfuncionario(Funcionario m1) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Funcionario> query = null;

		query = entitymanager.createQuery("select m1 from Funcionario m1", Funcionario.class);

		return query.getResultList();

	}

	// Metodo listar Despesas por usuario
	public List<Despesas> ListarDespesasUsuarioo(Despesas obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Despesas> query = null;

		query = entitymanager.createQuery("select m1 from Despesas m1 where m1.usuario = :usuario", Despesas.class);
		query.setParameter("usuario", userlog);

		return query.getResultList();

	}

	// Metodo listar Despesas por usuario
	public List<Despesas> ListarDespesas(Despesas obj, Date di, Date df) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Despesas> query = null;

		query = entitymanager.createQuery(
				"select m1 from Despesas m1 join Viagem v1 where m1.viagem = v1 and v1.dat_inicial between :datinicial and :datfinal group by m1.cod",
				Despesas.class);

		query.setParameter("datinicial", di);
		query.setParameter("datfinal", df);

		return query.getResultList();

	}

	// Metodo listar quantidade de viagens
	public List<Solicitacao> ListarQtdSolicitacao(Solicitacao obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery(
				"select count(m1) from Solicitacao m1 where m1.status = :finalizado group by m1.cliente",
				Solicitacao.class);
		query.setParameter("finalizado", "Finalizado");

		return query.getResultList();

	}

	// Metodo listar solicitações finalizadas
	public List<Solicitacao> ListarQtdSolicitacaoFinalizado(Solicitacao obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery("select m1 from Solicitacao m1 where m1.status = :finalizado",
				Solicitacao.class);
		query.setParameter("finalizado", "Finalizado");

		return query.getResultList();

	}

	// Metodo listar solicitação finalizadas por cliente
	public List<Solicitacao> ListarSolicitacaoporCliente(Solicitacao obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Solicitacao> query = null;

		query = entitymanager.createQuery(
				"select m1 from Solicitacao m1 where m1.status = :finalizado group by m1.cliente", Solicitacao.class);
		query.setParameter("finalizado", "Finalizado");

		return query.getResultList();

	}

	// Metodo listar municipio
	public List<Municipio> ListarMunicipio(Municipio obj) {

		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Municipio> query = null;

		query = entitymanager.createQuery("select m1 from Municipio m1", Municipio.class);

		return query.getResultList();

	}

}
