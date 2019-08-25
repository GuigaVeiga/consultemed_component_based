package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Agendamento;
import br.com.consultemed.utils.JPAUtils;

public class AgendamentoRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Agendamento> listaAgendamentos() {
		Query query = this.factory.createQuery("SELECT object(a) FROM Agendamento as a");
		return query.getResultList();
	}
	
	public Collection<Agendamento> listarAgendamentos() throws Exception {
		this.factory = emf.createEntityManager();
		List<Agendamento> contatos = new ArrayList<Agendamento>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Agendamento> query = factory.createNamedQuery("Agendamento.findAll", Agendamento.class);
			contatos = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return contatos;
	}

	public void salvarAgendamento(Agendamento agendamento) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (agendamento.getId() == null) {
				factory.persist(agendamento);
			} else {
				factory.merge(agendamento);
			}
			factory.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

	public void deleteById(Long id) throws Exception {
		this.factory = emf.createEntityManager();
		Agendamento agendamento = new Agendamento();

		try {

			agendamento = factory.find(Agendamento.class, id);
			factory.getTransaction().begin();
			factory.remove(agendamento);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}
	
}
