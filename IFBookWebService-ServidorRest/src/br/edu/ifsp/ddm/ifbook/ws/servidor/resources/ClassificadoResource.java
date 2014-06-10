package br.edu.ifsp.ddm.ifbook.ws.servidor.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Mensagem;

import com.google.gson.Gson;

@Path("/classificado")
public class ClassificadoResource {

	@GET
	@Path("/listall")
	@Produces("application/json")
	
	public String selTodos() {
		System.out.println("LIST ALL CLASSIFICADO RESOURCE!");
		return new Gson().toJson(new ClassificadoDAO().buscarTodos());
	}

	@GET
	@Path("/buscarTodosGSON")
	@Produces("application/json")
	public String selTodosGSON() {
		return new Gson().toJson(new ClassificadoDAO().buscarTodos());
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public String getClassificado(@PathParam("id") int id) {
		Classificado c = new ClassificadoDAO().getById(id);

		if (c == null)
			throw new NoContentException("Classificado não encontrada!");

		return new Gson().toJson(c);
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public String deleteClassificado(@PathParam("id") int id) {
		return new ClassificadoDAO().deletar(id);
	}

	@POST
	@Path("/insert")	
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirClassificado(String c) {
		Gson gson = new Gson();
		Classificado cla = gson.fromJson(c, Classificado.class);
		
		return new ClassificadoDAO().inserir(cla);
	}
	
	@POST
	@Path("/update")
	@Produces("application/json")
	@Consumes("application/json")
	public String atualizarClassificado(String classificado){
	//	System.out.println(usuario);
		Gson gson = new Gson();
		Classificado c = gson.fromJson(classificado, Classificado.class);
		
		return new ClassificadoDAO().update(c);
		
	}
	@GET
	@Path("/usuario/{id}")
	@Produces("application/json")
	public String buscarPorusuario(@PathParam("id") int id) {
		return new Gson().toJson(new ClassificadoDAO().buscarPorUsuario(id));
	}

}
