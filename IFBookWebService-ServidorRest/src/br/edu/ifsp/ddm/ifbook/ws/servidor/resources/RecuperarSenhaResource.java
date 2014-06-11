package br.edu.ifsp.ddm.ifbook.ws.servidor.resources;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.ifsp.ddm.ifbook.ws.servidor.mail.*;

@Path("/password")
public class RecuperarSenhaResource {
	
	@GET
	@Path("/recovery/{bv}")
	@Produces("application/json")
	public String EnviaNovaSenha(@PathParam("bv") String bv){
		RecuperaSenha pwd = new  RecuperaSenha();
		
		return pwd.enviaNovaSenha(bv);
	}
}
