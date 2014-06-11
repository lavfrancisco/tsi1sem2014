package br.edu.ifsp.ddm.ifbook.modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4360718276063483351L;
	private int idUsuario;
	private String Prontuario;
	private String Senha;
	private int Nivel;
	private String Nome;
	private String Apelido;
	private String Nascimento;
	private String LocalTrabalho;
	private String Cidade;
	private String Email;
	private String Telefone;
	private byte[] Foto = null;
	private EstadoCivil idEstadoCivil;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getProntuario() {
		return Prontuario;
	}
	public void setProntuario(String prontuario) {
		Prontuario = prontuario;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public int getNivel() {
		return Nivel;
	}
	public void setNivel(int nivel) {
		Nivel = nivel;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getApelido() {
		return Apelido;
	}
	public void setApelido(String apelido) {
		Apelido = apelido;
	}
	public String getNascimento() {
		return Nascimento;
	}
	public void setNascimento(String nascimento) {
		Nascimento = nascimento;
	}
	public String getLocalTrabalho() {
		return LocalTrabalho;
	}
	public void setLocalTrabalho(String localTrabalho) {
		LocalTrabalho = localTrabalho;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	public byte[] getFoto() {
		return Foto;
	}
	public void setFoto(byte[] foto) {
		Foto = foto;
	}
	public EstadoCivil getIdEstadoCivil() {
		return idEstadoCivil;
	}
	public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
