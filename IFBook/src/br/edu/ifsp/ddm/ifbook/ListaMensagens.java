package br.edu.ifsp.ddm.ifbook;

import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaMensagens  extends BaseAdapter {
	
	

		private Context context;
		private List<Mensagem> lista;
		private ImageView foto;
		private ImageView img;

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
			nome.setText(m.getUsuario().getApelido());
			
			TextView titulo = (TextView) view.findViewById(R.id.textViewTitulo);
			titulo.setText(m.getTitulo());
			
			TextView descricao = (TextView) view.findViewById(R.id.textViewDescricao);
			descricao.setText(m.getDescricao());
			
			TextView area = (TextView) view.findViewById(R.id.textViewAreaInteresse);
			area.setText(m.getAreaInteresse().getNome());
			
			TextView data = (TextView) view.findViewById(R.id.textViewData);
			data.setText(m.getData());
			
	foto = (ImageView) view.findViewById(R.id.fotoUsuarioMensagem);
			
			try{
				Bitmap bitmap = BitmapFactory.decodeByteArray(m.getUsuario().getFoto(), 0, m.getUsuario().getFoto().length);
				foto.setImageBitmap(bitmap);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		
img = (ImageView) view.findViewById(R.id.imgMenssagem);
			
			try{
				Bitmap bitmap = BitmapFactory.decodeByteArray(m.getImagem(), 0, m.getImagem().length);
				img.setImageBitmap(bitmap);
			}
			catch(Exception e){
				
				
				e.printStackTrace();
			}
			if(m.getImagem() == null){
				img.setVisibility(View.GONE);
				
				
				
			}
		
			
			
			return view;
		}
	}