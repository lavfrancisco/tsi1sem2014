package br.edu.ifsp.ddm.ifbook.rest;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class EstadoCivilREST {
	 //private StringBuilder URL_WS = new StringBuilder("http://10.0.5.30:8080/ExemploCadastroPessoaServidorRest/");
	// private StringBuilder URL_WS = new StringBuilder("http://10.0.6.9:8080/IFBookWebService-ServidorRest/");
	private StringBuilder URL_WS = new StringBuilder("http://10.0.0.102:8080/IFBookWebService-ServidorRest/");

   public EstadoCivilREST(String url)
   {
   	this.URL_WS.append("estadocivil/");    	
   }
   
   public EstadoCivil getEstadoCivil(int id) throws Exception {

    String[] resposta = new WebServiceCliente().get(URL_WS.toString()+id);
    
    if (resposta[0].equals("200")) {
        Gson gson = new Gson();
        EstadoCivil estadoCivil = gson.fromJson(resposta[1], EstadoCivil.class);
        return estadoCivil;
    } else {
        throw new Exception(resposta[1]);
    }
   }

   
   public List<EstadoCivil> getListaEstadoCivis() throws Exception {

	     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"listall");
	     
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         ArrayList<EstadoCivil> listaEstadoCivis = new ArrayList<EstadoCivil>();
	         JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
	         
	        for (int i = 0; i < array.size(); i++) {
	        	listaEstadoCivis.add(gson.fromJson(array.get(i), EstadoCivil.class));
	         }
	         return listaEstadoCivis;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
	    
   
}
