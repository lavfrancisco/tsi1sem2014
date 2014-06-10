package br.edu.ifsp.ddm.ifbook.ws.servidor.modelo;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class EstadoCivil{
	/**
	 * 
	 */
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
