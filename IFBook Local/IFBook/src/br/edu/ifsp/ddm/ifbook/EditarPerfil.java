package br.edu.ifsp.ddm.ifbook;

import java.io.ByteArrayOutputStream;

import br.edu.ifsp.ddm.ifbook.dao.EstadoCivilDAO;
import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.util.EstadoCivilListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class EditarPerfil extends Activity{
	private static final int CAMERA_REQUEST = 1888;
	private static int RESULT_LOAD_IMAGE = 1;
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;
	
	
	private EditText apelido;
	private EditText trabalho;
	private EditText telefone;
	private EditText email;
	private Spinner estado_civil;
	private ImageView foto;
	private Button camera;
	private Button arquivo;
	private Button salvar;
	private Usuario user;
	private Bitmap imagem;
	private ImageView foto2;
	private Intent i;
	private EstadoCivilListAdapter adapter;
	//private boolean alteraFoto = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_editar);
		i = getIntent();
		user = (Usuario) i.getSerializableExtra("Usuario");
		
		
foto2 = (ImageView) findViewById(R.id.exibePerfil2);
		
		try{
			Bitmap bitmap = BitmapFactory.decodeByteArray(user.getFoto(), 0, user.getFoto().length);
			foto2.setImageBitmap(bitmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		foto = (ImageView) findViewById(R.id.imgEdit);
		try{
			imagem = BitmapFactory.decodeByteArray(user.getFoto(), 0, user.getFoto().length);
			foto.setImageBitmap(imagem);
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		apelido = (EditText) findViewById(R.id.editApelido);
		apelido.setText(user.getApelido());
		
		trabalho = (EditText) findViewById(R.id.editTrabalho);
		trabalho.setText(user.getLocalTrabalho());
		
		telefone = (EditText) findViewById(R.id.editTelefone);
		telefone.setText(user.getTelefone());
		
		email = (EditText) findViewById(R.id.editEmail);
		email.setText(user.getEmail());
		
		EstadoCivilDAO dao = new EstadoCivilDAO(getApplicationContext());
		//EstadoCivilDAO dao = new EstadoCivilDAO();
		adapter = new EstadoCivilListAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dao.listAll());
		estado_civil = (Spinner) findViewById(R.id.spinnerEstadoC);
		estado_civil.setAdapter(adapter);
		estado_civil.setSelection(user.getIdEstadoCivil().getIdEstadoCivil() - 1);
		estado_civil.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			 public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
	                user.setIdEstadoCivil(adapter.getItem(position));
	     
	        }
			public void onNothingSelected(AdapterView<?> adapter) {  }
		});
		
		foto = (ImageView) findViewById(R.id.imgEdit);
		imagem = ((BitmapDrawable)foto.getDrawable()).getBitmap();
		
		camera = (Button) findViewById(R.id.buttonImgCamera);
		camera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
              //  alteraFoto=true;
				
			}
		});
		
		arquivo = (Button) findViewById(R.id.buttonImgArquivo);
		arquivo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
               // alteraFoto=true;
				
			}
		});
		
		salvar = (Button) findViewById(R.id.buttonSalvar);
		salvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Converte imagem para array de bytes para salvar no BD
				
		
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				imagem.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				//if(alteraFoto)
				//{
					user.setFoto(bos.toByteArray());
				//}
				
					
				
				
				user.setApelido(apelido.getText().toString());
				user.setLocalTrabalho(trabalho.getText().toString());
				user.setEmail(email.getText().toString());
				
				UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
				//UsuarioDAO dao = new UsuarioDAO();
				dao.autualizar(user);
				//dao.atualizar(user);
				
		        
				
				Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
				it.putExtra("Usuario", user);
				startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);

			}
		});
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            imagem = (Bitmap) data.getExtras().get("data"); 
            foto.setImageBitmap(imagem);
        } 
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imagem = BitmapFactory.decodeFile(picturePath); 
            foto.setImageBitmap(imagem);
        }
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
	public void exibeMensagens(View v){
		
		
		Intent it = new Intent(getApplicationContext(), ActivityListaMensagens.class);
		it.putExtra("Usuario", user);
		startActivity(it);
		
	}
	
	public void ExibeHome(View v){
		
		Intent it = new Intent(getApplicationContext(), Perfil_listagem.class);
		it.putExtra("Usuario", user);
		startActivity(it);
	
		
	}
}

