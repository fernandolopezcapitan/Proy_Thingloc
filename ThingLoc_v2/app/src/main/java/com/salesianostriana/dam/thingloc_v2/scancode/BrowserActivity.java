package com.salesianostriana.dam.thingloc_v2.scancode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.EditText;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;

import java.util.List;


public class BrowserActivity extends Activity {

    private WebView webview;
    private EditText url;
    private List<String> favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        webview = (WebView) this.findViewById(R.id.webView);
        url = (EditText) findViewById(R.id.urltext);


        webview.getSettings().setJavaScriptEnabled(true);

        url.setEnabled(false);

        String url = Utiles.ruta;

        Log.i("PRUEBA", url);
        webview.loadUrl("http://"+url);
    }


}
