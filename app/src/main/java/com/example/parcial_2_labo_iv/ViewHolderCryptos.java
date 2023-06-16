package com.example.parcial_2_labo_iv;

import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderCryptos extends RecyclerView.ViewHolder implements View.OnClickListener
{
    TextView nombre;
    TextView cotizacion_usd;
    TextView cotizacion_ars;
    TextView cotizacion_eur;
    TextView ultima_actualizacion;
    ImageView imagen_crypto;
    MainActivity mainAct;

    public ViewHolderCryptos(@NonNull View viewRecibida, MainActivity mainActRecibida)
    {
        super(viewRecibida);
        this.mainAct = mainActRecibida;
        this.nombre = viewRecibida.findViewById(R.id.tvNombre);
        this.cotizacion_usd = viewRecibida.findViewById(R.id.tvUSD);
        this.cotizacion_ars = viewRecibida.findViewById(R.id.tvARS);
        this.cotizacion_eur = viewRecibida.findViewById(R.id.tvEUR);
        this.ultima_actualizacion = viewRecibida.findViewById(R.id.tvUltimaActualizacion);
        this.imagen_crypto = viewRecibida.findViewById(R.id.imgCrypto);
        viewRecibida.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Log.d("Se hizo click","En la crypto: "+super.getAdapterPosition());
        //posicionElemSiendoLlamado = super.getAdapterPosition();
        //mainAct.abrirFormularioEdicionPersona(posicionElemSiendoLlamado);
    }
}