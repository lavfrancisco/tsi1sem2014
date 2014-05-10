package br.edu.ifsp.ddm.ifbook;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.dao.ClassificadoDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
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

public class ActivityListaClassificados extends Activity {

	private List<Classificado> classificados;
	private ClassificadoDAO dao;
	private ListView lvClassificados;
	private Classificado classificado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_classificados);

		lvClassificados = (ListView) findViewById(R.id.listaClassificados);
		lvClassificados.setOnItemLongClickListener(excluirClassificado);
		classificados = new ArrayList<Classificado>();
		dao = new ClassificadoDAO(getApplicationContext());
		atualizarLista();
	}

	private void atualizarLista() {

		dao = new ClassificadoDAO(this);
		classificados = dao.listAll();

		System.out.print("Classificados:" + classificados.size());
		if (classificados != null) {

			if (classificados.size() >=0) {

				ListaClassificados men = new ListaClassificados(
						getApplicationContext(), classificados);
				lvClassificados.setAdapter(men);
			}

		}

	}

	private void excluirClassificado(final int idClassificado) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Excluir o Classificado?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setMessage("Deseja excluir o Classificado?")
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (dao.deletar(idClassificado)) {
									
									exibirClassificado("Classificado excluído com sucesso!");
									atualizarLista();
								} else {
									exibirClassificado("Não foi possível excluir o Classificado!");
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

	private OnItemLongClickListener excluirClassificado = new OnItemLongClickListener() {

		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			excluirClassificado(classificados.get(pos).getIdClassificado());
			
			return true;
		}

	};

	private void exibirClassificado(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
	
	
	private OnItemClickListener selecionarClassificado = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
			
			Intent it = new Intent(ActivityListaClassificados.this, Login.class);	
			it.putExtra("idClassificado", classificados.get(pos).getIdClassificado());
			startActivity(it);

		}

	};

}