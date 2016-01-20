package com.example.lrdzero.practica4dss;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Despedida extends Activity implements View.OnClickListener{
    private Button botonSalida,despedida;
    private TextView screen,test2,test3;
    private ImageView dsf;
    Utilidad mUtilidad;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despedida);
        screen=(TextView) findViewById(R.id.despete);
        test2=(TextView)findViewById(R.id.despe1);
        test3=(TextView) findViewById(R.id.despe3);

        despedida=(Button) findViewById(R.id.despe);
        botonSalida=(Button) findViewById(R.id.back);
        mp = MediaPlayer.create(this,R.raw.acierto);



        screen.setText(R.string.despedida);
        test2.setText(R.string.despedida1);
        test3.setText(R.string.despedida3);

        despedida.setOnClickListener(this);
        botonSalida.setOnClickListener(this);

    }
public void onClick(View v){
    Intent i;
    switch(v.getId()){
        case R.id.despe:
            mp.start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i= getIntent();
            i.putExtra("RESULTADO","bien");
            setResult(RESULT_OK,i);
                finish();

            break;

        case R.id.back:
            i= getIntent();
            i.putExtra("RESULTADO","bien");
            setResult(RESULT_CANCELED,i);
            finish();
            break;
    }
}
}
