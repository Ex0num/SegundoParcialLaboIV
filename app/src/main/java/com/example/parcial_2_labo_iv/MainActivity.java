package com.example.parcial_2_labo_iv;

import com.example.parcial_2_labo_iv.databinding.ActivityMainBinding;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements Handler.Callback, SearchView.OnQueryTextListener
{
    private ActivityMainBinding binding;
    private AdapterCryptos adapterCryptos;
    public Handler manejador;
    public static List<CryptoModel> cryptos = new ArrayList<>(); //Son las filtradas
    public static List<CryptoModel> cryptosAuxList = new ArrayList<>(); //Son las originales

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        //El manejador es el que responsable de procesar los callbacks. THIS.
        //El manejador lo vamos a pasar a todos los hilos que van a escribir mensajes
        this.manejador = new Handler(this);

        //Debemos crear un hilo para que la solicitud pueda ser realizada. Es decir,
        //Obligatoriamente una solicitud HTTP tiene que ser realizada de forma asincrona.
        Thread hilo_traer_cryptos = new Thread(new RealizadorConsultasHttp("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin%2Cethereum%2Ccardano%2Ctether%2Cripple%2Cdogecoin%2Cmonero&vs_currencies=usd%2Cars%2Ceur&include_last_updated_at=true&precision=2", this.manejador, false));
        hilo_traer_cryptos.start();

        //Inicializo y creo el adapter y le seteo al ReciclerView su correspondiente adapter con su elemento Layout
        this.adapterCryptos = new AdapterCryptos(cryptos, this, this.manejador);

        //Configuramos el reciclerView (asigno Adapter,y LayoutManager)
        RecyclerView rvCryptos = super.findViewById(R.id.reciclerView_cryptos);
        rvCryptos.setAdapter(this.adapterCryptos);
        rvCryptos.setLayoutManager(new LinearLayoutManager(this));
    }

    //Es el encargado de verificar por cada vuelta del Thread de la grafica si hay algun mensaje
    //en espera a ser procesado por algun otro hilo. //De ahi el implements "Handler.Callback"
    @Override
    public boolean handleMessage(@NonNull Message mensaje_recibido)
    {
        //Asi puedo usar arg1 y arg2. Por ej. Saber si el mensaje fue debido a una imagen o a un texto. (arg1) o
        //saber la posicion del elemento que fue actualizado sease la data o la imagen (arg2)
        if (mensaje_recibido.arg1 == RealizadorConsultasHttp.HuboTransmision_de_Imagen)
        {
            //De los bytes de la imagen que ya lei y recibi del mensaje, los seteo a la imagen.
            Log.d("Respuesta", "Subiendo img traida");
            byte[] bytes_de_la_imagen = (byte[]) mensaje_recibido.obj;
            adapterCryptos.cryptos.get(mensaje_recibido.arg2).setBytesImagen(bytes_de_la_imagen);
            adapterCryptos.notifyItemChanged(mensaje_recibido.arg2);
        }
        else if (mensaje_recibido.arg1 == RealizadorConsultasHttp.HuboTransmision_de_RespuestaDeTexto)
        {
            //De los bytes del objeto que ya lei y recibi del mensaje, los seteo a la imagen.
            Log.d("Respuesta", "Subiendo obj traido");
            adapterCryptos.cryptos = (List<CryptoModel>) mensaje_recibido.obj;
            adapterCryptos.notifyDataSetChanged();
        }

        return false;
    }

    //------------------- METODOS QUE SE ENCARGAN DEL MENU ------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //Despues de que se creen las opciones de menu, recupero el itemDeMenu Find y le seteo el listener que le corresponde
        MenuItem mi = menu.findItem(R.id.buscador);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //----------------------------------------------------------------------

    //----------- METODOS QUE SE ENCARGAN DEL SEARCHVIEW -------------------
    @Override
    public boolean onQueryTextSubmit(String queryRecibida) {return false;}

    @Override
    public boolean onQueryTextChange(String queryRecibida)
    {
        Log.d("------------------------------------------------------------------------------","----------------------------------------------");
        Log.d("ENTONCES QUEDO LA ORIGINAL STATIC",MainActivity.cryptosAuxList.toString());
        Log.d("ENTONCES QUEDO LA SHOWED 2 ",adapterCryptos.cryptos.toString());

        List<CryptoModel> listaFiltraciones = new ArrayList<>();
        listaFiltraciones.clear();

        for (CryptoModel cryptoActual : MainActivity.cryptosAuxList)
        {
            if(cryptoActual.getNombre().toLowerCase().contains(queryRecibida.toLowerCase()))
            {
                listaFiltraciones.add(cryptoActual);
            }
        }

        Log.d("Lista ya filtrada",listaFiltraciones.toString());
        adapterCryptos.cryptos.clear();
        adapterCryptos.cryptos.addAll(listaFiltraciones);

        Log.d("Lista ya filtrada showed",adapterCryptos.cryptos.toString());
        adapterCryptos.notifyDataSetChanged();

        return false;
    }
    //------------------------------------------------------------------------
}