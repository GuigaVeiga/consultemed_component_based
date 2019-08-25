package br.com.consultemed.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.models.Agendamento;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.services.AgendamentoService;
import br.com.consultemed.services.MedicoService;
import br.com.consultemed.services.PacienteService;
import br.com.consultemed.utils.JPAUtils;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class AgendamentoController {

		
	@Getter
	@Setter
	private List<Agendamento> agendamentos;
	
	@Getter
	@Setter
	private List<Agendamento> agBusca;

	@Inject
	@Getter
	@Setter
	private Agendamento agendamento;
		
	@Getter
	@Setter
	private Agendamento agendamentoEditar;
		
	@Inject
	private AgendamentoService service;
	
	@Inject
	private PacienteService pservice;

	@Getter
	@Setter
	private List<Paciente> pacientes;
	
	@Inject
	private MedicoService mservice;

	@Getter
	@Setter
	private List<Medico> medicos;
	
	@PostConstruct
	public void init() {
		this.listarPacientes();
		this.listarMedicos();
	}
	
	public List<Paciente> listarPacientes() {
		this.pacientes = pservice.listaPaciente();
		return pacientes;
	}
	
	public List<Medico> listarMedicos() {
		this.medicos = mservice.listaMedico();
		return medicos;
	}

		
	public String editar() {
		this.agendamento = this.agendamentoEditar;
		return "/pages/agendamentos/addAgendamentos.xhtml";
	}
	
	public String excluir() throws Exception {
		this.agendamento = this.agendamentoEditar;
		this.service.deletarAgendamento(this.agendamento.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/agendamentos/agendamentos.xhtml?faces-redirect=true";
	}
	
	public String novoAgendamento() {
		this.agendamento = new Agendamento();
		return "/pages/agendamentos/addAgendamentos.xhtml?faces-redirect=true";
	}
		
	public String addAgendamento() {
		this.service.salvarAgendamento(this.agendamento);
		return "/pages/agendamentos/agendamentos.xhtml?faces-redirect=true";
	}
		
	public List<Agendamento> listaAgendamentos(){
		this.agendamentos = this.service.listaAgendamento();
		return agendamentos;
	}
	
	
	/*
	 * 
	 * public boolean filterByDate(Object value, Object filter, Locale locale) {
	 
	    if( filter == null ) {
	        return true;
	    }

	    if( value == null ) {
	        return false;
	    }

	    Date dt2 = (Date) filter;

	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", locale);
	    String date1 = sdf.format(value);
	    String date2 = sdf.format(dt2);
	    boolean status = date2.equals(date1);
	    return status;
	}*/	
	
}
