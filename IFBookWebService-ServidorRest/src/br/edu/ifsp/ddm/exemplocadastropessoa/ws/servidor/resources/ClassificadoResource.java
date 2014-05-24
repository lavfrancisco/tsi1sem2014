package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Classificado;

import com.google.gson.Gson;

@Path("/classificado")
public class ClassificadoResource {

	@GET
	@Path("/buscarTodos")
	@Produces("application/json")
	public ArrayList<Classificado> selTodos() {
		return new ClassificadoDAO().buscarTodos();
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
	public Classificado getClassificado(@PathParam("id") int id) {
		Classificado m = new ClassificadoDAO().buscar(id);

		if (m == null)
			throw new NoContentException("Pessoa não encontrada!");

		return m;
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public String deleteClassificado(@PathParam("id") int id) {
		return new ClassificadoDAO().deletar(id);
	}

	@POST
	@Path("/inserir")	
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirClassificado(Classificado m) {
		//return p.getNome();
		return new ClassificadoDAO().inserir(m);
	}
	
	@POST
	@Path("/inserirClassificados")
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirMensagens(List<Classificado> classificados) {
		return new ClassificadoDAO().inserir(classificados);
	}

}
