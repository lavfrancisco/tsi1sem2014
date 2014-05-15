package br.edu.ifsp.ddm.ifbook.modelo;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


public class Mensagem implements Serializable {

	private static final long serialVersionUID = 4360718276063483351L;
	private Integer idMensagem;
	private String Data;
	private String Titulo;
	private String Descricao;
	private byte[] Imagem = null;
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
	public byte[] getImagem() {
		return Imagem;
	}
	public void setImagem(byte[] imagem) {
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