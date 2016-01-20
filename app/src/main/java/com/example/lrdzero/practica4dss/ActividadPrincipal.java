package com.example.lrdzero.practica4dss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ActividadPrincipal extends Activity implements View.OnClickListener{
    private Button ot1,ot2,ot3,ot4;
    private Utilidad mUtilidad;
    private DBPref db;
    private MediaPlayer acierto,fallo;
    private TextView texto;
    private int PREGUNTAS=0;
    private final ArrayList<Pregunta> preguntas=new ArrayList<Pregunta>();
    private int index=0;
    private Cursor cuestiones;
    static final int PICK_CONTACT_REQUEST = 1;
    static final int PICK_ELSE_REQUEST=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_actividad_principal);



            mUtilidad = ( Utilidad ) getApplicationContext() ;
            ot1=(Button) findViewById(R.id.opcion_1);
            ot2=(Button) findViewById(R.id.opcion_2);
            ot3=(Button) findViewById(R.id.opcion_3);
            ot4=(Button) findViewById(R.id.opcion_4);
            texto =(TextView) findViewById(R.id.pregunta_inicial);


            acierto = MediaPlayer.create(this,R.raw.acierto);
            fallo = MediaPlayer.create(this, R.raw.fallo);

            db = DBPref.getInstance(this);




            cuestiones = this.db.getPreguntas(DBPref.Categoria.TECNOLOGIA, DBPref.Dificultad.FACIL, 5);
        cuestiones.moveToFirst();
        for(int i=0;i<cuestiones.getCount();i++,cuestiones.moveToNext()) {

            Log.e("SDFA", "ASDF");
            String[] cnt = new String[3];
            for (int j = 0; j < 3; j++) {
                cnt[j] = cuestiones.getString(cuestiones.getColumnIndex("respuesta_incorrecta_" + (j + 1)));
            }
            Pregunta p = new Pregunta(cuestiones.getString(cuestiones.getColumnIndex("pregunta")), cuestiones.getString(cuestiones.getColumnIndex("respuesta_correcta")), cnt, cuestiones.getString(cuestiones.getColumnIndex("pregunta_tipo")));
            preguntas.add(p);
        }

                ot1.setText("");
                ot2.setText("");
                ot3.setText("");
                ot4.setText("");
                ot1.setBackgroundResource(android.R.drawable.btn_default);
                ot2.setBackgroundResource(android.R.drawable.btn_default);
                ot3.setBackgroundResource(android.R.drawable.btn_default);
                ot4.setBackgroundResource(android.R.drawable.btn_default);
                texto.setBackgroundResource(android.R.drawable.btn_default);
                if(preguntas.get(index).getTipo().equals("1")){
                    String imageName = preguntas.get(index).getRespuesta();

                    int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    texto.setText(preguntas.get(index).getPregunta());

                    ot1.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    imageName = incorrectas[0];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot2.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));

                    imageName = incorrectas[1];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot3.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));

                    imageName = incorrectas[2];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot4.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));



                }
                else if (preguntas.get(index).getTipo().equals("2")){
                    String imageName = preguntas.get(index).getPregunta();
                    int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    texto.setBackground(resizeImage(ActividadPrincipal.this,resID,100,100));
                    texto.setText(" ¿Qué es esto? ");
                    ot1.setText(preguntas.get(index).getRespuesta());
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    ot2.setText(incorrectas[0]);
                    ot3.setText(incorrectas[1]);
                    ot4.setText(incorrectas[2]);

                }
                else {

                    texto.setText(preguntas.get(index).getPregunta());
                    ot1.setText(preguntas.get(index).getRespuesta());
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    ot2.setText(incorrectas[0]);
                    ot3.setText(incorrectas[1]);
                    ot4.setText(incorrectas[2]);
                }


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
            ot1.setEnabled(false);
            ot2.setEnabled(false);
            ot3.setEnabled(false);
            ot4.setEnabled(false);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(index==cuestiones.getCount()-1){
                    mUtilidad.setPuntuacion(10);
                    crearFin().show();
            }
                else {
                  avance().show();
                }
            break;
        case R.id.opcion_2:
            ot1.setEnabled(false);
            ot2.setEnabled(false);
            ot3.setEnabled(false);
            ot4.setEnabled(false);
            fallo.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            crearVista().show();
            break;
        case R.id.opcion_3:
            ot1.setEnabled(false);
            ot2.setEnabled(false);
            ot3.setEnabled(false);
            ot4.setEnabled(false);
            fallo.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            crearVista().show();
            break;
        case R.id.opcion_4:
            ot1.setEnabled(false);
            ot2.setEnabled(false);
            ot3.setEnabled(false);
            ot4.setEnabled(false);
            fallo.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                index=0;
                mUtilidad.resetearPuntuacion();
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
                index++;
                ot1.setEnabled(true);
                ot2.setEnabled(true);
                ot3.setEnabled(true);
                ot4.setEnabled(true);
                dialog.cancel();
            }
        }).setNegativeButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                index=0;
                mUtilidad.resetearPuntuacion();
                ot1.setText("");
                ot2.setText("");
                ot3.setText("");
                ot4.setText("");
                ot1.setBackgroundResource(android.R.drawable.btn_default);
                ot2.setBackgroundResource(android.R.drawable.btn_default);
                ot3.setBackgroundResource(android.R.drawable.btn_default);
                ot4.setBackgroundResource(android.R.drawable.btn_default);
                texto.setBackgroundResource(android.R.drawable.btn_default);
                if(preguntas.get(index).getTipo().equals("1")){
                    String imageName = preguntas.get(index).getRespuesta();

                    int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    texto.setText(preguntas.get(index).getPregunta());

                    ot1.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    imageName = incorrectas[0];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot2.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));

                    imageName = incorrectas[1];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot3.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));

                    imageName = incorrectas[2];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot4.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));



                }
                else if (preguntas.get(index).getTipo().equals("2")){
                    String imageName = preguntas.get(index).getPregunta();
                    int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    texto.setBackground(resizeImage(ActividadPrincipal.this,resID,100,100));
                    texto.setText(" ¿Qué es esto? ");
                    ot1.setText(preguntas.get(index).getRespuesta());
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    ot2.setText(incorrectas[0]);
                    ot3.setText(incorrectas[1]);
                    ot4.setText(incorrectas[2]);

                }
                else {

                    texto.setText(preguntas.get(index).getPregunta());
                    ot1.setText(preguntas.get(index).getRespuesta());
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    ot2.setText(incorrectas[0]);
                    ot3.setText(incorrectas[1]);
                    ot4.setText(incorrectas[2]);
                }
                ot1.setEnabled(true);
                ot2.setEnabled(true);
                ot3.setEnabled(true);
                ot4.setEnabled(true);
                //puntuacion resetea
                dialog.cancel();
            }
        });

        return builder;
    }
    public AlertDialog.Builder crearFin(){
        int value = mUtilidad.getPuntuacion();
        AlertDialog.Builder builder = new AlertDialog.Builder(ActividadPrincipal.this);
        builder.setMessage("¡¡¡¡FELICIDADES!!!\n Tu puentuación es de: "+value).setTitle("FIN DEL JUEGO").setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
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
                index=0;
                mUtilidad.resetearPuntuacion();
                //puntuacion resetea
                dialog.cancel();
            }
        });

        return builder;
    }
    public AlertDialog.Builder avance(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActividadPrincipal.this);
        builder.setMessage("Correcto").setTitle("HAS ACERTADO").setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUtilidad.setPuntuacion(10);
                index++;
                ot1.setText("");
                ot2.setText("");
                ot3.setText("");
                ot4.setText("");
                ot1.setBackgroundResource(android.R.drawable.btn_default);
                ot2.setBackgroundResource(android.R.drawable.btn_default);
                ot3.setBackgroundResource(android.R.drawable.btn_default);
                ot4.setBackgroundResource(android.R.drawable.btn_default);
                texto.setBackgroundResource(android.R.drawable.btn_default);
                if(preguntas.get(index).getTipo().equals("1")){
                    String imageName = preguntas.get(index).getRespuesta();

                    int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    texto.setText(preguntas.get(index).getPregunta());

                    ot1.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    imageName = incorrectas[0];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot2.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));

                    imageName = incorrectas[1];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot3.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));

                    imageName = incorrectas[2];
                    resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    ot4.setBackground(resizeImage(ActividadPrincipal.this, resID, 300, 300));



                }
                else if (preguntas.get(index).getTipo().equals("2")){
                    String imageName = preguntas.get(index).getPregunta();
                    int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    texto.setBackground(resizeImage(ActividadPrincipal.this,resID,100,100));
                    texto.setText(" ¿Qué es esto? ");
                    ot1.setText(preguntas.get(index).getRespuesta());
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    ot2.setText(incorrectas[0]);
                    ot3.setText(incorrectas[1]);
                    ot4.setText(incorrectas[2]);

                }
                else {

                    texto.setText(preguntas.get(index).getPregunta());
                    ot1.setText(preguntas.get(index).getRespuesta());
                    String [] incorrectas=preguntas.get(index).getRespuestasErroneas();
                    ot2.setText(incorrectas[0]);
                    ot3.setText(incorrectas[1]);
                    ot4.setText(incorrectas[2]);
                }
                ot1.setEnabled(true);
                ot2.setEnabled(true);
                ot3.setEnabled(true);
                ot4.setEnabled(true);
                dialog.cancel();
            }
        });

        return builder;
    }
    public static Drawable resizeImage(Context ctx, int resId, int w, int h) {

        // cargamos la imagen de origen
        Bitmap BitmapOrg = BitmapFactory.decodeResource(ctx.getResources(),
                resId);

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        // calculamos el escalado de la imagen destino
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // para poder manipular la imagen
        // debemos crear una matriz

        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // volvemos a crear la imagen con los nuevos valores
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                width, height, matrix, true);

        // si queremos poder mostrar nuestra imagen tenemos que crear un
        // objeto drawable y así asignarlo a un botón, imageview...
        return new BitmapDrawable(resizedBitmap);

    }
}
