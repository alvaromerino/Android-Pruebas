package com.alvaromerino.webviewejemplo.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.alvaromerino.webviewejemplo.R;

public class MainActivity extends Activity {
	
	private Context ctx;
	private WebView webview;
	private MiWebViewClient miWebClient;
	private MiWebChromeClient miWebChromeClient;
	private final static String MI_URL = "https://www.google.es";
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ctx = this;
		webview = (WebView) findViewById(R.id.webView);
		
		// Activar JavaScript
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		
		/* Establezco el webviewclient y el webchromeclient
		 * El webviewclient para poder tratar por ejemplo el comportamiento al pulsar sobre un enlace (para que se abra sobre el webview y no sobre un navegador), 
		 * o el comportamiento si hay un error al cargar la página.
		 * El webchromeclient por ejemplo para poder tratar los alert's de JavaScript.
		 */
		miWebClient = new MiWebViewClient();
		miWebChromeClient = new MiWebChromeClient();
		webview.setWebViewClient(miWebClient);
		webview.setWebChromeClient(miWebChromeClient);
		
		// Cargamos la URL
		webview.loadUrl(MI_URL);
	}
	
	/* Clase privada MiWebCliente */
	private class MiWebViewClient extends WebViewClient
	{
		/**
		 * Método de callback llamado cuando en una página se pulsa un enlace.
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			view.loadUrl(url);
			return true;
		}
		
		/**
		 * Método de callback llamado cuando se recibe un error en la carga de una página.
		 */
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) 
		{
			Toast.makeText(ctx, "Error: " + description, Toast.LENGTH_SHORT).show();
		}
	}
	
	/* Clase privada MiWebChromeClient */
	private class MiWebChromeClient extends WebChromeClient
	{
		/**
		 * Maneja los alert's de javascript
		 */
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) 
		{
			Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
			result.confirm();
			return true;
		}
	}

}
