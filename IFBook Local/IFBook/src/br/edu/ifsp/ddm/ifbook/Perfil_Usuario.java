package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.modelo.EstadoCivil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Perfil_Usuario extends Activity {

	private TextView nome;
	private TextView apelido;
	private TextView localTrabalho;
	private TextView Cidade;
	private UsuarioDAO usuariodao;
	private TextView EstadoCivil;
	private TextView aniversario;
	private TextView email;
	private ImageView foto;
	private TextView tel;
	private Intent it;
	private Usuario user;
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_usuario);
		nome = (TextView) findViewById(R.id.textNomeUsuario);
		apelido = (TextView) findViewById(R.id.textUsuarioApelido);
		localTrabalho = (TextView) findViewById(R.id.textLocaltrabalho);
		Cidade = (TextView) findViewById(R.id.textCidade);
		EstadoCivil = (TextView) findViewById(R.id.txtEstadoCivil);		
		aniversario = (TextView) findViewById(R.id.txtAniversario);
		email = (TextView) findViewById(R.id.txtEmail);
		tel = (TextView) findViewById(R.id.textTel);
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
		
		
	
	
		this.getIntent().getStringExtra("idUsuario");
		String idUsuario = this.getIntent().getStringExtra("idUsuario");
		usuariodao = new UsuarioDAO(getApplicationContext());
		//usuariodao = new UsuarioDAO();
		int idusuario = Integer.parseInt(idUsuario);
		Usuario usuario = new Usuario();

	    usuario = usuariodao.getById(idusuario);
		//usuario = usuariodao.getByID(idusuario);
	      
		//System.out.println("ESTADO CIVIL: "+usuario.getIdEstadoCivil().getEstadoCivil());
	    nome.setText((usuario.getNome()));
	    apelido.setText(usuario.getApelido());
	    localTrabalho.setText(usuario.getLocalTrabalho());		   
	    Cidade.setText(usuario.getCidade());
	    EstadoCivil.setText(usuario.getIdEstadoCivil().getEstadoCivil());
	    email.setText(usuario.getEmail());
	    tel.setText(usuario.getTelefone());
	    
foto = (ImageView) findViewById(R.id.fotoPerfilUsuario);
		
		try{
			Bitmap bitmap = BitmapFactory.decodeByteArray(usuario.getFoto(), 0, usuario.getFoto().length);
			foto.setImageBitmap(bitmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		Calendar c = Calendar.getInstance();  
	    SimpleDateFormat f = new SimpleDateFormat("yyyy");
	    String ano = f.format( c.getTime() );
	    
	    Calendar c2 = Calendar.getInstance();  
	    SimpleDateFormat f2 = new SimpleDateFormat("MM");
	    String mes = f2.format( c2.getTime() );
	    
	    Calendar c3 = Calendar.getInstance();  
	    SimpleDateFormat f3 = new SimpleDateFormat("dd");
	    String dia = f3.format( c3.getTime() );
	  
	    
	    String anoNascimento = usuario.getNascimento().substring(0,4);
	    String mesNascimento = usuario.getNascimento().substring(6,7);
	    String diaNascimento = usuario.getNascimento().substring(8,10);

	   
	    
	   int diaAtual = Integer.parseInt(dia);
	   int mesAtual = Integer.parseInt(mes);
	   int anoAtual = Integer.parseInt(ano);
	   int diaUsuario = Integer.parseInt(diaNascimento);
	   int mesUsuario = Integer.parseInt(mesNascimento);
	   int anoUsuario = Integer.parseInt(anoNascimento);
	    
	   int idade = anoAtual - anoUsuario;
	 
	   if((mesAtual <= mesUsuario) && (diaAtual < diaUsuario)){
		  
	   		idade = idade - 1;
	  	
	   }
	     
	    aniversario.setText(idade + " anos");
	
	}
	
	public void exibeMensagens(View v){
		
		
		Intent it = new Intent(getApplicationContext(), ActivityListaMensagens.class);
		it.putExtra("Usuario", user);
		startActivity(it);
		
	}
	
	public void meuPerfil(View v){
		  
		Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
		it.putExtra("Usuario", user);
		startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);
		
		
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
	



}

