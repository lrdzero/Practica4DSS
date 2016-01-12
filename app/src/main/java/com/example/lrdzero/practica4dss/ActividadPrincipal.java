package com.example.lrdzero.practica4dss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActividadPrincipal extends Activity implements View.OnClickListener{
    private Button ot1,ot2,ot3,ot4;
    private Utilidad mUtilidad;
    private DBPref db;
    private MediaPlayer acierto,fallo;
    private TextView texto;
    private int PREGUNTAS=0;
    private Pregunta pf;
    private DBHelper db2;
    private Cursor cuestiones;
    static final int PICK_CONTACT_REQUEST = 1;
    static final int PICK_ELSE_REQUEST=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_actividad_principal);

            //Recuperar el contexto para introducirle la pregunta

            mUtilidad = ( Utilidad ) getApplicationContext() ;
            ot1=(Button) findViewById(R.id.opcion_1);
            ot2=(Button) findViewById(R.id.opcion_2);
            ot3=(Button) findViewById(R.id.opcion_3);
            ot4=(Button) findViewById(R.id.opcion_4);
            texto =(TextView) findViewById(R.id.pregunta_inicial);


            acierto = MediaPlayer.create(this,R.raw.acierto);
            fallo = MediaPlayer.create(this,R.raw.fallo);

            db = new DBPref(this);




            cuestiones = this.db.getPreguntas(DBPref.Categoria.TECNOLOGIA, DBPref.Dificultad.FACIL, 4);
            String [] d =cuestiones.getColumnNames();
            String [] quest = new String[cuestiones.getCount()];




                cuestiones.moveToFirst();

                texto.setText(cuestiones.getString(cuestiones.getColumnIndex("pregunta")));
                ot1.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_correcta")));
                ot2.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_1")));
                ot3.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_2")));
                ot4.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_3")));


            //for(int i=0;i<quest.length;i++)
              //  Log.e("IDDA",quest[i]);




            //String dte= cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_1"));



            ot1.setOnClickListener(this);
            ot2.setOnClickListener(this);
            ot3.setOnClickListener(this);
            ot4.setOnClickListener(this);


    }
public void onClick(View v){

    Intent fin;
    switch (v.getId()){
        case R.id.opcion_1:
            acierto.start();
                if(cuestiones.isLast()){
                    mUtilidad.setPuntuacion(10);
                    crearFin().show();


                }
                else {
                    mUtilidad.setPuntuacion(10);
                    cuestiones.moveToNext();
                    texto.setText(cuestiones.getString(cuestiones.getColumnIndex("pregunta")));
                    ot1.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_correcta")));
                    ot2.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_1")));
                    ot3.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_2")));
                    ot4.setText(cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_3")));
                }
            break;
        case R.id.opcion_2:
            fallo.start();
            crearVista().show();
            break;
        case R.id.opcion_3:
            fallo.start();
            crearVista().show();
            break;
        case R.id.opcion_4:

            fallo.start();
            crearVista().show();
            break;
    }
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {

            if (resultCode == RESULT_OK) {
                finish();
            }
            else{
                //Reiniciar juego
                cuestiones.moveToFirst();
            }
        }

    }
    public AlertDialog.Builder crearVista(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActividadPrincipal.this);
        builder.setMessage("Fallaste").setTitle("Error").setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //restar puntuación y continuar
                mUtilidad.setPuntuacion(-7);
                cuestiones.moveToNext();
                dialog.cancel();
            }
        }).setNegativeButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cuestiones.moveToFirst();
                //puntuacion resetea
                dialog.cancel();
            }
        });

        return builder;
    }
    public AlertDialog.Builder crearFin(){
        int value = mUtilidad.getPuntuacion();
        AlertDialog.Builder builder = new AlertDialog.Builder(ActividadPrincipal.this);
        builder.setMessage("¡¡¡¡FELICIDADES!!!\n Tu puentuación es de: "+value).setTitle("FIN DEL JUEGO").setPositiveButton("Salir al menú principal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //restar puntuación y continuar
                db.addPuntuacion(mUtilidad.getPuntuacion());
                mUtilidad.resetearPuntuacion();
                Intent fin = new Intent(ActividadPrincipal.this,Despedida.class);
                startActivityForResult(fin, PICK_CONTACT_REQUEST);
                dialog.cancel();
            }
        }).setNegativeButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cuestiones.moveToFirst();
                mUtilidad.resetearPuntuacion();
                //puntuacion resetea
                dialog.cancel();
            }
        });

        return builder;
    }
}
