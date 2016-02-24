CREATE DATABASE ifbook;

USE ifbook;

CREATE TABLE estadocivil (
> idEstadocivil INTEGER PRIMARY KEY  AUTO\_INCREMENT  NOT NULL,
> EstadoCivil VARCHAR(20) NOT NULL );

> CREATE TABLE usuario (
> > idUsuario INTEGER PRIMARY KEY  AUTO\_INCREMENT  NOT NULL ,
> > Prontuario VARCHAR(9) NOT NULL ,
> > Senha VARCHAR(65) NOT NULL ,
> > Nivel INTEGER NOT NULL ,
> > Nome VARCHAR(50) NOT NULL ,
> > Nascimento DATETIME NOT NULL,
> > Apelido VARCHAR(15) NOT NULL ,
> > LocalTrabalho VARCHAR(30) NULL ,
> > Cidade VARCHAR(30) NULL ,
> > Email VARCHAR(50) NULL ,

> Telefone VARCHAR(16) NULL ,
> > Foto BLOB NULL ,
> > EstadoCivil\_idEstadoCivil INTEGER NOT NULL ,
> > FOREIGN KEY (EstadoCivil\_idEstadoCivil) REFERENCES estadocivil (idEstadoCivil) );


> CREATE TABLE areaInteresse (
> idAreaInteresse INTEGER PRIMARY KEY  AUTO\_INCREMENT  NOT NULL ,
> > Tipo INT NOT NULL ,
> > Nome VARCHAR(50) NULL );


> CREATE TABLE mensagem (
> > idMensagem INTEGER PRIMARY KEY  AUTO\_INCREMENT  NOT NULL ,
> > Titulo VARCHAR(50) NOT NULL ,
> > Descricao VARCHAR(200) NOT NULL ,
> > Data DATETIME NULL,
> > Imagem BLOB NULL ,
> > AreaInteresse\_idAreaInteresse INTEGER NOT NULL ,
> > Usuario\_idUsuario INTEGER NOT NULL ,
> > FOREIGN KEY (AreaInteresse\_idAreaInteresse) REFERENCES areaInteresse (idAreaInteresse),
> > FOREIGN KEY (Usuario\_idUsuario) REFERENCES usuario (idUsuario) );


> CREATE TABLE classificado (
> > idClassificado INTEGER PRIMARY KEY  AUTO\_INCREMENT  NOT NULL ,
> > Titulo VARCHAR(50) NULL ,
> > Descricao VARCHAR(200) NULL ,
> > Data DATETIME NULL,
> > Imagem BLOB NULL ,
> > AreaInteresse\_idAreaInteresse INTEGER NOT NULL ,
> > > Usuario\_idUsuario INTEGER NOT NULL ,

> > FOREIGN KEY (AreaInteresse\_idAreaInteresse) REFERENCES areaInteresse (idAreaInteresse),
> > FOREIGN KEY (Usuario\_idUsuario) REFERENCES usuario (idUsuario) );



> INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (1,'Casado(a)');
> INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (2,'Solteiro(a)');
> INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (3,'Divorciado(a)');
> INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (4,'Separado(a)');
> INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (5,'Viúvo(a)');
> INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (6,'União Estável');


> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (1,'bv1111248','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',2,'Danilo Oliveira Bezerra','1988-05-29','Twistão','21','Aguaí','daniloob\_361@hotmail.coml','(19)99329-5108',NULL,6);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (2,'bv1110977','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Ricardo Borges Júnior','1988-05-30','Oráculo','Lady Night','São João da Boa Vista','ricardoborges.jr@gmail.com','(19)99323-2716',NULL,2);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (3,'bv111090x','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Maximilian Silva Chiocheti','1988-05-31','Max','Banco do Brasil','São João da Boa Vista','mchiocheti@hotmail.com','(19)98175-1505',NULL,1);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (4,'bv1111019','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Jessé Cuzini Gestin','1988-06-01','Bigorna','Corujas Noturnas','Vargem Grande do Sul','jessegestin@gmail.com','(19)98287-6259',NULL,2);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (5,'bv1111132','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Tiago Modena','1988-06-02','Papai','Gogo-Boy','Vargem Grande do Sul','tiagoantoniomodena@gmail.com','(19)99399-2055',NULL,3);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (6,'bv1110993','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Simone Consentino Jus','1988-06-03','Si','Casa Cristal','São João da Boa Vista','simone.jus@bol.com.br','(19)98170-8648',NULL,1);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (7,'bv1111124','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Samara Ribeiro Vieira ','1988-06-04','Sah Jonas',NULL,'São João da Boa Vista','samara\_skatmusic@hotmail.com','(19)98349-9013',NULL,2);
> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (8,'bv1111051','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Daiane Ribeiro Pereira','1988-06-05','Daiane','Sercon Web','São João da Boa Vista','daianegalvao123@hotmail.com','',NULL,2);
> > INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (9,'bv1011073','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Lucas Grabert','1988-06-06','Lucas','','Itatinga','smartzig@gmail.com','(19)9999-7777',NULL,2);

> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (10,'bv1110837','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Marcio Delarolle','1988-06-07','Marcio','Unimed Leste-Paulista','São João da Boa Vista','marcio.delarolle@gmail.com',NULL,NULL,6);
> > INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (11,'bv1110195','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Felipe Baldon Benutti','1988-06-08','Pelps','Simpliss Ltda','São João da Boa Vista','fbenuti@gmail.com','(19)9190-0948',NULL,5);

> INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil\_idEstadoCivil) VALUES (12,'bv1110799','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Michele Hermann','1988-06-09','MiHermann','Departamento de Saúde','São João da Boa Vista','michele.hermann@hotmail.com','',NULL,6);

> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (1,'Compras',2);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (2,'Vendas',2);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (3,'Aluguel',2);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (4,'Vagas de Emprego',2);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (5,'Estágios',2);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (6,'Informática',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (7,'Automação',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (8,'Eletrônica',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (9,'Engenharia',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (10,'Química',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (11,'Administração',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (12,'Eventos',1);
> INSERT INTO areaInteresse (idAreaInteresse,Nome,Tipo) VALUES (13,'Informativos',1);

> INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (1,NOW(),'Festa da Galera','Consectetur adipiscing elit. Sed in ipsum rutrum, aliquet tortor vitae, dapibus dui posuere.',NULL,12,1);
> INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (2,NOW(),'Novo modo de LaÃ§o em Java','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc arcu turpis, elementum vitae turpis et, euismod sagittis mi. Aliquam erat volutpat. Vestibulum elementum, leo vitae bibendum orci aliquam',NULL,6,2);
> > INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (3,NOW(),'Semana da Tecnologia','Duis varius, purus ac venenatis dapibus, purus ipsum feugiat tortor, nec ullamcorper turpis quam at eros. Curabitur sed.',NULL,12,5);

> INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (4,NOW(),'Auxilio Estundatil','Sed sodales sapien ultrices, tristique velit at, adipiscing tortor. Mauris pulvinar, orci at commodo molestie.',NULL,13,12);
> INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (5,NOW(),'Testando','Integer aliquam massa in justo facilisis condimentum. Donec fringilla lobortis lectus et condimentum.',NULL,1,1);


> INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (1,NOW(),'Vendo um Tablet','Sed sodales sapien ultrices, tristique velit at, adipiscing tortor. Mauris pulvinar, orci at commodo molestie, nisi sed.',NULL,2,10);
> INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (2,NOW(),'Estágios','Nullam sit amet quam diam. Integer velit justo, mattis sit amet erat eget, suscipit iaculis velit. In magna metus metus.',NULL,5,9);
> > INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (3,NOW(),'Alugo um quarto','Etiam et mi venenatis lectus dignissim vestibulum. Nullam interdum consequat nulla, at auctor lectus consequat volutpat.',NULL,3,11);

> INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (4,NOW(),'Quero um PlayStation18','Integer aliquam massa in justo facilisis condimentum. Donec fringilla lobortis lectus et condimentum.',NULL,1,4);
> INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse\_idAreaInteresse,Usuario\_idUsuario) VALUES (5,NOW(),'Testando','Integer aliquam massa in justo facilisis condimentum. Donec fringilla lobortis lectus et condimentum.',NULL,1,1);
