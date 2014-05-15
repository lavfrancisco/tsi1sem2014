package br.edu.ifsp.ddm.ifbook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditarMeuClassificado extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_meu_classificado);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_editar_meu_classificado, menu);
		return true;
	}

}
