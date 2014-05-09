package br.edu.ifsp.ddm.ifbook;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.modelo.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ActivityListaMensagens extends Activity {

	private List<Mensagem> mensagens;
	private MensagemDAO dao;
	private ListView lvMensagens;
	private Mensagem mensagem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_mensagens);
		
		lvMensagens = (ListView) findViewById(R.id.listaMensagens);
		mensagens = new ArrayList<Mensagem>();
		dao = new MensagemDAO(getApplicationContext());
		atualizarLista();	
	}

private void atualizarLista() {
		
		mensagens = dao.listAll();
	
	    
		System.out.print("Mensagens:"+ mensagens.size());
		if (mensagens != null) {
		
		
			if (mensagens.size() > 0) {
				
			
				ListaMensagens men = new ListaMensagens(
						getApplicationContext(), mensagens);
				lvMensagens.setAdapter(men);
			}

		}

	}
	
}