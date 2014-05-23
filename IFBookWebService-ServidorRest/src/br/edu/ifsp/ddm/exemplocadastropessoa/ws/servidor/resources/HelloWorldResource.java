package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorldResource {

	@GET
	@Produces("text/plain")
	public String showHelloWorld() {
		return "Ola mundo!";
	}
	
	@POST
	@Path("/inserir")
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirPessoa(String s) {
		return s+" OK";
	}
	
	
}
