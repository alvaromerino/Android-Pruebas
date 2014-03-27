package com.alvaro93mg.android.activity_saludador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alvaro on 24/03/14.
 */
public class SaludoActivity extends Activity {

    private TextView textViewSaludo;
    private Button btnVolver;
    private String nombre;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        textViewSaludo = (TextView) findViewById(R.id.textViewSaludo);
        btnVolver = (Button) findViewById(R.id.btnVolver);
        nombre = getIntent().getStringExtra(MainActivity.KEY_NOMBRE);
        textViewSaludo.setText("¡Hola " + nombre + "!");

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolverNombreToMainActivity();
            }
        });
    }

    private void devolverNombreToMainActivity() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_NOMBRE, nombre);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        devolverNombreToMainActivity();
    }
}