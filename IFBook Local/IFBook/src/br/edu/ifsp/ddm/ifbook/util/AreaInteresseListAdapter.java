package br.edu.ifsp.ddm.ifbook.util;




import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.AreaInteresse;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AreaInteresseListAdapter extends ArrayAdapter<AreaInteresse>{

	private Context context;
	private List <AreaInteresse> relacionamentos;
	
	public AreaInteresseListAdapter(Context context, int textViewResourceId, List <AreaInteresse> relacionamentos) {
		super(context, textViewResourceId);
		this.context = context;
		this.relacionamentos = relacionamentos;
	}
	
	public int getCount(){
		return relacionamentos.size();
	}
	
	public AreaInteresse getItem(int position){
		return relacionamentos.get(position);
	}
	
	public long getItemId(int position){
		return position;
	}
	
	public View getDropDownView(int position, int convertView, ViewGroup parent){
		TextView label = new TextView(context);
		label.setText(relacionamentos.get(position).getIdAreaInteresse());
		return label;
	}
	
	public View getView(int position, int convertView, ViewGroup parent){
		TextView label = new TextView(context);
		label.setText(relacionamentos.get(position).getIdAreaInteresse());
		return label;
	}
}

