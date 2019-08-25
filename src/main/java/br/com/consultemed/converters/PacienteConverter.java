package br.com.consultemed.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

@FacesConverter(forClass = Paciente.class, value = "pacienteConverter")
public class PacienteConverter implements Serializable, Converter {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public Object getAsObject(FacesContext context, UIComponent component, String string) {

		System.out.println("chamada do nome:" + string);
		if (string != null && !string.isEmpty()) {
			return (Paciente) manager.find(Paciente.class, new Long(string));
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		System.out.println("chamada do objeto:" + object);
		if (object != null && object instanceof Paciente) {
			System.out.println("irÃ¡ retornar: " + ((Paciente) object).getId().toString());
			return ((Paciente) object).getId().toString();
		}
		return null;
	}

}
