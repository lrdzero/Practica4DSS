package com.example.lrdzero.practica4dss;

import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

/**

 5 * Created by Manuel on 17/12/2015.

 6 */

        public class DBPref extends DBHelper {
    private static volatile DBPref instance;
    public static enum Categoria {

        HISTORIA('A'),
        //...
        TECNOLOGIA('G'),
        OTROS('Z');

        public final char C;

        Categoria(char c) {

            this.C = c;

            }

        }

    public static enum Dificultad {

        FACIL('e'), MEDIA('m'),DIFICIL('h');

        public final char D;

        Dificultad(char d) {
            this.D = d;
            }

        }

    public DBPref(Context contexto) {

        super(contexto);

        }

    public static DBPref  getInstance(Context ctx){
        if(instance == null){
            synchronized (Utilidad.class){
                if(instance==null){
                    instance = new DBPref(ctx);
                }
            }
        }
        return instance;
    }
    public void addRegistro(Pregunta pregunta) {

        ContentValues valores = new ContentValues();
        valores.put("pregunta", pregunta.getPregunta());
        valores.put("respuesta_correcta", pregunta.getRespuesta());
        String[] respuestas_erroneas = pregunta.getRespuestasErroneas();
        for(int i = 0; i < respuestas_erroneas.length; i++) {
            int valor=i+1;
            valores.put("respuesta_incorrecta_" + valor, respuestas_erroneas[i]);
            }
        valores.put("dificultad","e");
        valores.put("categoria","G");
        valores.put("pregunta_tipo",0);
        db.insert("preguntas", null, valores);

        }

    public void addRegistros(Pregunta[] preguntas) {
        for(int i = 0; i < preguntas.length; i++) {
            this.addRegistro(preguntas[i]);
            }
        }

    public void addPuntuacion(int total){
        ContentValues valores = new ContentValues();
        valores.put("juego","unjuego");
        valores.put("total",Integer.toString(total));
        db.insert("puntuaciones",null,valores);
    }
    public Cursor getPreguntas(Categoria c, Dificultad d, int limit) {
        return this.db.rawQuery("SELECT pregunta, respuesta_correcta,respuesta_incorrecta_1, respuesta_incorrecta_2," + "respuesta_incorrecta_3,pregunta_tipo FROM preguntas  WHERE Categoria = ? AND Dificultad = ?" + "ORDER BY RANDOM() LIMIT ?",new String[]{String.valueOf(c.C), String.valueOf(d.D), String.valueOf(limit)});

        }
    public Cursor getPuntuaciones(){
        return this.db.rawQuery("SELECT juego,total FROM puntuaciones",new String[]{});
    }


    //...

}