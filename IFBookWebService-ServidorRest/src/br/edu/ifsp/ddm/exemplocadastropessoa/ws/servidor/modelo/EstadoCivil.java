package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo;

import java.io.Serializable;

public class EstadoCivil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 55837854871669928L;
	private int idEstadoCivil;
	private String EstadoCivil;
	
	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}
	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	public String getEstadoCivil() {
		return EstadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		EstadoCivil = estadoCivil;
	}
	@Override
	public String toString() {
		/*return "EstadoCivil [idEstadoCivil=" + idEstadoCivil + ", EstadoCivil="
				+ EstadoCivil + "]";*/
		return this.EstadoCivil;
	}
	
}
