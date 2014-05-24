package br.edu.ifsp.ddm.ifbook.rest;

import java.util.ArrayList;
import java.util.List;



import br.edu.ifsp.ddm.ifbook.modelo.Usuario;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class UsuarioREST {
	

    //private StringBuilder URL_WS = new StringBuilder("http://10.0.5.30:8080/ExemploCadastroPessoaServidorRest/");
    private StringBuilder URL_WS = new StringBuilder("http://192.168.1.27:8080/ExemploCadastroPessoaServidorRest/");

    public UsuarioREST(String url)
    {
    	this.URL_WS.append("usuario/");    	
    }
    
    public Usuario getUsuario(int id) throws Exception {

     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+id);
     
     if (resposta[0].equals("200")) {
         Gson gson = new Gson();
         Usuario usuario = gson.fromJson(resposta[1], Usuario.class);
         return usuario;
     } else {
         throw new Exception(resposta[1]);
     }
    }
    
    public Usuario getProntuario(String bv) throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS.toString()+bv);
        
        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            Usuario usuario = gson.fromJson(resposta[1], Usuario.class);
            return usuario;
        } else {
            throw new Exception(resposta[1]);
        }
       }
    
    public List<Usuario> getListaUsuarios() throws Exception {

     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"buscarTodosGSON");
     
     if (resposta[0].equals("200")) {
         Gson gson = new Gson();
         ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
         JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
         
        for (int i = 0; i < array.size(); i++) {
        	listaUsuario.add(gson.fromJson(array.get(i), Usuario.class));
         }
         return listaUsuario;
     } else {
         throw new Exception(resposta[1]);
     }
    }
    
    public String inserirUsuario(Usuario usuario) throws Exception {
     
     Gson gson = new Gson();
     String usuarioJSON = gson.toJson(usuario);
     String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"inserir",usuarioJSON);
     if (resposta[0].equals("200")) {
         return resposta[1];
     } else {
         throw new Exception(resposta[1]);
     }
    }

    public String atualizar(Usuario usuario) throws Exception {
        
        Gson gson = new Gson();
        String usuarioJSON = gson.toJson(usuario);
        String[] resposta = new WebServiceCliente().post(URL_WS.toString()+"alterar",usuarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
       }
   
    
    public String deletarUsuario(int id) {   
     String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"delete/"+id);
     return resposta[1];
    }
    
    
    
    
    
    
    public List<Usuario> getAniversariantes() throws Exception {
    	String sql = "SELECT idUsuario,Nome, strftime('%m/%d', Nascimento), Prontuario, Senha, Apelido, LocalTrabalho, Cidade, Email, Telefone, Foto, EstadoCivil_idEstadoCivil, Nivel FROM usuario WHERE strftime('%m/%d', Nascimento) >= strftime('%m/%d',date('now')) AND strftime('%m/%d', Nascimento) <= strftime('%m/%d',date('now','+7 day')) ORDER BY Nascimento;";
    	
    	
        String[] resposta = new WebServiceCliente().get(URL_WS.toString()+"buscarTodosGSON");
        
        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
            JsonParser parser = new JsonParser();
           JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
            
           for (int i = 0; i < array.size(); i++) {
           	listaUsuario.add(gson.fromJson(array.get(i), Usuario.class));
            }
            return listaUsuario;
        } else {
            throw new Exception(resposta[1]);
        }
       }
    
    
    
    
    
    
    
}
 