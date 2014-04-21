package com.alvaromerino.android.sqlite.activities;

import java.util.List;

import com.alvaromerino.android.sqlite.R;
import com.alvaromerino.android.sqlite.adapters.UsuarioAdapter;
import com.alvaromerino.android.sqlite.data.Usuario;
import com.alvaromerino.android.sqlite.handlers.DatabaseHandler;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ListActivity 
{
	public static String KEY_USUARIO = "usuario";
	private List<Usuario> usuarios;
	private UsuarioAdapter miAdapter;
	private DatabaseHandler dbHandler;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbHandler = new DatabaseHandler(this);
        dbHandler.open();
        usuarios = dbHandler.getAllUsuarios();
        dbHandler.close();
        miAdapter = new UsuarioAdapter(this, usuarios);
        setListAdapter(miAdapter);
    }

	@Override
	protected void onResume()
	{
		super.onResume();
		dbHandler.open();
        usuarios = dbHandler.getAllUsuarios();
        dbHandler.close();
        setListAdapter(new UsuarioAdapter(this, usuarios));
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

	/* onClick del boton de Nuevo Usuario del ActionBar
     * la propiedad onClick del boton está definido en el layout mediante XML
     */
    public void lanzarFormularioNuevoUsuario(MenuItem item) 
    {
    	Intent intent = new Intent(this, FormNuevoYActualizarUsuarioActivity.class);
    	startActivity(intent);
    }
    
    /* onClick del boton de Ver Detalle de cada Item en la lista.
     * la propiedad onClick del boton está definido en el layout mediante XML
     */
    public void verDetalle(View view) 
    {
    	Intent intent = new Intent(this, UsuarioDetallesActivity.class);
    	int posicion = getListView().getPositionForView(view);
    	Usuario usuario = usuarios.get(posicion);
    	intent.putExtra(KEY_USUARIO, usuario);
    	startActivity(intent);
    }

    /* onClick del boton de Actualizar de cada Item en la lista.
     * la propiedad onClick del boton está definido en el layout mediante XML
     */
    public void actualizar(View view)
    {
    	Intent intent = new Intent(this, FormNuevoYActualizarUsuarioActivity.class);
    	int posicion = getListView().getPositionForView(view);
    	Usuario usuario = usuarios.get(posicion);
    	intent.putExtra(KEY_USUARIO, usuario);
    	startActivity(intent);
    }
    
    /* onClick del boton de Eliminar de cada Item en la lista.
     * la propiedad onClick del boton está definido en el layout mediante XML
     */
    public void eliminar(View view) 
    {
    	int posicion = getListView().getPositionForView(view);
    	Usuario usuario = usuarios.get(posicion);
    	dbHandler.open();
    	dbHandler.deleteUsuario(usuario);
    	usuarios = dbHandler.getAllUsuarios();
    	dbHandler.close();

    	miAdapter = new UsuarioAdapter(this, usuarios);
        setListAdapter(miAdapter);
    }
    
}
