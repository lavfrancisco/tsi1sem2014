package br.edu.ifsp.ddm.ifbook;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Lista_Classificados extends Activity {

	private List<Classificado> classificados;
	private ClassificadoDAO dao;
	private ListView lvClassificados;
	private Classificado classificado;
	private Usuario user;
	private Intent it;
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;
	private ImageView foto;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_classificados);
		lvClassificados = (ListView) findViewById(R.id.listaClassificados);
		classificados = new ArrayList<Classificado>();
		dao = new ClassificadoDAO(getApplicationContext());
		lvClassificados.setOnItemClickListener(selecionarUsuarioClassificado);
		atualizarLista();	
		it = getIntent();
		user = (Usuario) it.getSerializableExtra("Usuario");

foto = (ImageView) findViewById(R.id.exibePerfil2);
		
		try{
			Bitmap bitmap = BitmapFactory.decodeByteArray(user.getFoto(), 0, user.getFoto().length);
			foto.setImageBitmap(bitmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	private OnItemClickListener selecionarUsuarioClassificado = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
			
			classificado = classificados.get(pos);
			
		       final Intent intent = new Intent(Lista_Classificados.this, Perfil_Usuario.class);
		   
				
	              String iduser = String.valueOf(classificado.getUsuario_idUsuario().getIdUsuario());
		    		
	              intent.putExtra("idUsuario", iduser);
	              intent.putExtra("Usuario", user);
	              startActivity(intent);
	          	
		}

	};
		
	
	
	

	
	public void meuPerfil(View v){
		  
		Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
		it.putExtra("Usuario", user);
		startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);
		
		
	}
	
	public void exibeClassificados(View v){
		
		
		Intent it = new Intent(getApplicationContext(), Lista_Classificados.class);
		it.putExtra("Usuario", user);
		startActivity(it);
	   
		
		
		
	}
	
	public void ExibeHome(View v){
		
		Intent it = new Intent(getApplicationContext(), Perfil_listagem.class);
		it.putExtra("Usuario", user);
		startActivity(it);
	
		
	}
	


private void atualizarLista() {
		
		classificados = dao.listAll();
	
		if (classificados != null) {
		
		
			if (classificados.size() > 0) {
				
			
				ListaClassificados cla = new ListaClassificados(
						getApplicationContext(), classificados);
				lvClassificados.setAdapter(cla);
			}

		}

	}


	
}