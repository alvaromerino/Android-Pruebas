package com.alvaromerino.android.sqlite.activities;

import com.alvaromerino.android.sqlite.R;
import com.alvaromerino.android.sqlite.data.Usuario;
import com.alvaromerino.android.sqlite.handlers.DatabaseHandler;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormNuevoYActualizarUsuarioActivity extends Activity {

	private TextView textviewTitulo;
	private Button btnAccion;
	private EditText etUsuario, etPassword;
	private DatabaseHandler dbHandler;
	private Usuario usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_nuevo_usuario);
		
		if (savedInstanceState == null) dbHandler = new DatabaseHandler(this);

		textviewTitulo = (TextView) findViewById(R.id.textviewTituloNuevoYActualizar);
		btnAccion = (Button) findViewById(R.id.btnAccionFormulario);
		etUsuario = (EditText) findViewById(R.id.edittextUsuario);
		etPassword = (EditText) findViewById(R.id.edittextPassword);
		etPassword.setTypeface(Typeface.DEFAULT);
		
		usuario = (Usuario) getIntent().getParcelableExtra(MainActivity.KEY_USUARIO);
		// Si el usuario es != null quiere decir que estoy abriendo el formulario en modo
		// Actualizar, para actualizar un usuario.
		// Si es null quiere decir que estoy abriendo el formulario en modo Nuevo Usuario.
		if (usuario != null) {
			
			textviewTitulo.setText(R.string.actualizar_usuario);
			btnAccion.setText(R.string.btnActualizarUsuario);
			etUsuario.setText(usuario.getUser());
			etPassword.setText(usuario.getPassword());
			
		} else {
			textviewTitulo.setText(R.string.nuevo_usuario);
			btnAccion.setText(R.string.btnDarDeAltaUsuario);
		}
		
	}
	
	/*
	 * Función a la que irá el programa al pulsar en el Botón de Alta. 
	 * El evento onClick() del botón está definido mediante XML.
	 */
	public void accionOnClick(View v) {
		
		String username = etUsuario.getText().toString().trim();
		String password = etPassword.getText().toString().trim();
		
		// Comprobar que los campos del formulario no están vacios.
		if (username.isEmpty()) {
			Toast.makeText(this, "Debes de rellenar el campo de usuario...", Toast.LENGTH_LONG).show();
			return;
		}
		
		if (password.isEmpty()) {
			Toast.makeText(this, "Debes de rellenar el campo de password...", Toast.LENGTH_LONG).show();
			return;
		}
		
		// Si el usuario es != null quiere decir que estoy abriendo el formulario en modo
		// Actualizar, para actualizar un usuario.
		// Si es null quiere decir que estoy abriendo el formulario en modo Nuevo Usuario.
		if (usuario != null) {
			
			dbHandler.open();
			usuario.setUser(username);
			usuario.setPassword(password);
			dbHandler.updateUsuario(usuario);
			dbHandler.close();
			Toast.makeText(this, "Usuario actualizado correctamente en la Base de Datos", Toast.LENGTH_LONG).show();
			finish();
			
		} else {
			
			// Buscar que no haya un usuario con el mismo nombre de usuario.
			dbHandler.open();
			Usuario usuarioTemporal = dbHandler.getUsuario(username.toLowerCase());
			dbHandler.close();
			
			if (usuarioTemporal != null) 
			{
				Toast.makeText(this, "Ya existe un usuario con ese username", Toast.LENGTH_LONG).show();
			}
			else {
				dbHandler.open();
				usuario = new Usuario(username.toLowerCase(), password);
				dbHandler.addUsuario(usuario);
				dbHandler.close();
				Toast.makeText(this, "Usuario añadido correctamente a la Base de Datos", Toast.LENGTH_LONG).show();
				finish();
			}
		}
		
			
	}

}
