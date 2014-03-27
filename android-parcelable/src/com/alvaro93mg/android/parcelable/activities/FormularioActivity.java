package com.alvaro93mg.android.parcelable.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alvaro93mg.android.parcelable.R;
import com.alvaro93mg.android.parcelable.data.Persona;

/**
 * Created by Alvaro on 25/03/14.
 */
public class FormularioActivity extends Activity {

    private EditText etDni, etNombre, etApellido1, etApellido2, etEdad;
    private Button btnAlta;
    private Context ctx;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ctx = this;

        etDni = (EditText) findViewById(R.id.editTextDni);
        etNombre = (EditText) findViewById(R.id.editTextNombre);
        etApellido1 = (EditText) findViewById(R.id.editTextApellido1);
        etApellido2 = (EditText) findViewById(R.id.editTextApellido2);
        etEdad = (EditText) findViewById(R.id.editTextEdad);
        btnAlta = (Button) findViewById(R.id.btnDarDeAlta);

        btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(sonLosDatosDelFormularioValidos()) {

                String dni = etDni.getText().toString().trim();
                String nombre = etNombre.getText().toString().trim();
                String apellido1 = etApellido1.getText().toString().trim();
                String apellido2 = etApellido2.getText().toString().trim();
                int edad = Integer.parseInt(etEdad.getText().toString());

                Persona persona = new Persona(dni, nombre, apellido1, apellido2, edad);
                Intent intent = new Intent();
                //Añadimos como extra al intent la persona que acabamos de dar de alta.
                intent.putExtra(MainActivity.KEY_PERSONA, persona);
                setResult(RESULT_OK, intent);
                finish();
            }

            }
        });
    }

    private boolean sonLosDatosDelFormularioValidos() {

        if(etDni.getText().toString().trim().isEmpty()) {
            Toast.makeText(ctx, "Dni vacío", Toast.LENGTH_LONG).show();
            return false;
        }

        if(etNombre.getText().toString().trim().isEmpty()) {
            Toast.makeText(ctx, "Nombre vacío", Toast.LENGTH_LONG).show();
            return false;
        }

        if(etApellido1.getText().toString().trim().isEmpty()) {
            Toast.makeText(ctx, "Primer Apellido vacío", Toast.LENGTH_LONG).show();
            return false;
        }

        if(etApellido2.getText().toString().trim().isEmpty()) {
            Toast.makeText(ctx, "Segundo Apellido vacío", Toast.LENGTH_LONG).show();
            return false;
        }

        if(etEdad.getText().toString().trim().isEmpty()) {
            Toast.makeText(ctx, "Edad vacía", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}