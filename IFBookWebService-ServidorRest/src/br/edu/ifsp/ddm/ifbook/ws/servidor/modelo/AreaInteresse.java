package br.edu.ifsp.ddm.ifbook.ws.servidor.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AreaInteresse {
	
	private int idAreaInteresse;
	private int Tipo;
	private String Nome;
	
	public int getIdAreaInteresse() {
		return idAreaInteresse;
	}
	public void setIdAreaInteresse(int idAreaInteresse) {
		this.idAreaInteresse = idAreaInteresse;
	}
	public int getTipo() {
		return Tipo;
	}
	public void setTipo(int tipo) {
		Tipo = tipo;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	
	@Override
	public String toString() {
			return this.Nome;
	}

}
