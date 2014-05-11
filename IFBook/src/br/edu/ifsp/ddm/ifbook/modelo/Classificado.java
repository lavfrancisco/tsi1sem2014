package br.edu.ifsp.ddm.ifbook.modelo;

import java.sql.Blob;
import java.sql.Date;

public class Classificado {

	private Integer idClassificado;
	private String Data;
	private String Titulo;
	private String Descricao;
	private Blob Imagem;
	private AreaInteresse AreaInteresse_idAreaInteresse;
	private Usuario Usuario_idUsuario;
	
	public Integer getIdClassificado() {
		return idClassificado;
	}
	public void setIdClassificado(Integer idClassificado) {
		this.idClassificado = idClassificado;
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
	public AreaInteresse getAreaInteresse_idAreaInteresse() {
		return AreaInteresse_idAreaInteresse;
	}
	public void setAreaInteresse_idAreaInteresse(
			AreaInteresse areaInteresse_idAreaInteresse) {
		AreaInteresse_idAreaInteresse = areaInteresse_idAreaInteresse;
	}
	public Usuario getUsuario_idUsuario() {
		return Usuario_idUsuario;
	}
	public void setUsuario_idUsuario(Usuario usuario_idUsuario) {
		Usuario_idUsuario = usuario_idUsuario;
	}
	
}
