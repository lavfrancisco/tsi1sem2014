package br.edu.ifsp.ddm.ifbook.ws.servidor.mail;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;


/**
 *
 * @author Luiz
 */
public class EmailMensagem {
    private String emailDestinatario;
    private String nomeDestinatario;
    private String assunto;
    private String corpo;
    private String mimeCorpo = "text/html";
    private MimeBodyPart anexo = null;

    /**
     * @return email do Destinatario
     */
    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    /**
     * @param emailDestinatario o email de destino
     * @param nomeDestinatario o nome por exterso do destinatario da mensagem
     */
    public void setDestinatario(String emailDestinatario, String nomeDestinario){
        this.emailDestinatario = emailDestinatario;
        this.nomeDestinatario = nomeDestinatario;
    }

    /**
     * @return the nomeDestinatario
     */
    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the corpo
     */
    public String getCorpo() {
        return corpo;
    }

    /**
     * @param corpo the corpo to set
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    /**
     * @return the mimeCorpo
     */
    public String getMimeCorpo() {
        return mimeCorpo;
    }

    /**
     * @param mimeCorpo the mimeCorpo to set
     */
    public void setMimeCorpo(String mimeCorpo) {
        this.mimeCorpo = mimeCorpo;
    }

    public MimeBodyPart getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = new MimeBodyPart();
        try {
            this.anexo.attachFile(anexo);
        } catch (IOException ex) {
            Logger.getLogger(EmailMensagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
