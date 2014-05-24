package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao.MensagemDAO;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Mensagem;

import com.google.gson.Gson;

@Path("/mensagem")
public class MensagemResource {

	@GET
	@Path("/buscarTodos")
	@Produces("application/json")
	public ArrayList<Mensagem> selTodos() {
		return new MensagemDAO().buscarTodos();
	}

	@GET
	@Path("/buscarTodosGSON")
	@Produces("application/json")
	public String selTodosGSON() {
		return new Gson().toJson(new MensagemDAO().buscarTodos());
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Mensagem getMensagem(@PathParam("id") int id) {
		Mensagem m = new MensagemDAO().buscar(id);

		if (m == null)
			throw new NoContentException("Pessoa não encontrada!");

		return m;
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public String deleteMensagem(@PathParam("id") int id) {
		return new MensagemDAO().deletar(id);
	}

	@POST
	@Path("/inserir")	
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirMensagem(Mensagem m) {
		//return p.getNome();
		return new MensagemDAO().inserir(m);
	}
	
	@POST
	@Path("/inserirMensagens")
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirMensagens(List<Mensagem> mensagens) {
		return new MensagemDAO().inserir(mensagens);
	}

}
