package br.com.consultemed.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.models.Medico;
import br.com.consultemed.utils.JPAUtils;

@FacesConverter (forClass = Medico.class, value = "medicoConverter")
public class MedicoConverter implements Serializable, Converter {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public Object getAsObject(FacesContext context, UIComponent component, String string) {

		System.out.println("chamada do nome:" + string);
		if (string!=null && !string.isEmpty()) {
			return (Medico) manager.find(Medico.class, new Long(string));
		}
		return null;
	}
	
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		System.out.println("chamada do objeto:" + object);
		if(object != null && object instanceof Medico) {
			System.out.println("irÃ¡ retornar: " + ((Medico)object).getId().toString());
			return ((Medico)object).getId().toString();
		}
		return null;
	}

}
