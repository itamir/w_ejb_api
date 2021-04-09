package br.ufrn.imd.material.controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufrn.imd.material.dominio.Usuario;
import br.ufrn.imd.material.negocio.UsuarioService;

@Named("usuarioMBean")
@SessionScoped
public class UsuarioMBean implements Serializable {
	
	@EJB
	private UsuarioService usuarioService;
	
	private Usuario usuario;
	
	private Usuario usuarioLogado;
		
	public UsuarioMBean() {
		usuario = new Usuario();
	}

	public String logar() {
		Usuario usuarioBd = usuarioService.getUsuario(usuario.getLogin(), usuario.getSenha());
		if(usuarioBd != null) {
			usuarioLogado = usuarioBd;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
			return "/pages/index.jsf";
		}
		//n�o existe
		else {
			FacesMessage msg = new FacesMessage("Usu�rio n�o encontrado.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			return null;
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
