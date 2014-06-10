package br.edu.ifsp.ddm.ifbook.ws.servidor.resources;




import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Usuario;


import com.google.gson.Gson;

@Path("/mensagem")
public class MensagemResource {

	@GET
	@Path("/listall")
	@Produces("application/json")
	public String selTodos() {
		return new Gson().toJson(new MensagemDAO().buscarTodos());
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
	public String getMensagem(@PathParam("id") int id) {
		Mensagem m = new MensagemDAO().getById(id);

		if (m == null)
			throw new NoContentException("Mensagem não encontrada!");

		return new Gson().toJson(m);
	}

	@GET
	@Path("/delete/{id}")
	@Produces("application/json")
	public String deleteMensagem(@PathParam("id") int id) {
		return new MensagemDAO().deletar(id);
	}

	@POST
	@Path("/insert")	
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirMensagem(String m) {
		Gson gson = new Gson();
		Mensagem msg = gson.fromJson(m, Mensagem.class);
		
		return new MensagemDAO().inserir(msg);
	}
	
		
	@POST
	@Path("/update")
	@Produces("application/json")
	@Consumes("application/json")
	public String atualizarMensagem(String mensagem){
	//	System.out.println(usuario);
		Gson gson = new Gson();
		Mensagem m = gson.fromJson(mensagem, Mensagem.class);
		
		return new MensagemDAO().update(m);
		
	}
	
	
	@GET
	@Path("/usuario/{id}")
	@Produces("application/json")
	public String buscarPorusuario(@PathParam("id") int id) {
		return new Gson().toJson(new MensagemDAO().buscarPorUsuario(id));
	}

}
