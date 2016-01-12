package com.example.lrdzero.practica4dss;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**

 10 * Created by Manuel on 17/12/2015.

 11 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD="Juego-preguntasDB";
    public static final int VERSION_ACTUAL_BD=2;
    protected SQLiteDatabase db;
    protected Context ctx;

    public DBHelper(Context context) {

        super(context, NOMBRE_BD, null, VERSION_ACTUAL_BD);
        this.ctx = context;
        this.open();

        }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d("debug", "Creando la Base de Datos");
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner( this.ctx.getResources().openRawResource(R.raw.database) );

        while(sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append('\n');

            if (sb.indexOf(";") != -1) {
                sqLiteDatabase.execSQL(sb.toString());
                sb.delete(0, sb.capacity());

                }

            }

        }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        Log.d("debug", "Actualizando la base de datos desde la version " + i + " a la version " + i2);

        }

    public void open() {

        Log.d("debug", "Abriendo la base de datos");
        this.db = this.getWritableDatabase();

        }

    public void close() {

        Log.d("debug", "Cerrando la base de datos");
        this.db.close();

        }

    public void addSampleData() {

        try {

            this.db.execSQL("DELETE FROM preguntas;");
            this.db.execSQL("INSERT INTO preguntas (pregunta, respuesta_correcta,respuesta_incorrecta_1, respuesta_incorrecta_2, respuesta_incorrecta_3,categoria, pregunta_tipo)" +"VALUES(\"El agua es esencial para la vida: En que porcentaje forma parte del peso total del cuerpo humano?\", \"60-80%\", \"10-30%\", \"40-50%\",\"90-110%\", 'G', '0');");

            //RESTO DE PREGUNTAS

            }

        catch (Exception e) {

            e.printStackTrace();
            Log.d("debug", e.toString());

            }

        }

    }