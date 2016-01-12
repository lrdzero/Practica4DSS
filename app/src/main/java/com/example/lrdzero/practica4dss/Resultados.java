package com.example.lrdzero.practica4dss;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {
    private TextView texto;
    private DBPref db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        texto =(TextView) findViewById(R.id.txto1);

        db = new DBPref(this);
        Cursor cur =db.getPuntuaciones();
        String data="";
        cur.moveToFirst();
        if(cur.getCount()!=0) {

               for(int i=0;i<cur.getCount();i++){
                   Log.e("DATAAA",cur.getString(cur.getColumnIndex("total")));
                   data+=cur.getString(cur.getColumnIndex("juego"))+" "+cur.getString(cur.getColumnIndex("total"))+"\n";
                   cur.moveToNext();
               }
        }
        texto.setText(data);



    }

}
