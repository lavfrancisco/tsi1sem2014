package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;



	/*public class EstadoCivilDAO  {

		private EstadoCivilREST rest = new EstadoCivilREST("estadocivil");

		public EstadoCivil getByID(Integer id) {
			EstadoCivil estadoCivil = null;
			try {
				estadoCivil = rest.getEstadoCivil(id);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return estadoCivil;
		}

		public List<EstadoCivil> listAll() {
			List<EstadoCivil> list = new ArrayList<EstadoCivil>();
			try {
				list = rest.getListaEstadoCivis();
			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;

		}

	}

*/

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EstadoCivilDAO extends DAO<EstadoCivil>{
	private SQLiteDatabase database;
	
	public EstadoCivilDAO(Context context) {
		super(context);
		campos = new String[]{"idEstadoCivil", "EstadoCivil"};
		tableName = "estadocivil";
		database = getWritableDatabase();
	}
	
	public EstadoCivilDAO(Context context, SQLiteDatabase db) {
		super(context);
		campos = new String[]{"idEstadoCivil", "EstadoCivil"};
		tableName = "estadocivil";
		database = db;
	}
	
	
	private EstadoCivil serializeByCursor(Cursor cursor){
		EstadoCivil estado = new EstadoCivil();
		estado.setIdEstadoCivil(cursor.getInt(0));
		estado.setEstadoCivil(cursor.getString(1));
		
		return estado;
	}
	
	private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
	{
		return database.query(tableName,campos,selection, selectionArgs, null, null, orderBy);		
	}
	
	public List<EstadoCivil> listAll() {
		List<EstadoCivil> list = new ArrayList<EstadoCivil>();
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
			close();
		}
		
		return list;	
	
	}
	
	public EstadoCivil getById (int id){
		EstadoCivil estado = new EstadoCivil();
			
		Cursor cursor = executeSelect("idEstadoCivil = ?", new String[]{String.valueOf(id)}, null);
		
		if(cursor!=null && cursor.moveToFirst())
		{
			estado = serializeByCursor(cursor);
		}
		if(!cursor.isClosed())
		{
			cursor.close();
			close();
		}
		
		return estado;
	}	
}
