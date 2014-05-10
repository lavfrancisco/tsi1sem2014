package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Usuario;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaAniversariantes extends BaseAdapter {

	private Context context;
	private List<Usuario> lista;
	public ImageView imagem;
	private ImageView foto;

	public ListaAniversariantes(Context context, List<Usuario> lista) {
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
		
		Usuario p = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.aniversariantes, null);
		
		TextView id = (TextView) view.findViewById(R.id.txtidUsuario);
		id.setText("ID: "+String.valueOf(p.getIdUsuario()));
		
		
		TextView nome = (TextView) view.findViewById(R.id.txtNome);
		nome.setText(p.getNome());
		
		TextView nascimento = (TextView) view.findViewById(R.id.txtNascimento);
		nascimento.setText(p.getNascimento());
		
		imagem = (ImageView) view.findViewById(R.id.estrela);
		
		foto = (ImageView) view.findViewById(R.id.fotoAnivesasriante);
		
		try{
			Bitmap bitmap = BitmapFactory.decodeByteArray(p.getFoto(), 0, p.getFoto().length);
			foto.setImageBitmap(bitmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		if(p.getNascimento().equalsIgnoreCase("Hoje")){
		imagem.setImageResource(android.R.drawable.btn_star_big_on);
		}
			
			
		
		
		return view;
	}
}