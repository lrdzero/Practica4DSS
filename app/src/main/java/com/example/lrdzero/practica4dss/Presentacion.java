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
import android.widget.Button;

public class Presentacion extends Activity implements View.OnClickListener {
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        btn1 = (Button) findViewById(R.id.pagina);


        btn1.setBackgroundResource(R.drawable.arenusca);
        btn1.setOnClickListener(this);
    }
public void onClick(View v){
    switch (v.getId()){
        case R.id.pagina:
            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("www.google.es"));
            startActivity(myWebLink);
            break;
    }
}
}
