package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ClassificadoDAO extends DAO<Classificado> {

	private SQLiteDatabase database;

	public ClassificadoDAO(Context context) {
		super(context);
		this.context = context;
		campos = new String[] { "idClassificado", "Titulo", "Descricao", "Data",
				"Imagem", "AreaInteresse_idAreaInteresse", "Usuario_idUsuario" };

		tableName = "classificado";
		database = getWritableDatabase();
	}
	
	
	public List<Classificado> listAll2() {
		List<Classificado> list = new ArrayList<Classificado>();
		String sql = "SELECT idClassificado, Titulo, Descricao, strftime('%d/%m/%Y %H:%M:%S', Data), Imagem, AreaInteresse_idAreaInteresse, Usuario_idUsuario FROM classificado ORDER BY idClassificado DESC;";
		
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
	

	public List<Classificado> listAll(int id) {
		List<Classificado> list = new ArrayList<Classificado>();

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

	public List<Classificado> listaClassificados() {
		List<Classificado> list = new ArrayList<Classificado>();
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
	
	public boolean inserir(Classificado classificado) {
		
		ContentValues values = serializeContentValues(classificado);
		if(database.insert(tableName, null, values)>0)
			return true;			
		else
			return false;
	}

	public boolean atualizar(Classificado classificado) {
		ContentValues values = serializeContentValues(classificado);
		if (database.update(tableName, values, "idClassificado = ?",
				new String[] { String.valueOf(classificado.getIdClassificado()) }) > 0)
			return true;
		else
			return false;
	}

	public boolean deletar(Integer id) {
		if (database.delete(tableName, "idClassificado = ?",
				new String[] { String.valueOf(id) }) > 0) {
			fecharConexao();
			return true;
			
		} else {
			fecharConexao();
			return false;
		}
	}

	private Classificado serializeByCursor(Cursor cursor) {
		Classificado classificado = new Classificado();
		classificado.setIdClassificado(cursor.getInt(0));
		classificado.setTitulo(cursor.getString(1));
		classificado.setDescricao(cursor.getString(2));
		classificado.setData(cursor.getString(3));
		classificado.setImagem(cursor.getBlob(4));
		AreaInteresseDAO areainteresseDAO = new AreaInteresseDAO(this.context);
		classificado.setAreaInteresse_idAreaInteresse(areainteresseDAO.getByID(cursor.getInt(5)));
		UsuarioDAO usuarioDAO = new UsuarioDAO(this.context);
		classificado.setUsuario_idUsuario(usuarioDAO.getById((cursor.getInt(6))));

		return classificado;

	}

	private ContentValues serializeContentValues(Classificado classificado) {
		ContentValues values = new ContentValues();
		values.put("idClassificado", classificado.getIdClassificado());
		values.put("Titulo", classificado.getTitulo());
		values.put("Descricao", classificado.getDescricao());
		values.put("Data", classificado.getData());
		values.put("Imagem", classificado.getImagem());
		values.put("AreaInteresse_idAreaInteresse", classificado.getAreaInteresse_idAreaInteresse().getIdAreaInteresse());
		values.put("Usuario_idUsuario", classificado.getUsuario_idUsuario().getIdUsuario());
	

		return values;
	}

	private Cursor executeSelect(String selection, String[] selectionArgs,
			String orderBy) {

		return database.query(tableName, campos, selection, selectionArgs,
				null, null, orderBy);

	}

}
