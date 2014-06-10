package br.edu.ifsp.ddm.ifbook.rest;


import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
	public class ClassificadoREST {
		

	    //private StringBuilder URL_WS = new StringBuilder("http://10.0.5.30:8080/ExemploCadastroPessoaServidorRest/");
		// private StringBuilder URL_WS = new StringBuilder("http://10.0.6.9:8080/IFBookWebService-ServidorRest/");
		private StringBuilder URL_WS = new StringBuilder("http://10.0.0.102:8080/IFBookWebService-ServidorRest/");

	    public ClassificadoREST(String url)
	    {
	    	this.URL_WS.append("classificado/");    	
	    }
	    
	    public Classificado getClassificado(int id) throws Exception {

	     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+id);
	     
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         Classificado classificado = gson.fromJson(resposta[1], Classificado.class);
	         return classificado;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
	    
	
	    public List<Classificado> getListaClassificados() throws Exception {

	     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"listall");
	     
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         ArrayList<Classificado> listaClassificado = new ArrayList<Classificado>();
	         JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
	         
	        for (int i = 0; i < array.size(); i++) {
	        	listaClassificado.add(gson.fromJson(array.get(i), Classificado.class));
	        	
	        	
	         }
	      
	         return listaClassificado;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
	    
	    
	    public List<Classificado> getListaMeusClassificados(int id) throws Exception {

		     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"usuario/"+id);
		     
		     if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         ArrayList<Classificado> listaClassificado = new ArrayList<Classificado>();
		         JsonParser parser = new JsonParser();
		        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
		         
		        for (int i = 0; i < array.size(); i++) {
		        	listaClassificado.add(gson.fromJson(array.get(i), Classificado.class));
		         }
		         return listaClassificado;
		     } else {
		         throw new Exception(resposta[1]);
		     }
		    }
		    
	    
	    public String inserirClassificado(Classificado classificado) throws Exception {
	     
	     Gson gson = new Gson();
	     String classificadoJSON = gson.toJson(classificado);
	     String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"insert",classificadoJSON);
	     if (resposta[0].equals("200")) {
	         return resposta[1];
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }

	    public String atualizar(Classificado classificado) throws Exception {
	        
	        Gson gson = new Gson();
	        String classificadoJSON = gson.toJson(classificado);
	        String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"update",classificadoJSON);
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
	    
	    
	}