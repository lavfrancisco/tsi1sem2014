package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.rest.UsuarioREST;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO extends DAO<Usuario>{
	
	private UsuarioREST rest = new UsuarioREST("usuario");
	
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
		try {
			list = rest.getListaUsuarios();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	public Usuario getByProntuario (String prontuario){
		Usuario usuario = null;
		try {
			usuario = rest.getProntuario(prontuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
	public Usuario getById (int id){
		Usuario usuario = null;
		try {
			usuario = rest.getUsuario(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
	public boolean autualizar(Usuario usuario){
		try {
			if (rest.atualizar(usuario).equalsIgnoreCase(
					"Usuario Atualizada!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
}