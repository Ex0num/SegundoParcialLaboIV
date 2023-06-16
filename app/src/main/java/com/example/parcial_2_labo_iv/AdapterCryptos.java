package com.example.parcial_2_labo_iv;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterCryptos extends RecyclerView.Adapter<ViewHolderCryptos>
{
    public List<CryptoModel> cryptos; //Es la lista de cryptos MOSTRADA. (Se usa para mostrar siempre las cryptos)
    private MainActivity mainAct;
    private Handler manejador;

    public  AdapterCryptos(List<CryptoModel> listaCryptosRecibida,MainActivity mainActRecibida, Handler manejadorRecibido)
    {
        this.cryptos = listaCryptosRecibida;
        this.mainAct = mainActRecibida;
        this.manejador = manejadorRecibido;
    }

    @NonNull
    @Override
    public ViewHolderCryptos onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crypto_item,parent,false);
        ViewHolderCryptos viewHolder = new ViewHolderCryptos(view,this.mainAct);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCryptos holder, int posicionRecibida) {
        CryptoModel crypto = this.cryptos.get(posicionRecibida);

        TextView nombre = holder.itemView.findViewById(R.id.tvNombre);
        nombre.setText(crypto.getNombre());

        TextView cotizacion_usd = holder.itemView.findViewById(R.id.tvUSD);
        cotizacion_usd.setText(crypto.getcotizacionUSD().toString());

        TextView cotizacion_ars = holder.itemView.findViewById(R.id.tvARS);
        cotizacion_ars.setText(crypto.getcotizacionARS().toString());

        TextView cotizacion_eur = holder.itemView.findViewById(R.id.tvEUR);
        cotizacion_eur.setText(crypto.getCotizacionEUR().toString());

        TextView ultima_vez_actualizacion = holder.itemView.findViewById(R.id.tvUltimaActualizacion);
        ultima_vez_actualizacion.setText(crypto.getUltimaVezActualizacion());

        if (crypto.getBytesImagen() == null && crypto.estaBuscandoImagen == false)
        {
            crypto.estaBuscandoImagen = true;

            //Tengo que ir a buscar las imagenes de la crypto.
            Thread hilo_buscarImagen = new Thread(new RealizadorConsultasHttp(crypto.getLinkHttpsIcono(), this.manejador,true, posicionRecibida));
            hilo_buscarImagen.start();
        }
        else
        {
            holder.imagen_crypto.setImageBitmap(BitmapFactory.decodeByteArray(crypto.getBytesImagen(),0, crypto.getBytesImagen().length));
        }
    }

    @Override
    public int getItemCount() {return this.cryptos.size();}
}
