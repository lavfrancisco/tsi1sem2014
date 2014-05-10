package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Perfil_listagem extends Activity {

	private List<Usuario> usuarios;
	private UsuarioDAO dao;
	private ListView lvUsuarios;
	private TextView boasvindas;
	private Usuario usuario;
	private Intent it;
	private Usuario user;
	
	
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_listagem);
		
		boasvindas = (TextView) findViewById(R.id.TextSaudacao);
		lvUsuarios = (ListView) findViewById(R.id.ListaAniversariantes);
		lvUsuarios.setOnItemClickListener(selecionarUsuario);
		usuarios = new ArrayList<Usuario>();
		dao = new UsuarioDAO(getApplicationContext());
		atualizarLista();
		this.getIntent().getStringExtra("idUsuario");
		Usuario user = new Usuario();
		it = getIntent();
		user = (Usuario) it.getSerializableExtra("Usuario");	
		
		boasvindas.setText(user.getApelido()+"!");
	
	}

	private OnItemClickListener selecionarUsuario = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
			
			usuario = usuarios.get(pos);
			
		       final Intent intent = new Intent(Perfil_listagem.this, Perfil_Usuario.class);
	           
	              String iduser = String.valueOf(usuario.getIdUsuario());
	              
	              intent.putExtra("idUsuario", iduser);
	              startActivity(intent);
			

		}

	};



	private void atualizarLista() {
		
		usuarios = dao.Aniversariantes();
		
		
		Calendar c = Calendar.getInstance();  
	    SimpleDateFormat f = new SimpleDateFormat("MM/dd");
	    String hoje = f.format( c.getTime() );
	    
	 
	    Date minhaData = new Date();  
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(minhaData);   
	    calendar.add(Calendar.DAY_OF_MONTH, 1);     
	    SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/dd"); 	    
	    String amanha = dataFormatada.format(calendar.getTime());
	 

		
		for (Usuario obj : usuarios) {
				
			
			 if(obj.getNascimento().equalsIgnoreCase(hoje)){
			    	obj.setNascimento("Hoje");
				    }
			 else if(obj.getNascimento().equalsIgnoreCase(amanha)){
				 
				obj.setNascimento("Amanha");			 
			 }
			 else{
		    
			 }
		    
	
		    
		  
		}
	
	    
		
		if (usuarios != null) {
		
		
			if (usuarios.size() > 0) {
			
				ListaAniversariantes pla = new ListaAniversariantes(
						getApplicationContext(), usuarios);
				lvUsuarios.setAdapter(pla);
			}

		}

	}
	
}
