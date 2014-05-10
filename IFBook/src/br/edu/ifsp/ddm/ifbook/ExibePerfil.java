package br.edu.ifsp.ddm.ifbook;

import br.edu.ifsp.ddm.ifbook.R;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ExibePerfil extends Activity{
	private TextView nome;
	private TextView apelido;
	private TextView nascimento;
	private TextView estadoCivil;
	private TextView localTrabalho;
	private TextView email;
	private TextView telefone;
	private Button editar;
	private ImageView foto;
	private Bitmap imagem;
	private Intent it;
	private Usuario user;
	private static final int ACTIVITY_EXIBIR_EDITAR = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		
		nome = (TextView) findViewById(R.id.perfilNome);
		nome.setText(user.getNome());
		
		apelido = (TextView) findViewById(R.id.perfilApelido);
		apelido.setText(user.getApelido());

		nascimento = (TextView) findViewById(R.id.perfilNasc);
		nascimento.setText(user.getNascimento());
		
		estadoCivil = (TextView) findViewById(R.id.perfilEstadoC);
		estadoCivil.setText(user.getIdEstadoCivil().getEstadoCivil());
		
		localTrabalho = (TextView) findViewById(R.id.perfilTrabalho);
		localTrabalho.setText(user.getLocalTrabalho());
		
		email = (TextView) findViewById(R.id.perfilEmail);
		email.setText(user.getEmail());
		
		telefone = (TextView) findViewById(R.id.perfilTelefone);
		telefone.setText(user.getTelefone());
		
		foto = (ImageView) findViewById(R.id.imagePerfil);
		
		try{
			imagem = BitmapFactory.decodeByteArray(user.getFoto(), 0, user.getFoto().length);
			foto.setImageBitmap(imagem);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		editar = (Button) findViewById(R.id.buttonEditPerfil);
		editar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				it = new Intent(getApplicationContext(), EditarPerfil.class);
				it.putExtra("Usuario", user);
				startActivityForResult(it, ACTIVITY_EXIBIR_EDITAR);
			}
		});
	}
}
