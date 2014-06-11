package br.edu.ifsp.ddm.ifbook.util;



import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class EstadoCivilListAdapter extends ArrayAdapter<EstadoCivil>{

	private Context context;
	private List <EstadoCivil> relacionamentos;
	
	public EstadoCivilListAdapter(Context context, int textViewResourceId, List <EstadoCivil> relacionamentos) {
		super(context, textViewResourceId);
		this.context = context;
		this.relacionamentos = relacionamentos;
	}
	
	public int getCount(){
		return relacionamentos.size();
	}
	
	public EstadoCivil getItem(int position){
		return relacionamentos.get(position);
	}
	
	public long getItemId(int position){
		return position;
	}
	
	public View getDropDownView(int position, int convertView, ViewGroup parent){
		TextView label = new TextView(context);
		label.setText(relacionamentos.get(position).getEstadoCivil());
		return label;
	}
	
	public View getView(int position, int convertView, ViewGroup parent){
		TextView label = new TextView(context);
		label.setText(relacionamentos.get(position).getEstadoCivil());
		return label;
	}
}
