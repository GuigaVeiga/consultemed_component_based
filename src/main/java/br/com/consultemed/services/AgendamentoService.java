package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Agendamento;
import br.com.consultemed.repository.repositories.AgendamentoRepository;

public class AgendamentoService {

	@Inject
	private AgendamentoRepository dao;
	
	public List<Agendamento> listaAgendamento(){
		return this.dao.listaAgendamentos();
	}
	
	public void salvarAgendamento(Agendamento Agendamento) {
		this.dao.salvarAgendamento(Agendamento);
	}
	
	public void deletarAgendamento(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
}
