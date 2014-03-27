package com.alvaro93mg.android.parcelable.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.alvaro93mg.android.parcelable.R;
import com.alvaro93mg.android.parcelable.data.Persona;

/**
 * Created by Alvaro on 25/03/14.
 */
public class PersonaDetallesActivity extends Activity {

    private Persona persona;
    private TextView textViewTitulo, textViewDni, textViewNombre, textViewApellido1, textViewApellido2, textViewEdad;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_detalles);

        //Obtenemos la persona a representar mediante getParcelableExtra();
        persona = (Persona) getIntent().getParcelableExtra(MainActivity.KEY_PERSONA);

        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        textViewDni = (TextView) findViewById(R.id.textViewDni);
        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        textViewApellido1 = (TextView) findViewById(R.id.textViewApellido1);
        textViewApellido2 = (TextView) findViewById(R.id.textViewApellido2);
        textViewEdad = (TextView) findViewById(R.id.textViewEdad);

        textViewTitulo.setText("Detalles de la Persona");
        textViewDni.setText(String.format("Dni: %s", persona.getDni()));
        textViewNombre.setText(String.format("Nombre: %s", persona.getNombre()));
        textViewApellido1.setText(String.format("Primer Apellido: %s", persona.getApellido1()));
        textViewApellido2.setText(String.format("Segundo Apellido: %s", persona.getApellido2()));
        textViewEdad.setText(String.format("Edad: %d", persona.getEdad()));
    }
}