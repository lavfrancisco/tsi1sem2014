package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao.PessoaDAO;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Pessoa;

import com.google.gson.Gson;

@Path("/pessoa")
public class PessoaResource {

	@GET
	@Path("/buscarTodos")
	@Produces("application/json")
	public ArrayList<Pessoa> selTodos() {
		return new PessoaDAO().buscarTodos();
	}

	@GET
	@Path("/buscarTodosGSON")
	@Produces("application/json")
	public String selTodosGSON() {
		return new Gson().toJson(new PessoaDAO().buscarTodos());
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Pessoa getPessoa(@PathParam("id") int id) {
		Pessoa p = new PessoaDAO().buscar(id);

		if (p == null)
			throw new NoContentException("Pessoa não encontrada!");

		return p;
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public String deletePessoa(@PathParam("id") int id) {
		return new PessoaDAO().deletar(id);
	}

	@POST
	@Path("/inserir")	
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirPessoa(Pessoa p) {
		//return p.getNome();
		return new PessoaDAO().inserir(p);
	}
	
	@POST
	@Path("/inserirPessoas")
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirPessoas(List<Pessoa> pessoas) {
		return new PessoaDAO().inserir(pessoas);
	}

}
