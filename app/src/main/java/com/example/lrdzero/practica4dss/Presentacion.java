package com.example.lrdzero.practica4dss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Presentacion extends Activity implements View.OnClickListener {
    private Button btn1;
    private WebView fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        btn1 = (Button) findViewById(R.id.pagina);
        fg=(WebView) findViewById(R.id.fragment);
        fg.loadUrl("https://play.google.com/store/apps/category/GAME");


        btn1.setOnClickListener(this);
    }
public void onClick(View v){
    Intent myWebLink;
    switch (v.getId()){
        case R.id.pagina:
            myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("https://play.google.com/store/apps/category/GAME"));
            startActivity(myWebLink);
            break;
        case R.id.fragment:
            myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("https://play.google.com/store/apps/category/GAME"));
            startActivity(myWebLink);
            break;
    }
}
}
