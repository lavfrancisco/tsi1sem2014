package br.edu.ifsp.ddm.ifbook;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListaMinhasMenssagens extends Activity {

	private List<Mensagem> mensagens;
	private MensagemDAO dao;
	private ListView lvMensagens;
	private Mensagem mensagem;
	private Usuario user;
	private Intent it;
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;
	private ImageView foto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_minhas_menssagens);
		lvMensagens = (ListView) findViewById(R.id.listaMensagens);
		lvMensagens.setOnItemLongClickListener(excluirMensagem);
		lvMensagens.setOnItemClickListener(selecionarMensagem);
		lvMensagens.setOnItemClickListener(selecionarUsuarioMenssagem);
		mensagens = new ArrayList<Mensagem>();
		dao = new MensagemDAO(getApplicationContext());
		it = getIntent();
		user = (Usuario) it.getSerializableExtra("Usuario");
		atualizarLista();	


		foto = (ImageView) findViewById(R.id.exibePerfil2);
				
				try{
					Bitmap bitmap = BitmapFactory.decodeByteArray(user.getFoto(), 0, user.getFoto().length);
					foto.setImageBitmap(bitmap);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
		
		atualizarLista();
	}
	
	public void NovaMenssagem(View v){
		
		Intent it = new Intent(getApplicationContext(), ActivityCadastroMensagem.class);
		it.putExtra("Usuario", user);
		startActivity(it);
		
	}
	

	
	private OnItemClickListener selecionarUsuarioMenssagem = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
			
			mensagem = mensagens.get(pos);
			
		       final Intent intent = new Intent(ListaMinhasMenssagens.this, ActivityEditarMenssagem.class);
		       
	    		

	    	      String idmenssagem = String.valueOf(mensagem.getIdMensagem());
		    		
	    	      intent.putExtra("IdMenssagem", idmenssagem);
	              intent.putExtra("Usuario", user);
	              startActivity(intent);
	          	
		}

	};
	
	public void editarMenssagem(View v){
		
		
		Intent it = new Intent(getApplicationContext(), ListaMinhasMenssagens.class);
		it.putExtra("Usuario", user);
		startActivity(it);
		
	}
	
	
	private void atualizarLista() {

		dao = new MensagemDAO(this);
		int id = user.getIdUsuario();
		mensagens = dao.listAll(id);

	
		if (mensagens != null) {

			if (mensagens.size() >=0) {

				ListaMensagens men = new ListaMensagens(
						getApplicationContext(), mensagens);
				lvMensagens.setAdapter(men);
			}

		}

	}
	
	
	public void meuPerfil(View v){
		  
		Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
		it.putExtra("Usuario", user);
		startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);
		
		
	}
	public void exibeMensagens(View v){
		
		
		Intent it = new Intent(getApplicationContext(), ActivityListaMensagens.class);
		it.putExtra("Usuario", user);
		startActivity(it);
		
	}
	
	
	public void exibeClassificados(View v){
		
		
		Intent it = new Intent(getApplicationContext(), ActivityListaClassificados.class);
		it.putExtra("Usuario", user);
		startActivity(it);
	   
		
		
		
	}
	
	public void ExibeHome(View v){
		
		Intent it = new Intent(getApplicationContext(), Perfil_listagem.class);
		it.putExtra("Usuario", user);
		startActivity(it);
	
		
	}
	

	private void excluirMensagem(final int idMensagem) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Excluir a Mensagem?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setMessage("Deseja excluir essa Mesagem?")
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							System.out.println("ID MENSSAGEM: "+idMensagem);
							dao = new MensagemDAO(getApplicationContext());
							
								if (dao.deletar(idMensagem)) {
									
									exibirMensagem("Mensagem excluída com sucesso!");
									atualizarLista();
								} else {
									exibirMensagem("Não foi possível excluir a Mensagem!");
								}

							}
						})
				.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		builder.create();
		builder.show();
		

	}

	private OnItemLongClickListener excluirMensagem = new OnItemLongClickListener() {

		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			excluirMensagem(mensagens.get(pos).getIdMensagem());
			
			return true;
		}

	};

	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
	
	
	private OnItemClickListener selecionarMensagem = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
			Intent it = new Intent(ListaMinhasMenssagens.this, ActivityCadastroMensagem.class);			
			it.putExtra("idMensagem", mensagens.get(pos).getIdMensagem());
			startActivity(it);

		}

	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_lista_minhas_menssagens, menu);
		return true;
	}

}
