/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsp.ddm.ifbook.ws.servidor.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Luiz
 */
public class EnviarEmail {
    /*
     * variavel interna utilizada para gravar a mensagem
     */
    private MimeMessage mimeMsg;
    
    public EnviarEmail(final EmailRemetente rmt, EmailMensagem msg) throws MessagingException, UnsupportedEncodingException {

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", rmt.getNomeHost());
        props.put("mail.smtp.port", rmt.getPorta());
        props.put("mail.smtp.user", rmt.getUsuarioEmail());
        if (rmt.getAutenticar()) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
        // 
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(rmt.getUsuarioEmail(),
                                                  rmt.getSenha());
            }            
        };
        // obtem a sessão
        Session session = Session.getDefaultInstance(props, auth);
        mimeMsg = new MimeMessage(session);
        mimeMsg.setFrom(new InternetAddress(rmt.getUsuarioEmail(),
                                            rmt.getUsuarioNome()));
        mimeMsg.addRecipient(RecipientType.TO,
                             new InternetAddress(msg.getEmailDestinatario(),
                                                 msg.getNomeDestinatario()));
        mimeMsg.setSubject(msg.getAssunto());
        
        if(msg.getAnexo()!=null)
        {
            // create the Multipart and add its parts to it
	    Multipart mp = new MimeMultipart();
            MimeBodyPart mensagem = new MimeBodyPart();
            mensagem.setText(msg.getCorpo());
	    mp.addBodyPart(mensagem);            
	    mp.addBodyPart(msg.getAnexo());
            mimeMsg.setContent(mp);
        }
        else
        {
             mimeMsg.setContent(msg.getCorpo(), msg.getMimeCorpo());
        }
       
        
        
        
        

        Transport.send(mimeMsg); // enviar a mensagem        
    }
    

}
