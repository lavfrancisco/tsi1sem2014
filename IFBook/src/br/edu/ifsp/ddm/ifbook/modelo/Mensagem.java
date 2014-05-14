package br.edu.ifsp.ddm.ifbook.modelo;

import java.sql.Blob;
import java.util.Date;


public class Mensagem {

	private Integer idMensagem;
	private String Data;
	private String Titulo;
	private String Descricao;
	private Blob Imagem;
	private AreaInteresse AreaInteresse;
	private Usuario Usuario;
	
	public Integer getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public Blob getImagem() {
		return Imagem;
	}
	public void setImagem(Blob imagem) {
		Imagem = imagem;
	}
	public AreaInteresse getAreaInteresse() {
		return AreaInteresse;
	}
	public void setAreaInteresse(
			AreaInteresse areaInteresse_idAreaInteresse) {
		AreaInteresse = areaInteresse_idAreaInteresse;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario_idUsuario) {
		Usuario = usuario_idUsuario;
	}
	
}