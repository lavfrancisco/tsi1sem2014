package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.AreaInteresse;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AreaInteresseDAO extends DAO<AreaInteresse> {


	private SQLiteDatabase database;
	
	public AreaInteresseDAO(Context context) {
		super(context);
		campos = new String[]{"idAreaInteresse","Tipo","Nome"};
		
		tableName = "areaInteresse";
		database = getWritableDatabase();	
	}

	
	public List<AreaInteresse> listAll() {
		List<AreaInteresse> list = new ArrayList<AreaInteresse>();
		Cursor cursor = executeSelect(null, null, "1");
		

		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		if(!cursor.isClosed())
		{
			cursor.close();
		}
		
		return list;
		
		
	}
	
	public boolean atualizar(AreaInteresse areainteresse) {
		ContentValues values = serializeContentValues(areainteresse);
		if(database.update(tableName, values, "id = ?", new String[]{String.valueOf(areainteresse.getIdAreaInteresse())})>0)
			return true;
		else
			return false;
	}		
	
	public boolean deletar(Integer id) {
		if(database.delete(tableName, "id = ?", new String[]{String.valueOf(id)})>0)
			return true;
		else
			return false;
	}
	
	
	
	private AreaInteresse serializeByCursor(Cursor cursor)
	{
		AreaInteresse areainteresse = new AreaInteresse();
		areainteresse.setIdAreaInteresse(cursor.getInt(0));
		areainteresse.setTipo(cursor.getInt(1));
		areainteresse.setNome(cursor.getString(2));		
		
		return areainteresse;
		
	}
	
	private ContentValues serializeContentValues(AreaInteresse areainteresse)
	{
		ContentValues values = new ContentValues();
		values.put("IdAreaInteresse", areainteresse.getIdAreaInteresse());
		values.put("Tipo", areainteresse.getTipo());
		values.put("Nome", areainteresse.getNome());
		
		
		return values;
	}	
	
	
	private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
	{
		
		return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);		

	}
	
	
	public AreaInteresse getByID(Integer id) {
		AreaInteresse area = new AreaInteresse();
		Cursor cursor = executeSelect("idAreaInteresse = ?",
				new String[] { String.valueOf(id) }, "idAreaInteresse");

		if (cursor != null && cursor.moveToFirst()) {

			area = serializeByCursor(cursor);

		}

		fecharConexao(cursor);

		return area;

	}
	
}
