package com.example.lrdzero.practica4dss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActividad extends Activity implements View.OnClickListener{
    private Button start,resultados,ajustes;
    private DBPref db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_actividad);
        start=(Button) findViewById(R.id.btn_jugar);
        resultados=(Button) findViewById(R.id.btn_resultados);
        ajustes=(Button) findViewById(R.id.btn_ajustes);

        db = new DBPref(this);
        start.setOnClickListener(this);
        resultados.setOnClickListener(this);
        ajustes.setOnClickListener(this);

    }
public void onClick(View v){
    Intent i;
    switch (v.getId()){
        case R.id.btn_ajustes:
            i=new Intent(MenuActividad.this,Presentacion.class);
            startActivity(i);
            break;
        case R.id.btn_jugar:
            i=new Intent(MenuActividad.this,ActividadPrincipal.class);
            startActivity(i);
            break;
        case R.id.btn_resultados:
            i=new Intent(MenuActividad.this,Resultados.class);
            startActivity(i);
            break;
    }
}
}
