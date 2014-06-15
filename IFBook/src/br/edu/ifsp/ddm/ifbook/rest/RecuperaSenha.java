package br.edu.ifsp.ddm.ifbook.rest;

import br.edu.ifsp.ddm.ifbook.modelo.Usuario;

import com.google.gson.Gson;

public class RecuperaSenha {
	private StringBuilder URL_WS = new StringBuilder("http://10.0.0.102:8080/IFBookWebService-ServidorRest/");
	
	public RecuperaSenha (String url)
    {
    	this.URL_WS.append("password/");    	
    }
	
public String novaSenha(Usuario usuario) throws Exception {
        
        Gson gson = new Gson();
        String usuarioJSON = gson.toJson(usuario);
        String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"recovery",usuarioJSON);
    	if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
       }
}
