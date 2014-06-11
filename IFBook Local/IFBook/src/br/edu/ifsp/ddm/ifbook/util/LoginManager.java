package br.edu.ifsp.ddm.ifbook.util;

import br.edu.ifsp.ddm.ifbook.modelo.Usuario;

public class LoginManager {
	private boolean logado = false;
	private Usuario usuario;
	private static LoginManager login = null; 
	
	public static LoginManager getLogin(){
		if(login == null){
			login = new LoginManager();
			login.logado = true;
		}
		return login;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isLogado() {
		return logado;
	}	
}
