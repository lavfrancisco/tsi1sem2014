package br.edu.ifsp.ddm.ifbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

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
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ActivityEditarMenssagem extends Activity {

	private Mensagem menssagem;
	private List<Mensagem> classificados;
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
	private Button editar;
	private Intent i;
	private ImageView foto;
	private ImageView img;
	private static int RESULT_LOAD_IMAGE = 1;
	private static final int CAMERA_REQUEST = 1888;
	private Usuario usuario;
	private TextView idText;
	private boolean erroGravacao;
	private boolean imagemEditada;
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_minha_menssagem);
		erroGravacao = false;
		imagemEditada = false;
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

			img = (ImageView) findViewById(R.id.imagemMenssagemEditar);
			imagem = ((BitmapDrawable)img.getDrawable()).getBitmap();	
		
		 titulo = (EditText) findViewById(R.id.editarMenssagemTitulo);
	        descricao = (EditText) findViewById(R.id.editarMenssagemDescricao);
	        area = (Spinner) findViewById(R.id.spMenssagemArea);
	        idText = (TextView) findViewById(R.id.IdMenssagemEditar);
			arquivo = (Button) findViewById(R.id.botaoImagemMenssagemEditar);
			editar = (Button) findViewById(R.id.botaoEditarMenssagem);
		
			
		  
		   
		this.getIntent().getStringExtra("IdMenssagem");
		String id = this.getIntent().getStringExtra("IdMenssagem");
		

		int idmen = Integer.parseInt(id);
		dao = new MensagemDAO();
		menssagem = new Mensagem();
	
			menssagem = dao.getByID(idmen);
		    titulo.setText((menssagem.getTitulo()));
		    idText.setText(id);
		    descricao.setText((menssagem.getDescricao()));
		    
		 
		    try{
				imagem = BitmapFactory.decodeByteArray(menssagem.getImagem(), 0, menssagem.getImagem().length);
				img.setImageBitmap(imagem);
		}
			catch(Exception e){
				e.printStackTrace();
			}
		    
		AreaInteresseDAO daoArea = new AreaInteresseDAO();
		adapter = new AreaInteresseListAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, daoArea.listAll());
		area.setAdapter(adapter);
		area.setSelection(menssagem.getAreaInteresse().getIdAreaInteresse() - 1);
	area.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			 public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				menssagem.setAreaInteresse(adapter.getItem(position));
	     
	       }
		public void onNothingSelected(AdapterView<?> adapter) {  }
	});
	
	
	
	editar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			if (titulo.getText().toString().trim().length() == 0) {

				Toast.makeText(getApplicationContext(), "Título Inválido: Preenchimento obrigatório desse campo!",
						Toast.LENGTH_LONG).show();

			} else if (descricao.getText().toString().trim().length() == 0) {

				Toast.makeText(getApplicationContext(),
						"Descrição inválida: Preenchimento obrigatório desse campo!", Toast.LENGTH_LONG).show();

			}else if (titulo.getText().toString().trim().length() > 50) {

				Toast.makeText(getApplicationContext(), "Título Inválido: Limite máximo de 50 caracteres!",
						Toast.LENGTH_LONG).show();

			}else if (descricao.getText().toString().trim().length() > 200) {

				Toast.makeText(getApplicationContext(),
						"Descrição inválida: Limite máximo de 200 caracteres!", Toast.LENGTH_LONG).show();

			}else {
				if (imagemEditada == true) {

					imagem = ((BitmapDrawable) img.getDrawable())
							.getBitmap();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					imagem.compress(Bitmap.CompressFormat.PNG, 100, bos);

					if (bos.size() <= 319324) {
			
						menssagem.setImagem(bos.toByteArray());
					}else{
						
						erroGravacao = true;
						Toast.makeText(getApplicationContext(),
								"Imagem inválida: Tamanho máximo de 2mb!", Toast.LENGTH_LONG)
								.show();
					}
					}
				if (erroGravacao == false){
			usuario = new Usuario();
			usuario.setIdUsuario(user.getIdUsuario());
			
		
			menssagem.setTitulo(titulo.getText().toString());
			menssagem.setDescricao(descricao.getText().toString());		
			MensagemDAO dao2 = new MensagemDAO();
			dao2.atualizar(menssagem);
			
	        
			
			Intent it = new Intent(getApplicationContext(), ActivityListaMensagens.class);
			it.putExtra("Usuario", user);
			startActivity(it);
			}else{
				
			Toast.makeText(getApplicationContext(), "Imagem muito grande!", Toast.LENGTH_LONG).show();
				
			}

			}
		}
	});
	
	
	arquivo.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(i, RESULT_LOAD_IMAGE);
			
		}
	});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_editar_classificado, menu);
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
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
			imagemEditada = true;

		}

	}

	public void exibeClassificados(View v) {

		Intent it = new Intent(getApplicationContext(),
				ActivityListaClassificados.class);
		it.putExtra("Usuario", user);
		startActivity(it);

	}

	public void exibeMensagens(View v) {

		Intent it = new Intent(getApplicationContext(),
				ActivityListaMensagens.class);
		it.putExtra("Usuario", user);
		startActivity(it);

	}

	public void ExibeHome(View v) {

		Intent it = new Intent(getApplicationContext(), Perfil_listagem.class);
		it.putExtra("Usuario", user);
		startActivity(it);

	}

	public void meuPerfil(View v) {

		Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
		it.putExtra("Usuario", user);
		startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);

	}

}
