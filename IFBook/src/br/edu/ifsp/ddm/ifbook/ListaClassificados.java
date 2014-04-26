package br.edu.ifsp.ddm.ifbook;

import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaClassificados  extends BaseAdapter {
	
	

		private Context context;
		private List<Classificado> lista;

		public ListaClassificados(Context context, List<Classificado> lista) {
			this.context = context;
			this.lista = lista;
		}

		public int getCount() {
			return lista.size();
			
		}
		


		public Object getItem(int position) {
			return lista.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			Classificado c = lista.get(position);
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.classificados, null);
			
			TextView id = (TextView) view.findViewById(R.id.textViewIdClassificados);
			id.setText("ID: "+String.valueOf(c.getIdClassificado()));
			
			
			TextView nome = (TextView) view.findViewById(R.id.textViewUsuario);
			nome.setText(c.getUsuario_idUsuario().getNome());
			
			TextView titulo = (TextView) view.findViewById(R.id.textViewTitulo);
			titulo.setText(c.getTitulo());
			
			TextView descricao = (TextView) view.findViewById(R.id.textViewDescricao);
			descricao.setText(c.getDescricao());
			
			TextView area = (TextView) view.findViewById(R.id.textViewAreaInteresse);
			area.setText(c.getAreaInteresse_idAreaInteresse().getNome());
			
			
			
			return view;
		}
	}