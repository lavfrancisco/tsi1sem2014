package br.edu.ifsp.ddm.ifbook.rest;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.AreaInteresse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class AreaInteresseREST {
	 //private StringBuilder URL_WS = new StringBuilder("http://10.0.5.30:8080/ExemploCadastroPessoaServidorRest/");
	// private StringBuilder URL_WS = new StringBuilder("http://10.0.6.9:8080/IFBookWebService-ServidorRest/");
	private StringBuilder URL_WS = new StringBuilder("http://10.0.0.102:8080/IFBookWebService-ServidorRest/");

    public AreaInteresseREST(String url)
    {
    	this.URL_WS.append("areainteresse/");    	
    }
    
    public AreaInteresse getAreaInteresse(int id) throws Exception {

     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+id);
     
     if (resposta[0].equals("200")) {
         Gson gson = new Gson();
         AreaInteresse areaInteresse = gson.fromJson(resposta[1], AreaInteresse.class);
         return areaInteresse;
     } else {
         throw new Exception(resposta[1]);
     }
    }
 
    
    public List<AreaInteresse> getListaAreaInteresses() throws Exception {

	     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"listall");
	     
	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         ArrayList<AreaInteresse> listaAreaInteresses = new ArrayList<AreaInteresse>();
	         JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
	         
	        for (int i = 0; i < array.size(); i++) {
	        	listaAreaInteresses.add(gson.fromJson(array.get(i), AreaInteresse.class));
	         }
	         return listaAreaInteresses;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	    }
	    
    
}

