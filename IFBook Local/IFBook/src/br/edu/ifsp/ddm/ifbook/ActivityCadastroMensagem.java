package br.edu.ifsp.ddm.ifbook;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import br.edu.ifsp.ddm.ifbook.dao.AreaInteresseDAO;
import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.AreaInteresse;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.util.AreaInteresseListAdapter;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityCadastroMensagem extends Activity {
	
	
	private Mensagem menssagem;
	private List<Mensagem> menssagens;
	private List<AreaInteresse> areas;
	private AreaInteresse areainteresse;
	private MensagemDAO dao;
	private EditText titulo;
	private EditText descricao;
	private Spinner area;
	private EditText idUsuario;
	private Intent it;
	private Usuario user;
	private AreaInteresseDAO daoArea;
	private AreaInteresseListAdapter adapter;
	private Bitmap imagem;
	private Button arquivo;
	private Button postar;
	private Intent i;
	private ImageView foto;
	private ImageView img;
	private boolean erroGravacao;
	private static int RESULT_LOAD_IMAGE = 1;
	private static final int CAMERA_REQUEST = 1888;
	private Usuario usuario;	
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_menssagem);
    	erroGravacao = false;
        titulo = (EditText) findViewById(R.id.editTituloNovaMenssagem);
        descricao = (EditText) findViewById(R.id.editDescricaoNovaMenssagem);
        area = (Spinner) findViewById(R.id.spArea2);
    	
		arquivo = (Button) findViewById(R.id.botaoImagemMenssagem);
		   postar = (Button) findViewById(R.id.botaoPostarMenssagem);
	
        
   	it = getIntent();
	user = (Usuario) it.getSerializableExtra("Usuario");
	

      preencherAreaInteresse();
		
      foto = (ImageView) findViewById(R.id.exibePerfil2);
		
		try{
			Bitmap bitmap = BitmapFactory.decodeByteArray(user.getFoto(), 0, user.getFoto().length);
			foto.setImageBitmap(bitmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		img = (ImageView) findViewById(R.id.imagemMenssagem);
		arquivo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
				
			}
		});	
		
 
	postar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {	
			menssagem = new Mensagem();
			
			if (titulo.getText().toString().trim().length() == 0) {

				Toast.makeText(getApplicationContext(), "T�tulo inv�lido: Preenchimento obrigat�rio desse campo!",
						Toast.LENGTH_LONG).show();

			} else if (descricao.getText().toString().trim().length() == 0) {

				Toast.makeText(getApplicationContext(),
						"Descri��o inv�lida: Preenchimento obrigat�rio desse campo!", Toast.LENGTH_LONG).show();

			}else if (titulo.getText().toString().trim().length() > 50) {

				Toast.makeText(getApplicationContext(), "T�tulo inv�lido: Limite m�ximo de 50 caracteres!",
						Toast.LENGTH_LONG).show();

			}else if (descricao.getText().toString().trim().length() > 200) {

				Toast.makeText(getApplicationContext(),
						"Descri��o inv�lida: Limite m�ximo de 200 caracteres!", Toast.LENGTH_LONG).show();

			} else{
				if (img.getDrawable() != null) {

					imagem = ((BitmapDrawable) img.getDrawable())
							.getBitmap();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					imagem.compress(Bitmap.CompressFormat.JPEG, 50, bos);

					if (imagem.getRowBytes()*imagem.getHeight() <= 2097152) {
			
						menssagem.setImagem(bos.toByteArray());
					}else{
						
						erroGravacao = true;
						Toast.makeText(getApplicationContext(),
								"Imagem inv�lida: Tamanho máximo de 2mb!", Toast.LENGTH_LONG)
								.show();
					}
					}
				if (erroGravacao == false){
			usuario = new Usuario();
			usuario.setIdUsuario(user.getIdUsuario());
			
		
			menssagem.setTitulo(titulo.getText().toString());
			menssagem.setDescricao(descricao.getText().toString());
			menssagem.setAreaInteresse(areas.get(area.getSelectedItemPosition()));
			menssagem.setUsuario(usuario);
	        dao = new MensagemDAO(getApplicationContext());
	       // dao = new MensagemDAO();
	    	Calendar c = Calendar.getInstance();  
		    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    String data = f.format( c.getTime() );
	        
	        menssagem.setData(data);        
			dao.inserir(menssagem);
	       // dao.salvar(menssagem);

			Intent it = new Intent(getApplicationContext(), ActivityListaMensagens.class);
			it.putExtra("Usuario", user);
			startActivity(it);
			}
			}
			

		}
	});
	}

	
	private void preencherAreaInteresse(){
		//daoArea = new AreaInteresseDAO();
		daoArea = new AreaInteresseDAO(this);
		areas = daoArea.listAll();
		
		try{
			ArrayAdapter<AreaInteresse> adapter =
					new ArrayAdapter<AreaInteresse>(this,android.R.layout.simple_list_item_1,areas);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			area.setAdapter(adapter);
			
			
			
		} catch (Exception ex){
			exibirMensagem("Erro:" + ex.getMessage());
		}
		
		
		

		
		
		
	}

	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastro_menssagem, menu);
		return true;
		
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	
 	 
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
         img.setImageBitmap(imagem);
     }
 
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
	public void meuPerfil(View v){
		  
		Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
		it.putExtra("Usuario", user);
		startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);
		
		
	}


}
