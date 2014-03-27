package com.alvaro93mg.android.parcelable.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.alvaro93mg.android.parcelable.R;
import com.alvaro93mg.android.parcelable.adapters.PersonaArrayAdapter;
import com.alvaro93mg.android.parcelable.data.Persona;

import java.util.ArrayList;

/**
 * Created by Alvaro on 25/03/14.
 */

public class ListadoPersonasActivity extends ListActivity {

    private ArrayList<Persona> personas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_personas);
        //Obtenemos el arraylist mediante getParcelableArrayListExtra();
        personas = getIntent().getParcelableArrayListExtra(MainActivity.KEY_PERSONAS);
        setListAdapter(new PersonaArrayAdapter(this, personas));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Persona persona = (Persona) l.getItemAtPosition(position);
        Intent intent = new Intent(ListadoPersonasActivity.this, PersonaDetallesActivity.class);
        //Añadimos como extra al intent la persona en la que se ha hecho click.
        intent.putExtra(MainActivity.KEY_PERSONA, persona);
        startActivity(intent);
    }

}