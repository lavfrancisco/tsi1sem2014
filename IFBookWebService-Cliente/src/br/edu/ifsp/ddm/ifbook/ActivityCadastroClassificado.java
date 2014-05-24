package br.edu.ifsp.ddm.ifbook;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.dao.AreaInteresseDAO;
import br.edu.ifsp.ddm.ifbook.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.AreaInteresse;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ActivityCadastroClassificado extends Activity {

	private Classificado classificado;
	private List<Classificado> classificados;
	private List<AreaInteresse> areas;
	private AreaInteresse areainteresse;
	private ClassificadoDAO dao;
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
	private Boolean erroGravacao;
	private static int RESULT_LOAD_IMAGE = 1;
	private static final int CAMERA_REQUEST = 1888;
	private Usuario usuario;
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cadastro_classificado);
		erroGravacao = false;

		titulo = (EditText) findViewById(R.id.editTituloNovoClassificado);
		descricao = (EditText) findViewById(R.id.editDescricaoNovoClassificado);
		area = (Spinner) findViewById(R.id.spArea);

		arquivo = (Button) findViewById(R.id.botaoImagemClassificado);
		postar = (Button) findViewById(R.id.botaoPostarClassificado);

		it = getIntent();
		user = (Usuario) it.getSerializableExtra("Usuario");

		preencherAreaInteresse();

		foto = (ImageView) findViewById(R.id.exibePerfil2);

		try {
			Bitmap bitmap = BitmapFactory.decodeByteArray(user.getFoto(), 0,user.getFoto().length);
			foto.setImageBitmap(bitmap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		img = (ImageView) findViewById(R.id.imagemClassificado);
		// imagem = ((BitmapDrawable)img.getDrawable()).getBitmap();
		arquivo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE);

			}
		});

		postar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				classificado = new Classificado();
				if (titulo.getText().toString().trim().length() == 0) {

					Toast.makeText(getApplicationContext(), "Título inválido!",
							Toast.LENGTH_LONG).show();

				} else if (descricao.getText().toString().trim().length() == 0) {

					Toast.makeText(getApplicationContext(),
							"Descrição inválida!", Toast.LENGTH_LONG).show();

				}else if (descricao.getText().toString().trim().length() > 200) {

					Toast.makeText(getApplicationContext(),
							"Descrição inválida!", Toast.LENGTH_LONG).show();

				} else if (titulo.getText().toString().trim().length() > 50) {

				
					Toast.makeText(getApplicationContext(), "Título inválido!",
							Toast.LENGTH_LONG).show();

				}
				else {

					if (img.getDrawable() != null) {

						imagem = ((BitmapDrawable) img.getDrawable())
								.getBitmap();
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						imagem.compress(Bitmap.CompressFormat.PNG, 100, bos);

						if (bos.size() <= 319324) {
							
							classificado.setImagem(bos.toByteArray());

						} else {
							erroGravacao = true;
							Toast.makeText(getApplicationContext(),
									"Imagem muito grande!", Toast.LENGTH_LONG)
									.show();

						}
					}

					if (erroGravacao==false) {
						
						usuario = new Usuario();
						usuario.setIdUsuario(user.getIdUsuario());
						
						
						classificado.setTitulo(titulo.getText().toString());
						classificado.setDescricao(descricao.getText().toString());
						classificado.setAreaInteresse_idAreaInteresse(areas.get(area.getSelectedItemPosition()));
						classificado.setUsuario_idUsuario(usuario);
						
						dao = new ClassificadoDAO(getApplicationContext());

				    	Calendar c = Calendar.getInstance();
						SimpleDateFormat f = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						String data = f.format(c.getTime());

						classificado.setData(data);

						dao.inserir(classificado);

						Intent it = new Intent(getApplicationContext(),
								ActivityListaClassificados.class);
						it.putExtra("Usuario", user);
						startActivity(it);
					}

				}

			}
		});
	}

	private void preencherAreaInteresse() {
		daoArea = new AreaInteresseDAO(this);
		areas = daoArea.listAll();

		try {
			ArrayAdapter<AreaInteresse> adapter = new ArrayAdapter<AreaInteresse>(
					this, android.R.layout.simple_list_item_1, areas);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			area.setAdapter(adapter);

		} catch (Exception ex) {
			exibirMensagem("Erro:" + ex.getMessage());
		}

	}

	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastro_classificado, menu);
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
