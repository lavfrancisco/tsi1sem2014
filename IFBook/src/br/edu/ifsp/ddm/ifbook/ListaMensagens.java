package br.edu.ifsp.ddm.ifbook;

import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaMensagens  extends BaseAdapter {
	
	

		private Context context;
		private List<Mensagem> lista;

		public ListaMensagens(Context context, List<Mensagem> lista) {
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
			Mensagem m = lista.get(position);
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.mensagens, null);
			
			TextView id = (TextView) view.findViewById(R.id.textViewIdMensagens);
			id.setText("ID: "+String.valueOf(m.getIdMensagem()));
			
			
			TextView nome = (TextView) view.findViewById(R.id.textViewUsuario);
			nome.setText(m.getUsuario().getNome());
			
			TextView titulo = (TextView) view.findViewById(R.id.textViewTitulo);
			titulo.setText(m.getTitulo());
			
			TextView descricao = (TextView) view.findViewById(R.id.textViewDescricao);
			descricao.setText(m.getDescricao());
			
			TextView area = (TextView) view.findViewById(R.id.textViewAreaInteresse);
			area.setText(m.getAreaInteresse().getNome());
			
			
			
			return view;
		}
	}