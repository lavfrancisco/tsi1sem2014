package br.edu.ifsp.ddm.ifbook.ws.servidor.mail;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import br.edu.ifsp.ddm.ifbook.ws.servidor.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.ws.servidor.exception.NoContentException;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.ws.servidor.util.GeradorDeHash;
import br.edu.ifsp.ddm.ifbook.ws.servidor.util.GeradorDeSenha;

public class RecuperaSenha {
	public String enviaNovaSenha(Usuario usuario){
		String sts = "Falha no envio!";
		
		EmailRemetente rmt = new EmailRemetente();
        rmt.setPortaAlternativa();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.getProntuario(usuario.getProntuario());
        
        if (user == null){
			throw new NoContentException("Usuario nâo encontrado!");
        }
        else{
        	GeradorDeSenha gen = new GeradorDeSenha();
	        EmailMensagem msg = new EmailMensagem();
	        String senha_plana = gen.gerar();
	        GeradorDeHash hash = new GeradorDeHash();
	        String senha_cifrada;
			try {
				senha_cifrada = hash.gerar(senha_plana);
				user.setSenha(senha_cifrada);
		        msg.setDestinatario(user.getEmail(), user.getNome());
		        msg.setAssunto("Sua nova senha para o IFBook");
		        msg.setCorpo("<h3>IFBook</h3>Olá "+user.getApelido()+"<br/>Sua nova senha para logar no IFBook é: <b>"+senha_plana+"</b>");
				EnviarEmail email = new EnviarEmail(rmt, msg);
				dao.updatePasswd(user);
				sts = "OK";
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
        }    
        return sts;
	}
}
