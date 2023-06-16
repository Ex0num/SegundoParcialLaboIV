package com.example.parcial_2_labo_iv;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager
{
    public String getResponse(String url_recibida) throws IOException
    {
        //Formateamos el string recibido a una verdadera URL.
        URL url = new URL(url_recibida);

        //Abrimos y creamos la conexion.
        HttpURLConnection conexion_url = (HttpURLConnection) url.openConnection();

        //Definimos la solicitud del metodo.
        conexion_url.setRequestMethod("GET");

        //Conectamos la url (se abre la comunicacion).
        conexion_url.connect();

        //Recibimos el codigo de estado (Status_code).
        int response_status = conexion_url.getResponseCode();

        if (response_status == 200) //El servidor respondio satisfactoriamente.
        {
            //Transportamos de la respuesta del servidor todos los bytes desde el inputStream al outputStream (cada 1024 bytes).
            InputStream input_stream = conexion_url.getInputStream();
            ByteArrayOutputStream byte_output_stream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;

            //En cada iteracion leo el InputStream cada 1024 bytes (Del buffer) y lo escribo en el OutputStream.
            //Mientras que los bytes leidos no lleguen a 0. Significa que hay mas por leer del InputStream.
            while ((length = input_stream.read(buffer)) != -1) {
                byte_output_stream.write(buffer, 0, length);
            }

            //Cerramos la conexion (Ya que si salgo del bucle es que ya toda la respuesta fue leida).
            input_stream.close();

            //Casteo a string el byteArray que me quedo formado.
            return new String (byte_output_stream.toByteArray());
        }
        else //El servidor no respondio satisfactoriamente.
        {
            throw  new RuntimeException("Error en el servidor");
        }
    }

    public byte[] getImage(String url_recibida) throws IOException
    {
        //Formateamos el string recibido a una verdadera URL.
        URL url = new URL(url_recibida);

        //Abrimos y creamos la conexion.
        HttpURLConnection conexion_url = (HttpURLConnection) url.openConnection();

        //Definimos la solicitud del metodo.
        conexion_url.setRequestMethod("GET");

        //Conectamos la url (se abre la comunicacion).
        conexion_url.connect();

        //Recibimos el codigo de estado (Status_code).
        int response_status = conexion_url.getResponseCode();

        if (response_status == 200) //El servidor respondio satisfactoriamente.
        {
            Log.d("-------------","-------------");
            Log.d("--------","Obteniendo STATUS");
            Log.d("-------------","-------------");

            //Transportamos de la respuesta del servidor todos los bytes desde el inputStream al outputStream (cada 1024 bytes).
            InputStream input_stream = conexion_url.getInputStream();
            ByteArrayOutputStream byte_output_stream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;

            //En cada iteracion leo el InputStream cada 1024 bytes (Del buffer) y lo escribo en el OutputStream.
            //Mientras que los bytes leidos no lleguen a 0. Significa que hay mas por leer del InputStream.
            while((length = input_stream.read(buffer)) != -1)
            {
                byte_output_stream.write(buffer,0,length);
            }

            //Cerramos la conexion (Ya que si salgo del bucle es que ya toda la respuesta fue leida).
            input_stream.close();

            //Devuelvo el byteArray directamente que contiene los bytes de la imagen.
            return byte_output_stream.toByteArray();
        }
        else //El servidor no respondio satisfactoriamente.
        {
            throw  new RuntimeException("Error en el servidor");
        }
    }
}