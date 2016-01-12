package com.example.lrdzero.practica4dss;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by lrdzero on 10/01/2016.
 */
public class Utilidad extends Application{

    private int respuesta =0;

    @Override

    public void onConfigurationChanged ( Configuration newConfig ){

    super.onConfigurationChanged(newConfig) ;
    }

@Override
public void onCreate ( ) {

        super.onCreate();
}

        @Override

public void onLowMemory ( ) {

        super.onLowMemory() ; }

        @Override
public void onTerminate ( ) {
        super.onTerminate();
        }

        public int getPuntuacion ( ) {

        return respuesta ; }
        public void resetearPuntuacion(){
            respuesta=0;
        }
        public void setPuntuacion ( int puntuacion ) {
        respuesta+= puntuacion ; }


}
