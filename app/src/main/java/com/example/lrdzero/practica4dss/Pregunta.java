package com.example.lrdzero.practica4dss;

/**
 * Created by lrdzero on 10/01/2016.
 */
public class Pregunta {
    private String pregunta;
    private String respuesta;
    private String [] algo;

    Pregunta(){algo = new String[7];}

    public void setPregunta(String p){pregunta=p;}
    public void setRespuesta(String r){respuesta=r;}
    public void setRespuestasErroneas(String g[]){
        int valor;
        if(g.length>7){
            for (int i = 0; i < 7; i++) {
                algo[i] = g[i];
            }
        }
        else {
            for (int i = 0; i < g.length; i++) {
                algo[i] = g[i];
            }
        }
    }
    public String getPregunta(){return pregunta;}
    public String getRespuesta(){return respuesta;}
    public String[] getRespuestasErroneas(){return algo;}
}
