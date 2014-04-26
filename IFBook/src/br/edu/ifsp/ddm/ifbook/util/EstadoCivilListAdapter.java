package br.edu.ifsp.ddm.ifbook.util;

import java.util.List;
import java.util.zip.Inflater;




import br.edu.ifsp.ddm.ifbook.R;
import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;


import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EstadoCivilListAdapter extends BaseAdapter{
	
	private Context context;
	private List<EstadoCivil> lista;
	
	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EstadoCivil e = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.perfil_editar, null);
		
		TextView estado = (TextView) view.findViewById(R.id.estado_civil);
		
		return null;
	}

}
