package com.alvaromerino.android.sqlite.activities;

import com.alvaromerino.android.sqlite.R;
import com.alvaromerino.android.sqlite.data.Usuario;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UsuarioDetallesActivity extends Activity {

	private TextView textviewId, textviewUsuario, textviewPassword;
	private Usuario usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario_detalles);
		
		textviewId = (TextView) findViewById(R.id.textViewDetallesId);
		textviewUsuario = (TextView) findViewById(R.id.textViewDetallesUsuario);
		textviewPassword = (TextView) findViewById(R.id.textViewDetallesPassword);
		
		usuario = (Usuario) getIntent().getParcelableExtra(MainActivity.KEY_USUARIO);

		textviewId.setText(String.format("%s %s", "Id:", usuario.getId()));
		textviewUsuario.setText(String.format("%s %s", "Usuario:", usuario.getUser()));
		textviewPassword.setText(String.format("%s %s", "Password:", usuario.getPassword()));
	}
	
}
