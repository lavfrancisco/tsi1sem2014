package br.edu.ifsp.ddm.ifbook;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

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
		lvMensagens.setOnItemLongClickListener(excluirMensagem);
		mensagens = new ArrayList<Mensagem>();
		dao = new MensagemDAO(getApplicationContext());
		atualizarLista();
	}

	private void atualizarLista() {

		dao = new MensagemDAO(this);
		mensagens = dao.listAll();

		System.out.print("Mensagens:" + mensagens.size());
		if (mensagens != null) {

			if (mensagens.size() >=0) {

				ListaMensagens men = new ListaMensagens(
						getApplicationContext(), mensagens);
				lvMensagens.setAdapter(men);
			}

		}

	}

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
									
									exibirMensagem("Mensagem excluída com sucesso!");
									atualizarLista();
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

	private OnItemLongClickListener excluirMensagem = new OnItemLongClickListener() {

		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			excluirMensagem(mensagens.get(pos).getIdMensagem());
			
			return true;
		}

	};

	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
	
	
	private OnItemClickListener selecionarMensagem = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
			Intent it = new Intent(ActivityListaMensagens.this, Login.class);			
			it.putExtra("idMensagem", mensagens.get(pos).getIdMensagem());
			startActivity(it);

		}

	};

}