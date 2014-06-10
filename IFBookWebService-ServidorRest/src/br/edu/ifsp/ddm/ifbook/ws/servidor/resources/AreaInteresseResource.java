package br.edu.ifsp.ddm.ifbook.ws.servidor.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.AreaInteresseDAO;

import com.google.gson.Gson;

@Path("/areainteresse")
public class AreaInteresseResource {
	
	@GET
	@Path("/listall")
	@Produces("application/json")
	public String listarTodos(){		
		return new Gson().toJson(new AreaInteresseDAO().listarTodos());
	}
}
