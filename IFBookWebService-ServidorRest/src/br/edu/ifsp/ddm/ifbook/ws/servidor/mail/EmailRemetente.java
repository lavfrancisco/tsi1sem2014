/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsp.ddm.ifbook.ws.servidor.mail;

/**
 *
 * @author Luiz
 */
public class EmailRemetente {

  private String nomeHost = "smtp.gmail.com";
  private String porta = "25";
  private String usuarioEmail="ifspsistema04";
  private String usuarioNome="Recuperador de Senha IFBook";
  private String senha = "sistema04";
  private Boolean autenticar = true;

    /**
     * @return nome do Host (endereço ip ou FQDN)
     */
    public String getNomeHost() {
        return nomeHost;
    }

    /**
     * @param nomeHost nome do host SMTP que enviará a mensagem
     */
    public void setNomeHost(String nomeHost) {
        this.nomeHost = nomeHost;
    }

    /**
     * @return a porta do servidor SMTP configurada
     */
    public String getPorta() {
        return porta;
    }

    /**
     * seta a porta para um valor
     *
     * @param porta novo valor, normalmente 25, 465 ou 587
     * Google mail SSL Porta 465 StartTLS Porta 587
     * Outlook.com (ou Hotmail) StartTLS Porta 587
     * Yahoo SSL Porta 465
     *
     */
    public void setPorta(String porta) {
        this.porta = porta;
    }

    /*
     * porta default alternativa usado para new submissions (MSA)
     * muito usada quando em conjunto com StartTLS
     */
    public void setPortaAlternativa() {
        this.porta = "587";
    }

    /*
     * porta padrão do SMTP
     */
    public void setPortaDefault() {
        this.porta = "25";
    }

    /**
     * @return o email do usuario
     */
    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    /**
     * @param usuario o endereço de email do usuário
     *        (utilizado para conectar ao servidor SMTP
     *         e para preencher o campo FROM)
     */
    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    /**
     * @return senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha pega a senha do usuário
     *             (usado para autenticar o usuário no servidor)
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
    * @return nome do Usuario
     */
    public String getUsuarioNome() {
        return usuarioNome;
    }

    /**
     * @param nomeUsuario texto simples com o nome do usuário
     */
    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    /**
     * @return indica se deve fazer procedimento de autenticação
     */
    public Boolean getAutenticar() {
        return autenticar;
    }

    /**
     * @param autenticar
     */
    public void setAutenticar(Boolean autenticar) {
        this.autenticar = autenticar;
    }


}
