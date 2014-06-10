package br.edu.ifsp.ddm.ifbook.dao;


import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.rest.MensagemREST;

public class MensagemDAO {

	private MensagemREST rest = new MensagemREST("mensagem");

	public Mensagem getByID(Integer id) {
		System.out.println("DAO ID: "+id);	
		Mensagem mensagem = null;
		try {
			mensagem = rest.getMensagem(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mensagem;
	}

	public List<Mensagem> listAll() {
		List<Mensagem> list = new ArrayList<Mensagem>();
		try {
			list = rest.getListaMensagens();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	public boolean atualizar(Mensagem mensagem) {
		try {
			if (rest.atualizar(mensagem).equalsIgnoreCase(
					"Mensagem atualizada!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean deletar(Integer id) {
		try {
			if (rest.deletar(id).equalsIgnoreCase("MENSAGEM REMOVIDA!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	
	public List<Mensagem> listAll2(int id) {
		List<Mensagem> list = new ArrayList<Mensagem>();
		try {
			list = rest.getListaMinhasMenssagens(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public boolean salvar(Mensagem mensagem) {

		try {
			if (rest.inserirMensagem(mensagem).equalsIgnoreCase("MENSAGEM INSERIDA!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
}


	//public boolean salvar(Usuario usuario) {

		//try {
	//		if (rest.inserirPessoa(usuario).equalsIgnoreCase("USUARIO INSERIDO!"))
	//			return true;
	//	} catch (Exception e) {
		//	e.printStackTrace();
	//		return false;
	//	}
	//	return false;
	//}

	//public boolean deletar(Integer id) {
	//	try {
		//	if (rest.deletarPessoa(id).equalsIgnoreCase("PESSOA REMOVIDA!"))
		//		return true;
		//} catch (Exception e) {
		//	e.printStackTrace();
		//	return false;
		//}
	//	return false;
	//}


/*import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO extends DAO<Usuario>{
	private SQLiteDatabase database;
	private Context local_context;
	
	public UsuarioDAO(Context context) {
		super(context);
		campos = new String[]{"idUsuario","Nome", "Nascimento", "Prontuario", "Senha", "Apelido", "LocalTrabalho", "Cidade", "Email", "Telefone", "Foto", "EstadoCivil_idEstadoCivil","Nivel"};
		tableName = "usuario";
		local_context = context;
		database = getWritableDatabase();
				
	}
	


	
	private Usuario serializeByCursor(Cursor cursor)
	{
		Usuario usuario = new Usuario();
		EstadoCivil estadoCivil;
		
		usuario.setIdUsuario(cursor.getInt(0));
		usuario.setNome(cursor.getString(1));
	    usuario.setNascimento(cursor.getString(2));
	    usuario.setProntuario(cursor.getString(3));
	    usuario.setSenha(cursor.getString(4));
	    usuario.setApelido(cursor.getString(5));
	    usuario.setLocalTrabalho(cursor.getString(6));
	    usuario.setCidade(cursor.getString(7));
	    usuario.setEmail(cursor.getString(8));
	    usuario.setTelefone(cursor.getString(9));
	    usuario.setFoto(cursor.getBlob(10));
	    int numEstCivil = (cursor.getInt(11));	    
	    EstadoCivilDAO estadoDAO = new EstadoCivilDAO(local_context, database);
	    estadoCivil = estadoDAO.getById(numEstCivil);
	    usuario.setIdEstadoCivil(estadoCivil);
	    int nivelUsuario = (cursor.getInt(12));
	    usuario.setNivel(nivelUsuario);
	   
		return usuario;
		
	}
	
	private ContentValues serializeContentValues(Usuario usuario){
		ContentValues values = new ContentValues();
				
		values.put("idUsuario",usuario.getIdUsuario());
		values.put("Nome",usuario.getNome());
		values.put("Nascimento",usuario.getNascimento());
		values.put("Prontuario",usuario.getProntuario());
		values.put("Senha",usuario.getSenha());
		values.put("Apelido",usuario.getApelido());
		values.put("LocalTrabalho",usuario.getLocalTrabalho());
		values.put("Cidade",usuario.getCidade());
		values.put("Email",usuario.getEmail());
		values.put("Telefone",usuario.getTelefone());
		values.put("Foto",usuario.getFoto());
		values.put("EstadoCivil_idEstadoCivil",usuario.getIdEstadoCivil().getIdEstadoCivil());
		values.put("Nivel", usuario.getNivel());
		
		return values;
	}

	private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
	{
		return database.query(tableName,campos,selection, selectionArgs, null, null, orderBy);

	}
	
	
	
	public List<Usuario> Aniversariantes() {
		List<Usuario> list = new ArrayList<Usuario>();
		String sql = "SELECT idUsuario,Nome, strftime('%m/%d', Nascimento), Prontuario, Senha, Apelido, LocalTrabalho, Cidade, Email, Telefone, Foto, EstadoCivil_idEstadoCivil, Nivel FROM usuario WHERE strftime('%m/%d', Nascimento) >= strftime('%m/%d',date('now')) AND strftime('%m/%d', Nascimento) <= strftime('%m/%d',date('now','+7 day')) ORDER BY Nascimento;";
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				
				
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		fecharConexao(cursor);
		
		return list;
		
		
	}

	
	public List<Usuario> listAll() {
		List<Usuario> list = new ArrayList<Usuario>();
		Cursor cursor = executeSelect(null, null, "1");
		

		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				
				
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		fecharConexao(cursor);
		
		return list;	
	
	}
	
	public Usuario getByProntuario (String prontuario){
		Usuario usuario = new Usuario();
			
		Cursor cursor = executeSelect("Prontuario = ?", new String[]{prontuario}, null);
		
		if(cursor!=null && cursor.moveToFirst())
		{
			usuario = serializeByCursor(cursor);
		}
		fecharConexao(cursor);
		
		return usuario;
	}
	
	public Usuario getById (int id){
		Usuario usuario = new Usuario();
			
		Cursor cursor = executeSelect("idUsuario = ?", new String[]{String.valueOf(id)}, null);
		
		if(cursor!=null && cursor.moveToFirst())
		{
			usuario = serializeByCursor(cursor);
		}
		fecharConexao(cursor);
		
		return usuario;
	}
	
	public boolean autualizar(Usuario usuario){
		ContentValues values = serializeContentValues(usuario);
		if(database.update(tableName, values, "idUsuario = ?", new String[]{String.valueOf(usuario.getIdUsuario())})>0){
			close();
			return true;
		}
		else{
			try {
				clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
	


/*package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MensagemDAO extends DAO<Mensagem> {

	private SQLiteDatabase database;

	public MensagemDAO(Context context) {
		super(context);
		this.context = context;
		campos = new String[] { "idMensagem", "Titulo", "Descricao", "Data",
				"Imagem", "AreaInteresse_idAreaInteresse", "Usuario_idUsuario" };

		tableName = "mensagem";
		database = getWritableDatabase();
	}

	public List<Mensagem> listAll() {
		List<Mensagem> list = new ArrayList<Mensagem>();
		Cursor cursor = executeSelect(null, null, "1");

		if (cursor != null && cursor.moveToFirst()) {
			do {
				list.add(serializeByCursor(cursor));
			} while (cursor.moveToNext());

		}

		if (!cursor.isClosed()) {
			cursor.close();
		}

		return list;

	}

	public List<Mensagem> listAll(int id) {
		List<Mensagem> list = new ArrayList<Mensagem>();

		Cursor cursor = executeSelect("Usuario_idUsuario = ?", new String[]{String.valueOf(id)}, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				list.add(serializeByCursor(cursor));
			} while (cursor.moveToNext());

		}

		if (!cursor.isClosed()) {
			cursor.close();
		}

		return list;

	}

	public boolean inserir(Mensagem mensagem) {
		ContentValues values = serializeContentValues(mensagem);
		if (database.insert(tableName, null, values) > 0)
			return true;
		else
			return false;
	}
	
	public boolean atualizar(Mensagem mensagem) {
		ContentValues values = serializeContentValues(mensagem);
		if (database.update(tableName, values, "idMensagem = ?",
				new String[] { String.valueOf(mensagem.getIdMensagem()) }) > 0)
			return true;
		else
			return false;
	}

	public boolean deletar(Integer id) {
		System.out.println("ID MENSSAGEM: "+ id);
		if (database.delete(tableName, "idMensagem = ?",
				new String[] { String.valueOf(id) }) > 0) {
			fecharConexao();
			return true;
			
		} else {
			fecharConexao();
			return false;
		}
	}

	private Mensagem serializeByCursor(Cursor cursor) {
		Mensagem mensagem = new Mensagem();
		mensagem.setIdMensagem(cursor.getInt(0));
		mensagem.setTitulo(cursor.getString(1));
		mensagem.setDescricao(cursor.getString(2));
		mensagem.setData(cursor.getString(3));
		mensagem.setImagem(cursor.getBlob(4));
		AreaInteresseDAO areainteresseDAO = new AreaInteresseDAO(this.context);

		mensagem.setAreaInteresse(areainteresseDAO
				.getByID(cursor.getInt(5)));
		UsuarioDAO usuarioDAO = new UsuarioDAO(this.context);
		mensagem.setUsuario(usuarioDAO.getById((cursor.getInt(6))));

		return mensagem;

	}
	
	
	public List<Mensagem> listAll2() {
		List<Mensagem> list = new ArrayList<Mensagem>();
		String sql = "SELECT idMensagem, Titulo, Descricao, strftime('%d/%m/%Y %H:%M:%S', Data), Imagem, AreaInteresse_idAreaInteresse, Usuario_idUsuario FROM mensagem ORDER BY idMensagem DESC;";
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				
				
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		fecharConexao(cursor);
		
		return list;
		
		
	}
	
	public Mensagem getById (int id){
		Mensagem mensagem = new Mensagem();
			
		Cursor cursor = executeSelect("idMensagem = ?", new String[]{String.valueOf(id)}, null);
		
		if(cursor!=null && cursor.moveToFirst())
		{
			mensagem = serializeByCursor(cursor);
		}
		fecharConexao(cursor);
		
		return mensagem;
	}

	private ContentValues serializeContentValues(Mensagem mensagem) {
		ContentValues values = new ContentValues();
		
		values.put("idMensagem", mensagem.getIdMensagem());
		values.put("Titulo", mensagem.getTitulo());
		values.put("Descricao", mensagem.getDescricao());
		values.put("Data", mensagem.getData());
		values.put("Imagem", mensagem.getImagem());
		values.put("AreaInteresse_idAreaInteresse", mensagem.getAreaInteresse().getIdAreaInteresse());
		values.put("Usuario_idUsuario", mensagem.getUsuario().getIdUsuario());

		return values;
	}

	private Cursor executeSelect(String selection, String[] selectionArgs,
			String orderBy) {

		return database.query(tableName, campos, selection, selectionArgs,
				null, null, orderBy);

	}

}
*/