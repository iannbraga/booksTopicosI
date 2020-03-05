package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.application.Util;
import br.unitins.books.model.Entity;
import br.unitins.books.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Usuario> listaUsuario;

	public void incluir() {
		if(getUsuario().getNome().trim().equals("") || getUsuario().getLogin().trim().equals("") || getUsuario().getSenha().trim().equals("") || getUsuario().getEmail().trim().equals("")) {
			Util.addErrorMessage("Preencha todos os campos!!");
			return;
		}
		
		getUsuario().setId(proximoId());
		getListaUsuario().add(getUsuario());
		limpar();
	}
	
	public void editar(Usuario usuario) {
		
		setUsuario(usuario.getClone());
	}
	
	public void remover() {
		getListaUsuario().remove(getUsuario());
		limpar();
	}
	
	public void limpar() {
		usuario = null;
	}
	
	private int proximoId() {
		int resultado = 0;
		for(Usuario usuario : listaUsuario) {
			if(usuario.getId() > resultado)
				resultado = usuario.getId();
		}
		return ++resultado;
	}
	
	public List<Usuario> getListaUsuario() {
		if(listaUsuario == null)
			listaUsuario = new ArrayList<Usuario>();
		return listaUsuario;
	}
	
	public Usuario getUsuario() {
		if(usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Entity entity) {
		this.usuario = (Usuario) entity;
	}
}
