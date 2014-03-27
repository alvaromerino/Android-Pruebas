package com.alvaro93mg.android.activity_saludador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Alvaro on 24/03/14.
 */
public class MainActivity extends Activity {

    public static String KEY_NOMBRE = "NOMBRE";
    private static int SALUDO_REQUEST_CODE = 0;
    private EditText editTextNombre;
    private Button btnSaludar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        btnSaludar = (Button) findViewById(R.id.btnSaludar);

        btnSaludar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString().trim();
                if (nombre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir algo en nombre...", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SaludoActivity.class);
                    intent.putExtra(KEY_NOMBRE, nombre);
                    startActivityForResult(intent, SALUDO_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SALUDO_REQUEST_CODE && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra(KEY_NOMBRE);
            Toast.makeText(this, "Anteriormente se saludó a: " + nombre, Toast.LENGTH_LONG).show();
        }

    }
}