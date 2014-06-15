package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

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
		dao = new ClassificadoDAO();
		lvClassificados.setOnItemClickListener(selecionarUsuarioClassificado);
		it = getIntent();
		user = (Usuario) it.getSerializableExtra("Usuario");
	

		if(user.getNivel() == 2){
			lvClassificados.setOnItemLongClickListener(excluirClassificado);
		}
		atualizarLista();	


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
		   
				
	              String iduser = String.valueOf(classificado.getUsuario().getIdUsuario());
		    		
	              intent.putExtra("idUsuario", iduser);
	              intent.putExtra("Usuario", user);
	              startActivity(intent);
	          	
		}

	};
		
	
	private OnItemLongClickListener excluirClassificado = new OnItemLongClickListener() {

		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			excluirClassificado(classificados.get(pos).getIdClassificado());
			
			return true;
		}

	};
	private void excluirClassificado(final int idClassificado) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Excluir o Classificado?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setMessage("Deseja excluir o Classificado?")
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (dao.deletar(idClassificado)) {
									
									exibirClassificado("Classificado exclu�do com sucesso!");
									atualizarLista();
								} else {
									exibirClassificado("N�o foi poss�vel excluir o Classificado!");
								}

							}
						})
				.setNegativeButton("N�o",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		builder.create();
		builder.show();

	}





	
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

private void exibirClassificado(String msg) {
	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
}
	
}