package br.com.javaparaweb.financeiro.web;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.com.javaparaweb.financeiro.usuario.Usuario;
import br.com.javaparaweb.financeiro.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	//--- a partir do cap 5
	private List<Usuario> lista;
	private String destinoSalvar;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();

		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(usuario);
		//befor in chap 4
		//return "usuario_sucesso";
		//now :
		return this.destinoSalvar;
	}

	public String novo() {
		this.destinoSalvar = "usuario_sucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/usuario";
	}
	
	public String editar() {
		 this.confirmarSenha = this.usuario.getSenha();
		 return "/public/usuario";
	}
	
	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		
		this.lista = null;
		return null;
	}
	
	public String ativar() {
		
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		
		return null;

	}
	
	public List<Usuario> getLista(){
		
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		
		return this.lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	

}
