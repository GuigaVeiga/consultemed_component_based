/**
 * 
 */
package br.com.consultemed.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class PacienteRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public List<Paciente> listaPacientes() {
		Query query = this.factory.createQuery("SELECT object(m) FROM Paciente as m");
		return query.getResultList();
	}
	
	public boolean getPacienteByCpf(String cpf) {
		try{

		String jpql = "SELECT object(m) FROM Paciente as m WHERE m.cpf= :cpfPaciente";
		Query query = this.factory.createQuery(jpql);
		query.setParameter("cpfPaciente", cpf);
		
		if (query.getSingleResult()!=null) {
				return true;
			}else {return false;
			}
		}catch (Exception e) {
			return false;
		}
	}	
	

	public Collection<Paciente> listarMedicos() throws Exception {
		this.factory = emf.createEntityManager();
		List<Paciente> contatos = new ArrayList<Paciente>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Paciente> query = factory.createNamedQuery("Paciente.findAll", Paciente.class);
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

	public void salvarPaciente(Paciente paciente) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (paciente.getId() == null) {
				factory.persist(paciente);
			} else {
				factory.merge(paciente);
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
		Paciente paciente = new Paciente();

		try {

			paciente = factory.find(Paciente.class, id);
			factory.getTransaction().begin();
			factory.remove(paciente);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}

}
