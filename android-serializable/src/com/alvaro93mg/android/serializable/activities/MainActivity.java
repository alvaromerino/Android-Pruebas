package com.alvaro93mg.android.serializable.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.alvaro93mg.android.serializable.R;
import com.alvaro93mg.android.serializable.data.Persona;

import java.util.ArrayList;

/**
 * Created by Alvaro on 24/03/14.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static int FORMULARIO_NUEVA_PERSONA_REQUEST_CODE = 0;
    public static String KEY_PERSONAS = "personas";
    public static String KEY_PERSONA = "persona";
    private Button btnAlta, btnListado, btnSalir;
    private Context contexto;
    private ArrayList<Persona> personas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Si es la primera vez, inicializo el arraylist de personas;
        if (savedInstanceState == null) personas = new ArrayList<Persona>();

        contexto = this;

        btnAlta = (Button) findViewById(R.id.btnNuevaPersona);
        btnListado = (Button) findViewById(R.id.btnListadoPersonas);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnAlta.setOnClickListener(this);
        btnListado.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.btnNuevaPersona:
                intent = new Intent(contexto, FormularioActivity.class);
                startActivityForResult(intent, FORMULARIO_NUEVA_PERSONA_REQUEST_CODE);
                break;

            case R.id.btnListadoPersonas:
                intent = new Intent(contexto, ListadoPersonasActivity.class);
                //Pasamos el arraylist de personas a la Activity ListadoPersonasActivity
                //mediante putExtra(String name, Serializable value);
                intent.putExtra(KEY_PERSONAS, personas);
                startActivity(intent);
                break;

            case R.id.btnSalir:
                finish();
                System.exit(0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FORMULARIO_NUEVA_PERSONA_REQUEST_CODE && resultCode == RESULT_OK) {
            //Obtenemos mediante getSerializableExtra(),
            //la persona dada de alta en el formulario para añadirlo al arraylist.
            Persona persona = (Persona) data.getSerializableExtra(KEY_PERSONA);
            personas.add(persona);
        }
    }

}