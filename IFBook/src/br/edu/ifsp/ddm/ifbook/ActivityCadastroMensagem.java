package br.edu.ifsp.ddm.ifbook;

import java.util.List;
import java.util.Date;

import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityCadastroMensagem extends Activity {
	
	
	private Mensagem p;
	private List<Mensagem> mensagem;
	private MensagemDAO dao;
	private EditText edID;
	private EditText edTITULO;
	private EditText edDESCRICAO;
	private EditText edDATA;
	private Spinner spCATEGORIA;
	private EditText edIDUSUARIO;
	private String operacao;
	private Integer[] categoria = { 1 , 2 };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.perfil_login);
        
        setContentView(R.layout.mensagem);
        //Esse ID será autoincrement
        //edID = (EditText) findViewById(R.id.edID);
        edTITULO = (EditText) findViewById(R.id.edTITULO);
        edDESCRICAO = (EditText) findViewById(R.id.edDESCRICAO);
        // nao ta puxando as categorias
        spCATEGORIA = (Spinner) findViewById(R.id.spCATEGORIA);
		
		operacao = new String ("Novo");
		dao = new MensagemDAO(getApplicationContext());
		preencherCategoria();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void preencherCategoria(){
		try{
			ArrayAdapter<String> adapter =
					new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spCATEGORIA.setAdapter(adapter);
		} catch (Exception ex){
			exibirMensagem("Erro:" + ex.getMessage());
		}
		
	}
	
	public void salvar(View v){
		
		if (operacao.equalsIgnoreCase("Novo")) {
			p = new Mensagem();
			Intent it = new Intent(getApplicationContext(), Lista_Classificados.class);
			startActivity(it);
	
		}
		
        Date d = new Date();
        
        p.setTitulo(edTITULO.getText().toString());
        p.setDescricao(edDESCRICAO.getText().toString());
        //inserir imagem comofaz? perguntar luis
        p.setAreaInteresse_idAreaInteresse(categoria[spCATEGORIA.getSelectedItemPosition()]);
        //Colocando um id do usuário aleatório pois será buscado do banco de dados.
        p.setUsuario_idUsuario(1);
        //p.setData(d);
        //limparDados();
		
	}
	
	public void novo(View v) {
		operacao=new String("Novo");
		//adicionar o caminho para visualizar msgs
		//limparDados();
	}
	
	private void limparDados() {
		edID.setText("");
		edTITULO.requestFocus();
		edDESCRICAO.setText("");
		edDATA.setText("");
		spCATEGORIA.setSelection(0);
		edIDUSUARIO.setText("");
		
	}
	
	private void preencherDados(Mensagem mensagem) {
		edID.setText(String.valueOf(mensagem.getIdMensagem()));
		edTITULO.setText(mensagem.getTitulo());
		edDESCRICAO.setText(mensagem.getDescricao());
		//edDATA.setText(String.valueOf(mensagem.getData()));
		spCATEGORIA.setSelection(mensagem.getAreaInteresse_idAreaInteresse());
		//edIDUSUARIO.setText(String.valueOf(Mensagem.getIdUsuario()));
	}
	
	
	
	// Excluir Mensagem 14/04/2014
	private void excluirMensagem(final int idMensagem) {
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Excluir a Mensagem?")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setMessage("Deseja excluir essa Mesagem?")
					.setCancelable(false)
					.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									if (dao.deletar(idMensagem)) {
										//atualizarLista();
										exibirMensagem("Mensagem excluída com sucesso!");
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
	
	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}

//Comando para formatar a data:
/*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
String dateInString = edDATA.getText().toString();

try {

  Date date = formatter.parse(dateInString);
  System.out.println(date);
  System.out.println(formatter.format(date));

} catch (ParseException e) {
  e.printStackTrace();
}*/