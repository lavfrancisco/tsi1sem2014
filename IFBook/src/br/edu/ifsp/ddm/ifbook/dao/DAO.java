package br.edu.ifsp.ddm.ifbook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO <T extends Object> extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ifbook.sqlite";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_ESTADOCIVIL = "estadocivil";
	public static final String TABLE_USUARIO = "usuario";
	public static final String TABLE_AREAINTERESSE = "areainteresse";
	public static final String TABLE_MENSAGEM = "mensagem";
	public static final String TABLE_CLASSIFICADO = "classificado";
	
	
	protected Context context;
	protected String[] campos;
	protected String tableName;
	
	
	private static final String CREATE_TABLE_ESTADOCIVIL = "CREATE TABLE estadocivil ( "
	+ "idEstadocivil INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, "
	+ "EstadoCivil VARCHAR(20) NOT NULL );";
	
	private static final String CREATE_TABLE_USUARIO = "CREATE TABLE usuario ( "
	+ " idUsuario INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "
	+ " Prontuario VARCHAR(9) NOT NULL , "
	+ " Senha VARCHAR(65) NOT NULL , "
	+ " Nivel INTEGER NOT NULL , "
	+ " Nome VARCHAR(50) NOT NULL , "
	+ " Nascimento DATETIME NOT NULL, "
	+ " Apelido VARCHAR(15) NOT NULL , "
	+ " LocalTrabalho VARCHAR(30) NULL , "
	+ " Cidade VARCHAR(30) NULL , "
	+ " Email VARCHAR(50) NULL , "
	+ " Telefone VARCHAR(16) NULL , "
	+ " Foto BLOB NULL , "
	+ " EstadoCivil_idEstadoCivil INTEGER NOT NULL , "
	+ "   FOREIGN KEY (EstadoCivil_idEstadoCivil) REFERENCES estadocivil (idEstadoCivil) );";
	
	private static final String CREATE_TABLE_AREAINTERESSE = "CREATE TABLE areaInteresse ( "
	+ " idAreaInteresse INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "
    + " Tipo INT NOT NULL ,"
	+ " Nome VARCHAR(50) NULL );";
	
	private static final String CREATE_TABLE_MENSAGEM = "CREATE TABLE mensagem ( "
	+ "  idMensagem INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "
	+ "  Titulo VARCHAR(50) NOT NULL , "
	+ "  Descricao VARCHAR(45) NOT NULL , "
	+ "  Data DATETIME NULL, "
	+ "  Imagem BLOB NULL , "
	+ "  AreaInteresse_idAreaInteresse INTEGER NOT NULL , "
	+ "  Usuario_idUsuario INTEGER NOT NULL , "
	+ "  FOREIGN KEY (AreaInteresse_idAreaInteresse) REFERENCES areaInteresse (idAreaInteresse), "
	+ "  FOREIGN KEY (Usuario_idUsuario) REFERENCES usuario (idUsuario) );";
	
	private static final String CREATE_TABLE_CLASSIFICADO = "CREATE TABLE classificado ( "
	+ "  idClassificado INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "
	+ "  Titulo VARCHAR(50) NULL , "
	+ "  Descricao VARCHAR(600) NULL , "
	+ "  Data DATETIME NULL, "
	+ "  Imagem BLOB NULL , "
	+ "  AreaInteresse_idAreaInteresse INTEGER NOT NULL , "
	+ "  Usuario_idUsuario INTEGER NOT NULL , "
	+ "  FOREIGN KEY (AreaInteresse_idAreaInteresse) REFERENCES areaInteresse (idAreaInteresse), "
	+ "  FOREIGN KEY (Usuario_idUsuario) REFERENCES usuario (idUsuario) );";


	public DAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_ESTADOCIVIL);
		database.execSQL(CREATE_TABLE_USUARIO);
		database.execSQL(CREATE_TABLE_AREAINTERESSE);
		database.execSQL(CREATE_TABLE_MENSAGEM);
		database.execSQL(CREATE_TABLE_CLASSIFICADO);
		
		database.execSQL("INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (1,'Casado(a)'););");
		database.execSQL("INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (2,'Solteiro(a)');");
		database.execSQL("INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (3,'Divorciado(a)');");
		database.execSQL("INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (4,'Separado(a)');");
		database.execSQL("INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (5,'Viúvo(a)');");
		database.execSQL("INSERT INTO estadocivil (idEstadoCivil,EstadoCivil) VALUES (6,'União Estável');");
		/* 
		 * SENHAS 'CRIPTOGRAFADAS' usando o algoritimo SHA-256 nÃ£o alterar!!! Exceto se vocÃª adicionar uma hash gerada por esse algoritimo
		 * as senhas para efetuar login sÃ£o 123456 para todos usuarios 
		 * 
		 */
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (1,'bv1111248','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',2,'Danilo Oliveira Bezerra','1988-05-10','Twistão','21','Aguaí','daniloob_361@hotmail.coml','(19)99329-5108',NULL,6);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (2,'bv1110977','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Ricardo Borges Júnior','1988-05-09','OrÃ¡culo','Lady Night','São João da Boa Vista','ricardoborges.jr@gmail.com','(19)99323-2716',NULL,2);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (3,'bv111090x','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Maximilian Silva Chiocheti','1988-05-10','Max','Banco do Brasil','São João da Boa Vista','mchiocheti@hotmail.com','(19)98175-1505',NULL,1);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (4,'bv1111019','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Jessé Cuzini Gestin','1988-05-11','Bigorna','Corujas Noturnas','Vargem Grande do Sul','jessegestin@gmail.com','(19)98287-6259',NULL,2);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (5,'bv1111132','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Tiago Modena','1988-05-12','Papai','Gogo-Boy','Vargem Grande do Sul','tiagoantoniomodena@gmail.com','(19)99399-2055',NULL,3);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (6,'bv1110993','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Simone Consentino Jus','1988-05-13','Si','Casa Cristal','São João da Boa Vista','simone.jus@bol.com.br','(19)98170-8648',NULL,1);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (7,'bv1111124','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Samara Ribeiro Vieira ','1988-05-14','Sah Jonas',NULL,'São João da Boa Vista','samara_skatmusic@hotmail.com','(19)98349-9013',NULL,2);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (8,'bv1111051','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Daiane Ribeiro Pereira','1988-05-15','Daiane','Sercon Web','São João da Boa Vista','daianegalvao123@hotmail.com','',NULL,2);");
	    database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (9,'bv1011073','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Lucas Grabert','1988-05-16','Lucas','','Itatinga','smartzig@gmail.com','(19)9999-7777',NULL,2);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (10,'bv1110837','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Marcio Delarolle','1988-05-17','Marcio','Unimed Leste-Paulista','São João da Boa Vista','marcio.delarolle@gmail.com',NULL,NULL,6);");
	    database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (11,'bv1110195','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Felipe Baldon Benutti','1988-05-18','Pelps','Simpliss Ltda','São João da Boa Vista','fbenuti@gmail.com','(19)9190-0948',NULL,5);");
		database.execSQL("INSERT INTO usuario (idUsuario,Prontuario,Senha,Nivel,Nome,Nascimento,Apelido,LocalTrabalho,Cidade,Email,Telefone,Foto,EstadoCivil_idEstadoCivil) VALUES (12,'bv1110799','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1,'Michele Hermann','1988-05-19','MiHermann','Departamento de Saúde','São João da Boa Vista','michele.hermann@hotmail.com','',NULL,6);");

		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (1,'Compras',2);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (2,'Vendas',2);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (3,'Aluguel',2);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (4,'Vagas de Emprego',2);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (5,'Estágios',2);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (6,'InformÃ¡tica',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (7,'Automação',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (8,'EletrÃ´nica',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (9,'Engenharia',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (10,'Química',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (11,'Administração',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (12,'Eventos',1);");
		database.execSQL("INSERT INTO areainteresse (idAreaInteresse,Nome,Tipo) VALUES (13,'Informativos',1);");
		
		database.execSQL("INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (1,date('now'),'Festa da Galera','Consectetur adipiscing elit. Sed in ipsum rutrum, aliquet tortor vitae, dapibus dui posuere.',NULL,12,1);");
		database.execSQL("INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (2,date('now'),'Novo modo de LaÃ§o em Java','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc arcu turpis, elementum vitae turpis et, euismod sagittis mi. Aliquam erat volutpat. Vestibulum elementum, leo vitae bibendum orci aliquam',NULL,6,2);");
	    database.execSQL("INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (3,date('now'),'Semana da Tecnologia','Duis varius, purus ac venenatis dapibus, purus ipsum feugiat tortor, nec ullamcorper turpis quam at eros. Curabitur sed.',NULL,12,5);");
		database.execSQL("INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (4,date('now'),'Auxilio Estundatil','Sed sodales sapien ultrices, tristique velit at, adipiscing tortor. Mauris pulvinar, orci at commodo molestie.',NULL,13,12);");
		database.execSQL("INSERT INTO mensagem (idMensagem,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (5,date('now'),'Testando','Integer aliquam massa in justo facilisis condimentum. Donec fringilla lobortis lectus et condimentum.',NULL,1,1);");

		
	    database.execSQL("INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (1,date('now'),'Vendo um Tablet','Sed sodales sapien ultrices, tristique velit at, adipiscing tortor. Mauris pulvinar, orci at commodo molestie, nisi sed.',NULL,2,10);");
		database.execSQL("INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (2,date('now'),'Estágios','Nullam sit amet quam diam. Integer velit justo, mattis sit amet erat eget, suscipit iaculis velit. In magna metus metus.',NULL,5,9);");
	    database.execSQL("INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (3,date('now'),'Alugo um quarto','Etiam et mi venenatis lectus dignissim vestibulum. Nullam interdum consequat nulla, at auctor lectus consequat volutpat.',NULL,3,11);");
		database.execSQL("INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (4,date('now'),'Quero um PlayStation18','Integer aliquam massa in justo facilisis condimentum. Donec fringilla lobortis lectus et condimentum.',NULL,1,4);");
		database.execSQL("INSERT INTO classificado (idClassificado,Data,Titulo,Descricao,Imagem,AreaInteresse_idAreaInteresse,Usuario_idUsuario) VALUES (5,date('now'),'Testando','Integer aliquam massa in justo facilisis condimentum. Donec fringilla lobortis lectus et condimentum.',NULL,1,1);");

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ESTADOCIVIL);
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USUARIO);
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_AREAINTERESSE);
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MENSAGEM);
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CLASSIFICADO);
		
		onCreate(db);
	}
	
	
	
	protected void fecharConexao(Cursor cursor) {
		if (!cursor.isClosed()) {
			cursor.close();
			close();
		}

	}
	
	protected void fecharConexao() {		
			close();		

	}


}	

