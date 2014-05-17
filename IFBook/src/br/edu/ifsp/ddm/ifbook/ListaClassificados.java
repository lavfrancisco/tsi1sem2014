package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaClassificados  extends BaseAdapter {
	
	

		private Context context;
		private List<Classificado> lista;
		private ImageView foto;
		private ImageView img;

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
			View view = inflater.inflate(R.layout.classificados2, null);
			
			TextView id = (TextView) view.findViewById(R.id.textIdClassificado);
			id.setText("ID: "+String.valueOf(c.getIdClassificado()));
			
			
			TextView apelido = (TextView) view.findViewById(R.id.textNomeUsuarioClassificado);
			apelido.setText(c.getUsuario_idUsuario().getApelido());
			
			TextView titulo = (TextView) view.findViewById(R.id.textTituloClassificado);
			titulo.setText(c.getTitulo());
			
			TextView descricao = (TextView) view.findViewById(R.id.textDescricaoClassificado);
			descricao.setText(c.getDescricao());
			//descricao.setHeight(10);
			
			TextView area = (TextView) view.findViewById(R.id.textAreaInteresseClassificado);
			area.setText(c.getAreaInteresse_idAreaInteresse().getNome());  
		 
		    
			TextView data = (TextView) view.findViewById(R.id.textDataClassificado);
			data.setText(c.getData());
			
			TextView idUsuario = (TextView) view.findViewById(R.id.textIdUsuarioClassificado);
			idUsuario.setText("ID: "+String.valueOf(c.getUsuario_idUsuario().getIdUsuario()));
			
			foto = (ImageView) view.findViewById(R.id.fotoUsuarioClassificado);
			
			try{
				Bitmap bitmap = BitmapFactory.decodeByteArray(c.getUsuario_idUsuario().getFoto(), 0, c.getUsuario_idUsuario().getFoto().length);
				foto.setImageBitmap(bitmap);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		
img = (ImageView) view.findViewById(R.id.imgClassificado);
			
			try{
				Bitmap bitmap = BitmapFactory.decodeByteArray(c.getImagem(), 0, c.getImagem().length);
				img.setImageBitmap(bitmap);
			}
			catch(Exception e){
				
				
				e.printStackTrace();
			}
	if(c.getImagem() == null){
		img.setVisibility(View.GONE);
		
		
		
	}
			

			
			
			
			return view;
		}
	}