package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
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

		tableName = "Mensagem";
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

	public List<Mensagem> listaMensagens() {
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
		mensagem.setData(null);
		mensagem.setImagem(null);
		AreaInteresseDAO areainteresseDAO = new AreaInteresseDAO(this.context);

		mensagem.setAreaInteresse(areainteresseDAO
				.getByID(cursor.getInt(5)));
		UsuarioDAO usuarioDAO = new UsuarioDAO(this.context);
		mensagem.setUsuario(usuarioDAO.getById((cursor.getInt(6))));

		return mensagem;

	}

	private ContentValues serializeContentValues(Mensagem mensagem) {
		ContentValues values = new ContentValues();
		values.put("idMensagem", mensagem.getIdMensagem());
		values.put("Data", mensagem.getData().toString());
		values.put("Titulo", mensagem.getTitulo());
		values.put("Descricao", mensagem.getDescricao());
		values.put("AreaInteresse_idAreaInteresse", mensagem
				.getAreaInteresse().getIdAreaInteresse());
		//valor fixo para testes
		values.put("Usuario_idUsuario", 1);

		return values;
	}

	private Cursor executeSelect(String selection, String[] selectionArgs,
			String orderBy) {

		return database.query(tableName, campos, selection, selectionArgs,
				null, null, orderBy);

	}

}
