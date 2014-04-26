package br.edu.ifsp.ddm.ifbook.modelo;

import java.sql.Blob;
import java.util.Date;

public class Mensagem {

	private Integer idMensagem;
	private Date Data;
	private String Titulo;
	private String Descricao;
	private Blob Imagem;
	private Integer AreaInteresse_idAreaInteresse;
	private Integer Usuario_idUsuario;
	
	public Integer getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
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
	public Integer getAreaInteresse_idAreaInteresse() {
		return AreaInteresse_idAreaInteresse;
	}
	public void setAreaInteresse_idAreaInteresse(
			Integer areaInteresse_idAreaInteresse) {
		AreaInteresse_idAreaInteresse = areaInteresse_idAreaInteresse;
	}
	public Integer getUsuario_idUsuario() {
		return Usuario_idUsuario;
	}
	public void setUsuario_idUsuario(Integer usuario_idUsuario) {
		Usuario_idUsuario = usuario_idUsuario;
	}
	
}

