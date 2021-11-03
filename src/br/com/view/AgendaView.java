package br.com.view;


import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import br.com.controller.ConectaBancoController;
import br.com.model.Solicitacao;


@ManagedBean(name = "AgendaView")
@ViewScoped
public class AgendaView {

	private ScheduleModel eventoModel;
    private DefaultScheduleEvent event = new DefaultScheduleEvent();
	private Solicitacao evento;
	private DefaultScheduleEvent solicitSelect = new DefaultScheduleEvent();
	private ScheduleEvent eve = new DefaultScheduleEvent();
	private List<Solicitacao> lstEvento;
	private Object obj;

	
	@PostConstruct
	public void listar() {

		eventoModel = new DefaultScheduleModel();
		
		try {
			
			Solicitacao s1 = new Solicitacao();
			this.lstEvento = new ConectaBancoController().ListarSolicitacaoAgenda(s1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for (Solicitacao lstsolicit : lstEvento) {
			
			
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setStartDate(lstsolicit.getDatinicial());
			
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(lstsolicit.getDatfinal());
			cal.add(Calendar.DATE, 1);
			
			evt.setEndDate(cal.getTime());
			evt.setTitle(lstsolicit.getUsuariosolicitante().getNome());
			evt.setDescription(lstsolicit.getCliente().getNome());
			evt.setAllDay(true);
			evt.setEditable(true);
			evt.setId(lstsolicit.getMotivo().getDescricao());
			
			
			if (lstsolicit.getStatus().equals("Finalizado")) {
				
				evt.setStyleClass("emp1");
				
			} else {
				
				evt.setStyleClass("emp2");
			
				
			}
			
	
			eventoModel.addEvent(evt);
		
			
		}
			

	}
	
	
	//seleciona o evento da agenda 
	public void selecionaevento(SelectEvent selectEvent) {
		
		this.eve =  (ScheduleEvent) selectEvent.getObject(); 
	}
	
	
	
	;
	// Careguar solicitacao
	public void btnBuscarAction() {

		Solicitacao s1 = new Solicitacao();
		this.lstEvento = new ConectaBancoController().ListarSolicitacaoAgenda(s1);

	}

	
	// get e set
	
	
	public ScheduleModel getEventoModel() {
		return eventoModel;
	}

	public DefaultScheduleEvent getEvent() {
		return event;
	}




	public void setEvent(DefaultScheduleEvent event) {
		this.event = event;
	}




	public ScheduleEvent getEve() {
		return eve;
	}


	public void setEve(ScheduleEvent eve) {
		this.eve = eve;
	}


	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public DefaultScheduleEvent getSolicitSelect() {
		return solicitSelect;
	}

	public void setSolicitSelect(DefaultScheduleEvent solicitSelect) {
		this.solicitSelect = solicitSelect;
	}

	public void setEventoModel(ScheduleModel eventoModel) {
		this.eventoModel = eventoModel;
	}

	public Solicitacao getEvento() {
		return evento;
	}

	public void setEvento(Solicitacao evento) {
		this.evento = evento;
	}

	public List<Solicitacao> getLstEvento() {
		return lstEvento;
	}

	public void setLstEvento(List<Solicitacao> lstEvento) {
		this.lstEvento = lstEvento;
	}
    
    
}