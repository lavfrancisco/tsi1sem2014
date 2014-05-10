package br.edu.ifsp.ddm.ifbook;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

public class Lista_Classificados extends Activity {

	private List<Classificado> classificados;
	private ClassificadoDAO dao;
	private ListView lvClassificados;
	private Classificado classificado;
	private Usuario user;
	private Intent it;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_classificados);
		
		it = getIntent();
		user = (Usuario) it.getSerializableExtra("Usuario");	
	
		
		lvClassificados = (ListView) findViewById(R.id.listaClassificados);
		classificados = new ArrayList<Classificado>();
		dao = new ClassificadoDAO(getApplicationContext());
		atualizarLista();	
	}

private void atualizarLista() {
		
		classificados = dao.listAll();
	
	    
		System.out.print("CLASSIFICADOS:"+ classificados.size());
		if (classificados != null) {
		
		
			if (classificados.size() > 0) {
				
			
				ListaClassificados cla = new ListaClassificados(
						getApplicationContext(), classificados);
				lvClassificados.setAdapter(cla);
			}

		}

	}
	
}