package com.alvaro93mg.android.serializable.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.alvaro93mg.android.serializable.R;
import com.alvaro93mg.android.serializable.adapters.PersonaArrayAdapter;
import com.alvaro93mg.android.serializable.data.Persona;

import java.util.ArrayList;

/**
 * Created by Alvaro on 24/03/14.
 */

public class ListadoPersonasActivity extends ListActivity {

    private ArrayList<Persona> personas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_personas);
        asignarArrayListPersonasMedianteSerializacion();
        setListAdapter(new PersonaArrayAdapter(this, personas));
    }

    @SuppressWarnings("unchecked")
    /**
     * Obtenemos mediante getSerializableExtra(), el arraylist de personas, y lo asignamos.
     * Este método tiene la anotación 'SuppressWarnings("unchecked")' para que no nos muestre warnings,
     * ya que el método de getSerializableExtra() devuelve un objeto Serializable,
     * y al castearlo a (ArrayList<Persona>) nos daría un warning diciendo que no es
     * seguro o que no está comprobado. Esto pasa porque el compilador de Java no puede detectar el tipo o tipos
     * del objeto Serializable en tiempo de compilación, solo puede comprobarlo en tiempo de ejecución.
     */
    private void asignarArrayListPersonasMedianteSerializacion() {
        personas = (ArrayList<Persona>) getIntent().getSerializableExtra(MainActivity.KEY_PERSONAS);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Persona persona = (Persona) l.getItemAtPosition(position);
        Intent intent = new Intent(ListadoPersonasActivity.this, PersonaDetallesActivity.class);
        intent.putExtra(MainActivity.KEY_PERSONA, persona);
        startActivity(intent);
    }

}