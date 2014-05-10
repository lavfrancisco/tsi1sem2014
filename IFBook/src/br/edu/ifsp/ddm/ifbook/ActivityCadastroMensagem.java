package br.edu.ifsp.ddm.ifbook;

import java.util.List;
import java.util.Date;

import br.edu.ifsp.ddm.ifbook.dao.AreaInteresseDAO;
import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.AreaInteresse;
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
	
	
	private Mensagem mensagem;
	private List<Mensagem> mensagens;
	private List<AreaInteresse> areas;
	private MensagemDAO dao;
	private AreaInteresseDAO daoAreaInteresse;
	private EditText edID;
	private EditText edTITULO;
	private EditText edDESCRICAO;
	private EditText edDATA;
	private Spinner spCATEGORIA;
	private EditText edIDUSUARIO;
	private String operacao;	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mensagem);
        edTITULO = (EditText) findViewById(R.id.edTITULO);
        edDESCRICAO = (EditText) findViewById(R.id.edDESCRICAO);
        spCATEGORIA = (Spinner) findViewById(R.id.spCATEGORIA);
		operacao = new String ("Novo");
		dao = new MensagemDAO(this);		
		preencherCategoria();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void preencherCategoria(){
		daoAreaInteresse = new AreaInteresseDAO(this);
		areas = daoAreaInteresse.listAll();
		
		try{
			ArrayAdapter<AreaInteresse> adapter =
					new ArrayAdapter<AreaInteresse>(this,android.R.layout.simple_list_item_1,areas);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spCATEGORIA.setAdapter(adapter);
		} catch (Exception ex){
			exibirMensagem("Erro:" + ex.getMessage());
		}
		
	}
	
	public void salvar(View v){
		
		if (operacao.equalsIgnoreCase("Novo")) {
			mensagem = new Mensagem();
			
	
		}
		
        Date d = new Date();
        
        mensagem.setTitulo(edTITULO.getText().toString());
        mensagem.setDescricao(edDESCRICAO.getText().toString());
        //inserir imagem comofaz? perguntar luis
        mensagem.setAreaInteresse(areas.get(spCATEGORIA.getSelectedItemPosition()));
        //Colocando um id do usuário aleatório pois será buscado do banco de dados.
        //mensagens.setUsuario_idUsuario(1);
        mensagem.setData(d);
        
        
        if(dao.inserir(mensagem)){
        	Intent it = new Intent(getApplicationContext(), ActivityListaMensagens.class);
        	startActivity(it);
        }
        else
        {
        	exibirMensagem("Não foi possível adicionar a mensagem!");
        }
        
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
		//edDATA.setText(String.valueOf(mensagens.getData()));
		//spCATEGORIA.setSelection(mensagens.getAreaInteresse_idAreaInteresse());
		//edIDUSUARIO.setText(String.valueOf(Mensagem.getIdUsuario()));
	}
	
	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}

