package br.ufrn.imd.material.negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.material.dominio.Usuario;
import br.ufrn.imd.material.repositorios.UsuarioRepositorio;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioRepositorio usuarioRepositorio;
	
	public Usuario getUsuario(String login, String senha) {
		return usuarioRepositorio.getUsuario(login, senha);
	}
	
}
