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

		tableName = "Classificado";
		database = getWritableDatabase();
	}

	public List<Classificado> listAll() {
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
		classificado.setData(null);
		classificado.setImagem(null);
		AreaInteresseDAO areainteresseDAO = new AreaInteresseDAO(this.context);

		classificado.setAreaInteresse_idAreaInteresse(areainteresseDAO
				.getByID(cursor.getInt(5)));
		UsuarioDAO usuarioDAO = new UsuarioDAO(this.context);
		classificado.setUsuario_idUsuario(usuarioDAO.getById((cursor.getInt(6))));

		return classificado;

	}

	private ContentValues serializeContentValues(Classificado classificado) {
		ContentValues values = new ContentValues();
		values.put("idClassificado", classificado.getIdClassificado());
		values.put("Data", classificado.getData().toString());
		values.put("Titulo", classificado.getTitulo());
		values.put("Descricao", classificado.getDescricao());
		values.put("AreaInteresse_idAreaInteresse", classificado
				.getAreaInteresse_idAreaInteresse().getNome());
		values.put("Usuario_idUsuario", classificado.getUsuario_idUsuario()
				.getNome());

		return values;
	}

	private Cursor executeSelect(String selection, String[] selectionArgs,
			String orderBy) {

		return database.query(tableName, campos, selection, selectionArgs,
				null, null, orderBy);

	}

}
