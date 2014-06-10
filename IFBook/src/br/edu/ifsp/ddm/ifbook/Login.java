package br.edu.ifsp.ddm.ifbook;

import java.security.MessageDigest;

import br.edu.ifsp.ddm.ifbook.R;


import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.rest.UsuarioREST;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;
	private Context c;
	private UsuarioDAO dao;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_login);
		c = getApplicationContext();

		Button entrar = (Button) findViewById(R.id.buttonEntrar);
		entrar.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				EditText login = (EditText) findViewById(R.id.editLogin);
				EditText eSenha = (EditText) findViewById(R.id.editSenha);
			
				String prontuario = login.getText().toString();
				String senha = eSenha.getText().toString();

			//	dao = new UsuarioDAO(c);
				dao = new UsuarioDAO();
		
				if(prontuario.length() == 0 || senha.length() == 0){
					exibirMensagem("Preencha os campos para logar!");
				}
				else{
					try{
						Usuario user = dao.getByProntuario(prontuario);
						// 'Criptografando' a senha!
						MessageDigest md = MessageDigest.getInstance("SHA-256");
						md.update(senha.getBytes());
						byte byteData[] = md.digest();
						StringBuffer sb = new StringBuffer();
				        for (int i = 0; i < byteData.length; i++) {
				         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				        }
						if(user.getSenha().equals(sb.toString()) && user.getProntuario().equals(prontuario)){
							Intent it = new Intent(getApplicationContext(), Perfil_listagem.class);
							it.putExtra("Usuario", user);
							startActivity(it);
						}
						else{
							exibirMensagem("Prontuario ou Senha inválidos!");
						}
					}
					catch(Exception e){
						exibirMensagem("Falha ao logar! :(");
						e.printStackTrace();
					}

				}
				
			}
		});
		
		TextView recuperarSenha  = (TextView) findViewById(R.id.linkSenha);
		recuperarSenha.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				exibirMensagem("Funcionalida não implementada\n por excesso de motivos! ;)");
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}
