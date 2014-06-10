package br.edu.ifsp.ddm.ifbook.rest;


import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
	public class MensagemREST {
		

	    //private StringBuilder URL_WS = new StringBuilder("http://10.0.5.30:8080/ExemploCadastroPessoaServidorRest/");
		// private StringBuilder URL_WS = new StringBuilder("http://10.0.6.9:8080/IFBookWebService-ServidorRest/");
		private StringBuilder URL_WS = new StringBuilder("http://10.0.0.102:8080/IFBookWebService-ServidorRest/");

	    public MensagemREST(String url)
	    {
	    	this.URL_WS.append("mensagem/");    	
	    }
	    
	    public Mensagem getMensagem(int id) throws Exception {

	     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+id);
	     System.out.println("ID MENSAGEM: "+id);
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         Mensagem mensagem = gson.fromJson(resposta[1], Mensagem.class);
	         return mensagem;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
	    
	
	    public List<Mensagem> getListaMensagens() throws Exception {

	     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"listall");
	     
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         ArrayList<Mensagem> listaMensagem = new ArrayList<Mensagem>();
	         JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
	         
	        for (int i = 0; i < array.size(); i++) {
	        	listaMensagem.add(gson.fromJson(array.get(i), Mensagem.class));
	         }
	         return listaMensagem;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
	
	    
	    public String inserirMensagem(Mensagem mensagem) throws Exception {
	     
	     Gson gson = new Gson();
	     String mensagemJSON = gson.toJson(mensagem);
	     String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"insert",mensagemJSON);
	     if (resposta[0].equals("200")) {
	         return resposta[1];
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }

	    public String atualizar(Mensagem mensagem) throws Exception {
	        
	        Gson gson = new Gson();
	        String mensagemJSON = gson.toJson(mensagem);
	        String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"update",mensagemJSON);
	        if (resposta[0].equals("200")) {
	            return resposta[1];
	        } else {
	            throw new Exception(resposta[1]);
	        }
	       }
	   
	    public String deletar(int id) {   
	        String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"delete/"+id);
	        return resposta[1];
	       }
	    
	    
	    
	    public List<Mensagem> getListaMinhasMenssagens(int id) throws Exception {

		     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"usuario/"+id);
		     
		     if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         ArrayList<Mensagem> listaMensagem = new ArrayList<Mensagem>();
		         JsonParser parser = new JsonParser();
		        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
		         
		        for (int i = 0; i < array.size(); i++) {
		        	listaMensagem.add(gson.fromJson(array.get(i), Mensagem.class));
		         }
		         return listaMensagem;
		     } else {
		         throw new Exception(resposta[1]);
		     }
		    }
		    
	    
	    
	 
	}
