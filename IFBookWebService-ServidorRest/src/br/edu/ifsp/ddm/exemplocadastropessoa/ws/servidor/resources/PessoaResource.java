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
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao.UsuarioDAO;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Pessoa;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Usuario;

import com.google.gson.Gson;

@Path("/usuario")
public class PessoaResource {

	@GET
	@Path("/buscarTodos")
	@Produces("application/json")
	public ArrayList<Pessoa> selTodos() {
		return new PessoaDAO().buscarTodos();
	}

	@GET
	@Path("/getAniversariantes")
	@Produces("application/json")
	public String selTodosGSON() {
		return new Gson().toJson(new UsuarioDAO().Aniversariantes());
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Usuario getUsuario(@PathParam("id") int id) {
		Usuario usuario = new UsuarioDAO().getById(id);

		if (usuario == null)
			throw new NoContentException("Usuario não encontrado!");

		return usuario;
	}
	
	@GET
	@Path("/{bv}")
	@Produces("application/json")
	public Usuario getUsuario(@PathParam("bv") String bv) {
		Usuario usuario = new UsuarioDAO().getProntuario(bv);

		if (usuario == null)
			throw new NoContentException("Usuario não encontrado!");

		return usuario;
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
