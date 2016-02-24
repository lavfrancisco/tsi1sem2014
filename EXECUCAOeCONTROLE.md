## 1.	EXECUÇÃO E CONTROLE ##

Após a realização do planejamento do projeto, iniciou-se a fase de execução do projeto, onde a equipe foi dividida em vários grupos, onde cada grupo tinha a responsabilidade de desenvolver uma parte especifica do aplicativo web e local. Foi definida a arquitetura que seria utilizada, como pode ser visto na imagem abaixo:
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/arquiteturapronta.png' /> </p>
<p align='center'>Figura 1 - Arquitetura do Projeto </p>

Na figura da arquitetura do projeto podemos observar o desenvolvimento de um projeto mobile na qual o bando de dados é responsável pela armazenagem dos dados do usuário e ações do sistema. Os serviços que o sistema pode realizar estão disponíveis na plataforma móvel. Nas próximas seções poderá ser visualizada a organização do projeto com maiores detalhes.

<br>
<h3>1.1.	APLICATIVO ANDROID</h3>
Para criar um projeto estruturado, foi utilizado os conceitos MVC, dividindo em camadas, que são responsáveis por determinadas ações. Na Figura abaixo podemos observar que foram criados pacotes para conter as  classes de DAO, um pacote para as classes do modelo,um pacote para adicionar classes uteis para todo o projeto.<br>
<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/figura2b.png' /> </p>
<p align='center'>Figura 2 - Estrutura do Projeto </p>

<br><br>
Abaixo contém a parte visual do projeto, onde estão contidos os arquivos de layout que foram criadas.<br>
<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/Figura%203%20%E2%80%93%20Estrutura%20de%20Layout%20do%20Projeto2.png' /> </p>
<p align='center'>Figura 3 – Estrutura de Layout do Projeto </p>

<br><br>
É possível verificar na imagem abaixo as classes que foram criadas no pacote modelo.<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/figura4b.png' /> </p>
<p align='center'>Figura 4 – Classes do pacote modelo </p>

<br><br>
Observa-se na figura abaixo as classes criadas no pacote DAO, responsável por realizar o acesso e todas as interações ao banco de dados.<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/figura4c.png' /> </p>
<p align='center'>Figura 5 – Classes do Pacote DAO </p>

<br><br>
Na figura abaixo é possível observar as Activitys que foram criadas no projeto<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/Figura%204%20%E2%80%93%20Classes%20do%20pacote.png' /> </p>
<p align='center'>Figura 6 – Classes do pacote </p>

<br><br>
Na imagem abaixo pode-se observar as classes que foram criadas no pacote rest<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/Figura%206%20%E2%80%93%20Classes%20do%20pacote%20rest.png' /> </p>
<p align='center'>Figura 7 – Classes do pacote rest </p>

<br><br>
A seguir é possível observar as classes que foram criadas no pacote útil (conexão com Web Service)<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/Figura%208%20%E2%80%93%20Classes%20do%20pacote%20%C3%BAtil.png' /> </p>
<p align='center'>Figura 8 – Classes do pacote útil </p>

<br><br>
Na próxima figura podemos observar as classes que foram criadas no pacote br.edu.ifsp.ddm.ifbok<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/ddm.ifbok.png' />	 </p>
<p align='center'>Figura 9 – Classes do Pacote br.edu.ifsp.ddm.ifbok </p>

<br><br>
<h3>1.2 WEB SERVICE (Servidor)</h3>
<br>
Para criar um projeto estruturado, dividindo em camadas, que são responsáveis por determinadas ações. Na Figura abaixo podemos observar que foram criados pacotes para conter as classes de DAO, um pacote para as classes do modelo e por fim um pacote para adicionar as classes do resources.<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/web1.png' /> </p>
<p align='center'>Figura 10 – Projeto do WebService </p>

<br><br>
As classes contidas no pacote resources preparam as informações vindas do banco para fornecer para a aplicação Android. Abaixo podem ser observadas as classes criadas para o pacote Dao.<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/web2.png' /> </p>
<p align='center'>Figura 11 – Classes do Pacote Dao WebService </p>

<br><br>
Abaixo pode ser observado as classes criadas para os pacotes Exception<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/web3.png' /> </p>
<p align='center'>Figura 12 – Classes do Pacote Exception </p>

<br><br>
Abaixo pode ser observado as classes criadas para os pacotes Jdbc<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/web4.png' /> </p>
<p align='center'>Figura 13 – Classes do Pacote Jdbc </p>

<br><br>
Abaixo pode ser observado as classes criadas para os pacotes Modelo<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/web5.png' /> </p>
<p align='center'>Figura 14 – Classes dos Pacotes Modelo </p>

<br><br>
Abaixo pode ser observado as classes criadas para os pacotes Resources<br>
<p align='center'><img src='https://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/web6.png' /> </p>
<p align='center'>Figura 15 – Classes dos Pacotes Resources </p>

