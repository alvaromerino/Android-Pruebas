package com.alvaromerino.localizacion;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A través de esta Actividad, podremos recuperar la posición actual a través del GPS.
 * Si en vez del GPS, quisieramos usar el 3G como localización, solo habría que cambiar en el código LocationManager.GPS_PROVIDER por
 * LocationManager.NETWORK_PROVIDER
 * @author Alvaro
 *
 */

public class MainActivity extends Activity implements OnClickListener, LocationListener {

	private TextView txtLatitud, txtLongitud, txtPrecision, txtEstadoProveedor;
	private Button btnActualizar, btnDesactivar;
	
	private LocationManager locationManager;
	private Location location;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtLatitud = (TextView) findViewById(R.id.textviewLatitud);
        txtLongitud = (TextView) findViewById(R.id.textviewLongitud);
        txtPrecision = (TextView) findViewById(R.id.textviewPrecision);
        txtEstadoProveedor = (TextView) findViewById(R.id.textviewEstadoProveedor);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnDesactivar = (Button) findViewById(R.id.btnDesactivar);
        btnActualizar.setOnClickListener(this);
        btnDesactivar.setOnClickListener(this);
    }

    private void actualizarPosicion() 
    {
    	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    	location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	muestraPosicion();
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, this);
    }
    
    private void muestraPosicion()
    {
    	if (location != null)
    	{
    		txtLatitud.setText("Latitud: " + String.valueOf(location.getLatitude()));
    		txtLongitud.setText("Longitud: " + String.valueOf(location.getLongitude()));
    		txtPrecision.setText("Precision: " + String.valueOf(location.getAccuracy()));
    		Log.i("localizacion", String.valueOf(location.getLatitude()) + " - " + String.valueOf(location.getLongitude()));
    	}
    	else
    	{
    		txtLatitud.setText("Latitud: (sin datos)");
    		txtLongitud.setText("Longitud: (sin datos)");
    		txtPrecision.setText("Precision: (sin datos)");
    		Toast.makeText(this, "Sin datos. Puede que tenga el GPS desactivado en Ajustes.", Toast.LENGTH_LONG).show();
    	}
    }
    
    // Método del OnClickListener
	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) {
		case R.id.btnActualizar:
			actualizarPosicion();
			break;

		case R.id.btnDesactivar:
			if (locationManager != null) 
			{
				locationManager.removeUpdates(this);
				Toast.makeText(this, "Se han desactivado las actualizaciones del GPS en esta aplicación.", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(this, "Está desactivado, por lo tanto, no hay nada que desactivar.",Toast.LENGTH_LONG).show();
			}
			break;
			
		default:
			break;
		}
	}

	/* Métodos del LocationListener */
	@Override
	public void onLocationChanged(Location location)
	{
		this.location = location;
		muestraPosicion();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		Log.i("localizacion", "Estado del Proveedor: " + status);
		txtEstadoProveedor.setText("Estado del Proveedor: " + status);
	}

	@Override
	public void onProviderEnabled(String provider) 
	{
		txtEstadoProveedor.setText("Proveedor ON");
	}

	@Override
	public void onProviderDisabled(String provider) 
	{
		txtEstadoProveedor.setText("Proveedor OFF");
	}

}
