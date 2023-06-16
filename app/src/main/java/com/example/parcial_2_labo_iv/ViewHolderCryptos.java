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
    TextView cotiazacion_usd;
    TextView cotiazacion_ars;
    TextView cotiazacion_eur;
    TextView ultima_actualizacion;
    ImageView imagen_crypto;
    MainActivity mainAct;

    public ViewHolderCryptos(@NonNull View viewRecibida, MainActivity mainActRecibida)
    {
        super(viewRecibida);
        this.mainAct = mainActRecibida;
        this.nombre = viewRecibida.findViewById(R.id.tvNombre);
        this.cotiazacion_usd = viewRecibida.findViewById(R.id.tvUSD);
        this.cotiazacion_ars = viewRecibida.findViewById(R.id.tvARS);
        this.cotiazacion_eur = viewRecibida.findViewById(R.id.tvEUR);
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