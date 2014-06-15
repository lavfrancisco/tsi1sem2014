package br.edu.ifsp.ddm.ifbook.ws.servidor.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.edu.ifsp.ddm.ifbook.ws.servidor.mail.*;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Usuario;

@Path("/password")
public class RecuperarSenhaResource {
	
	@POST
	@Path("/recovery")
	@Produces("application/json")
	@Consumes("application/json")
	public String EnviaNovaSenha(String usuario){
		Gson gson = new Gson();
		Usuario user = gson.fromJson(usuario, Usuario.class);
		RecuperaSenha pwd = new  RecuperaSenha();
		
		return pwd.enviaNovaSenha(user);
	}
}
