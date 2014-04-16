package com.alvaromerino.acelerometro;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AcelerometroActivity extends Activity implements SensorEventListener
{
	private SensorManager sensorManager;
	private Sensor acelerometro;
	private TextView textviewX, textviewY, textviewZ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);

        textviewX = (TextView) findViewById(R.id.txtX);
        textviewY = (TextView) findViewById(R.id.txtY);
        textviewZ = (TextView) findViewById(R.id.txtZ);
        
        // Establezco orientacion vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    	sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    	
    	// Si al obtener del sensorManager el sensor por defecto de Tipo Acelerometro es distinto de null,
    	// quiere decir que disponemos de un Acelerometro.
    	if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
    	{
    		// obtengo el acelerometro.
    		acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    		// registro el listener
    		sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    	}
    }
    
    @Override
    protected void onPause()
    {
    	// quito el listener registrado
    	sensorManager.unregisterListener(this, acelerometro);
    	super.onPause();
    }
    
    @Override
    protected void onStop() 
    {
    	// quito el listener registrado
    	sensorManager.unregisterListener(this, acelerometro);
    	super.onStop();
    }

    // Método llamado cuando los valores del sensor han cambiado.
	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		this.textviewX.setText("X = " + event.values[0]);
		this.textviewY.setText("Y = " + event.values[1]);
		this.textviewZ.setText("Z = " + event.values[2]);
	}

	// Método llamado cuando la precisión del sensor ha cambiado.
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
		/* Aquí no nos interesa hacer nada, pero la interfaz SensorEventListener nos obliga 
		 * a implementar el método aunque sea vacío. */
	}

}
