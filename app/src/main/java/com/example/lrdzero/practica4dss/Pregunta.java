package com.example.lrdzero.practica4dss;

/**
 * Created by lrdzero on 10/01/2016.
 */

//Patron Inmutable
public class Pregunta {
    private final String pregunta;
    private final String respuesta;
    private final String [] algo;
    private final String tipo;

    Pregunta(String pre,String resp,String []conjuto,String tp){
        this.pregunta=pre;
        this.respuesta=resp;
        this.algo= new String[7];
        for(int i=0;i<conjuto.length;i++){
            this.algo[i]=conjuto[i];
        }
        this.tipo=tp;
    }

    public String getPregunta(){return pregunta;}
    public String getRespuesta(){return respuesta;}
    public String getTipo(){return tipo;}
    public String[] getRespuestasErroneas(){return algo;}
}
