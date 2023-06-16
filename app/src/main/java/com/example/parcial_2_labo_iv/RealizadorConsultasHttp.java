package com.example.parcial_2_labo_iv;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

public class RealizadorConsultasHttp implements Runnable
{
    String url_a_consultar;
    Handler manejador_solicitud;
    Boolean esUnaImagen;
    public static final int HuboTransmision_de_Imagen = 1;
    public static final int HuboTransmision_de_RespuestaDeTexto = 2;
    Integer posicionQueCambio;

    //Lo llamo desde el Adapter para traer la informacion de imagen necesaria
    public RealizadorConsultasHttp (String url_recibida, Handler manejador_recibido, Boolean esUnaImagen_recibido, Integer posicionRecibida)
    {
        this.url_a_consultar = url_recibida;
        this.manejador_solicitud = manejador_recibido;
        this.esUnaImagen = esUnaImagen_recibido;
        this.posicionQueCambio = posicionRecibida;
    }

    //Lo llamo desde el MainActivity para traer la informacion de texto necesaria
    public RealizadorConsultasHttp (String url_recibida, Handler manejador_recibido, Boolean esUnaImagen_recibido)
    {
        this.url_a_consultar = url_recibida;
        this.manejador_solicitud = manejador_recibido;
        this.esUnaImagen = esUnaImagen_recibido;
    }

    @Override
    public void run()
    {
        HttpManager http_manager = new HttpManager();
        try
        {
            //Genero el mensaje. Notese que podemos pasar hasta 3 argumentos en el mensaje. "arg1","arg2","obj". Usamos los 3
            //a modo de ejemplo pero principalmente el obj es el que usamos al obtener la respuesta desde el httpManager.
            Message mensaje = new Message();

            if (this.esUnaImagen == true)
            {
                mensaje.obj = http_manager.getImage(url_a_consultar);
                mensaje.arg1 = HuboTransmision_de_Imagen;
                mensaje.arg2 = this.posicionQueCambio;
                Log.d("----------","------------------------------");
                Log.d("Rta:","Imagen obteniendose");
                Log.d("----------","------------------------------");

                //Una vez finalizado y obtenido un resultado del GetResponse, envio el mensaje generado a la cola de mensajes mediante el Handler.
                manejador_solicitud.sendMessage(mensaje);
            }
            else
            {
                String cryptosJsonText = http_manager.getResponse(url_a_consultar);
                mensaje.arg1 = HuboTransmision_de_RespuestaDeTexto;
                mensaje.arg2 = 1; //Mando 1 a modo de ejemplo. Pero no lo uso para nada
                Log.d("----------","------------------------------");
                Log.d("Rta:","Texto obteniendose");
                Log.d("----------","------------------------------");
                Log.d("Rta:",cryptosJsonText);
                Log.d("----------","------------------------------");

                //Parseamos a JSON la respuesta recibida
                mensaje.obj = ParserJSON.parsearJSON(cryptosJsonText,true);

                //Una vez finalizado y obtenido un resultado del GetResponse, envio el mensaje generado a la cola de mensajes mediante el Handler.
                manejador_solicitud.sendMessage(mensaje);
            }
        }
        catch (IOException error) {error.printStackTrace();}
    }
}

