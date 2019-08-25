/**
 * 
 */
package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Usuario;
import br.com.consultemed.repository.repositories.UsuarioRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class UsuarioService {

	@Inject
	private UsuarioRepository dao;
	
	public List<Usuario> listaUsuarios(){
		return this.dao.listaUsuarios();
	}
	
	public void salvarUsuario(Usuario usuario) {
		this.dao.salvarUsuario(usuario);
	}
	
	public boolean getUserByMail(String email) {
		if(this.dao.getUserByMail(email)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deletarUsuario(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
}