<h2>2. IMPLANTAÇÃO</h2>
Com o objetivo de orientar as pessoas que desejarem implantar o sistema no Instituto Federal, foi elaborado um planejamento de sua implantação, que pode ser visto nas próximas seções.<br>
<br>
<h3>2.1. PLANEJAMENTO DA IMPLANTAÇÃO</h3>
O sistema IFBOOk foi concebido para ser executado pelos seguintes softwares:<br>
<br>Apache Tomcat Versão 7.0.34.0: O Apache Tomcat é um servidor web Java de aplicações JEE, mais especificamente, um contêiner de servlets. Um tutorial de instalação para esta versão do Tomcat pode ser encontrado em:<br>
<br><br>Linux: <a href='http://desenvolvedorinteroperavel.wordpress.com/2012/03/18/instalacao-do-tomcat-7-e-java-jdk-1-7-no-centos-fedora-e-red-hat/'>http://desenvolvedorinteroperavel.wordpress.com/2012/03/18/instalacao-do-tomcat-7-e-java-jdk-1-7-no-centos-fedora-e-red-hat/</a>
<br><br>Windows: <a href='http://www.oficinadanet.com.br/artigo/992/instalacao_e_configuracao_do_tomcat'>http://www.oficinadanet.com.br/artigo/992/instalacao_e_configuracao_do_tomcat</a>
<br>MySQL Versão 5.1.34-community: O MySQL é um sistema de gerenciamento de banco de dados (SGBD), que utiliza a linguagem SQL (Linguagem de Consulta Estruturada, do inglês Structured Query Language) como interface. Um tutorial de instalação para esta versão doMysql pode ser encontrado em:<br>
<br><br>Linux: <a href='http://www.vivaolinux.com.br/dica/Instalando-MySQL-5.1-no-Debian-5-x86_64-pelo-aptget'>http://www.vivaolinux.com.br/dica/Instalando-MySQL-5.1-no-Debian-5-x86_64-pelo-aptget</a>
Windows: <a href='http://www.sistemabasico.com.br/2011/06/30/instalando-o-mysql-5-5-8-no-windows-7/'>http://www.sistemabasico.com.br/2011/06/30/instalando-o-mysql-5-5-8-no-windows-7/</a>
<br><br>O arquivo com a implementação da Base de dados e alguns dados pré-inseridos é fornecido por um arquivo sem extensão com nome ifbook. Para restaurar a base dados através do arquivo você deve possuir o aplicativo Mysql Administrator, um software do pacote de instalação MySQL WorkBench.<br>
<br><br>Ao executar o programa a tela da Figura 16 será exibida:<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/figura22-acessomysql.png' /> </p>
<p align='center'>Figura 16 – Acesso MySQL </p>

<br>Após  preencher os dados relativos a conexão com o servidor de banco de dados Mysql a seguinte janela será exibida:<br>
<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/banco.png' /> </p>
<p align='center'>Figura 17 – Acesso MySQL </p>

Com a Base de Dados restaurada, o próximo passo é implantar o arquivo ifbook.WAR que contem os serviços, dentro de um servidor Tomcat pré-configurado. Tutoriais para a instalação e confiração do Tomcat em diversas plataformas pode ser obtido em: <a href='http://www.mhavila.com.br/topicos/java/tomcat.html'>http://www.mhavila.com.br/topicos/java/tomcat.html</a>
<br><br>
Abra um navegador de sua preferência e se conecte a <a href='http://10.0.0.102:8080'>http://10.0.0.102:8080</a> . E insira o usuário e senha. Se você não sabe qual é o usuário e a senha do servidor procure no arquivo CATALINA_HOME/conf/tomcat-users.xml e veja e cheque a configuração.<br>
<br><br>
Role a página até seção chamada "Deploy directory or WAR file located on server". Insira o nome do contexto precedido por “/” (ex /sdn) no campo de Caminho de contexto.<br>
<br>Localize o arquivo .WAR do SDN chamado sdn.WAR e entre com o caminho completo do arquivo (Ex., /camnho/para/sdn.WAR) no campo Directory URL.<br>
<br>
<br>Esta seção deve se parecer com a exibida na Figura 18:<br>
<p align='center'><img src='http://tsi1sem2014.googlecode.com/svn/wiki/images/execucaoecontrole/deploy.png' /> </p>
<p align='center'>Figura 18- Campo de seleção do arquivo .WAR </p>
<br>Clique em Deploy. A página era recarregar. No topo da página, veja se aparece a mensagem OK - Deployed application at context path /camnho/para/sdn.WAR"<br>
<br><br>
Na seção Applications cheque se exista uma linha representando a aplicação sdn. Pronto, a aplicação já pode ser acessada.