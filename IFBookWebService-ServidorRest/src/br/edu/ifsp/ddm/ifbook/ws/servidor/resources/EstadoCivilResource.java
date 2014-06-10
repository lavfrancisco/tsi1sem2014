package br.edu.ifsp.ddm.ifbook.ws.servidor.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.EstadoCivilDAO;
import br.edu.ifsp.ddm.ifbook.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.EstadoCivil;

@Path("/estadocivil")
public class EstadoCivilResource {
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public String getEstadoCivil(@PathParam("id") int id){
		EstadoCivil estadoCivil = new EstadoCivilDAO().getById(id);
		
		if(estadoCivil == null)
			throw new NoContentException("Estao civil não encontrado");
		
		return new Gson().toJson(estadoCivil);
	}
	
	@GET
	@Path("/listall")
	@Produces("application/json")
	public String listarTodos(){		
		return new Gson().toJson(new EstadoCivilDAO().listarTodos());
	}
	
}
