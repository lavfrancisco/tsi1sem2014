package br.edu.ifsp.ddm.ifbook.util;

public class LoginManager {
	private boolean logado = false;
	private String usuario;
	private int id;
	private int nivel;
	private static LoginManager login = null; 
	
	public static LoginManager getLogin(){
		if(login == null){
			login = new LoginManager();
			login.logado = true;
		}
		return login;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public boolean isLogado() {
		return logado;
	}	
}
