package com.example.parcial_2_labo_iv;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import androidx.annotation.NonNull;

import java.util.Date;

public class CryptoModel
{
    String nombre; //"bitcoin"
    Float cotizacionUSD; //25660.61
    Float cotizacionARS; //6376574.35
    Float cotizacionEUR; //23449.87
    String ultima_vez_actualizado; //1686861182
    String linkHttpsIcono; //https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579
    Boolean estaBuscandoImagen;
    byte[] bytesImagen;

    CryptoModel () {}

    CryptoModel (String nombreRecibido, Float cotizacionUSDRecibida, Float cotizacionARSRecibida, Float cotizacionEURRecibida, String ultima_vez_actualizadoRecibido, String linkHttpsIconoRecibido)
    {
        this.setNombre(nombreRecibido);
        this.setcotizacionUSD(cotizacionUSDRecibida);
        this.setcotizacionARS(cotizacionARSRecibida);
        this.setCotizacionEUR(cotizacionEURRecibida);
        this.setLinkIcono(linkHttpsIconoRecibido);
        this.setEstaBuscandoImagen(false);

        long timestamp = Long.parseLong(ultima_vez_actualizadoRecibido);
        Date fechaHora = new Date(timestamp * 1000L); // Multiplicar por 1000 para convertir segundos a milisegundos

        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        formatoFechaHora.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires")); // Especificar la zona horaria de Buenos Aires

        String fechaHoraLocal = formatoFechaHora.format(fechaHora);
        this.setUltimaVezActualizacion(fechaHoraLocal);
    }

    public String getNombre() {return this.nombre;}
    public void setNombre(String nombreRecibido) {this.nombre = nombreRecibido;}

    public Float getcotizacionUSD() {return this.cotizacionUSD;}
    public void setcotizacionUSD(Float cotizacionUSDRecibida) {this.cotizacionUSD = cotizacionUSDRecibida;}

    public Float getcotizacionARS() {return this.cotizacionARS;}
    public void setcotizacionARS(Float cotizacionARSRecibida) {this.cotizacionARS = cotizacionARSRecibida;}

    public Float getCotizacionEUR() {return this.cotizacionEUR;}
    public void setCotizacionEUR(Float cotizacionEURRecibida) {this.cotizacionEUR = cotizacionEURRecibida;}

    public String getUltimaVezActualizacion() {return this.ultima_vez_actualizado;}
    public void setUltimaVezActualizacion(String ultima_vez_actualizadoRecibido) {this.ultima_vez_actualizado = ultima_vez_actualizadoRecibido;}

    public String getLinkHttpsIcono() {return this.linkHttpsIcono;}
    public void setLinkIcono(String linkHttpsIconoRecibido) {this.linkHttpsIcono = linkHttpsIconoRecibido;}

    public Boolean getEstaBuscandoImagen() {return this.estaBuscandoImagen;}
    public void setEstaBuscandoImagen(Boolean estaBuscandoImagenRecibido) {this.estaBuscandoImagen = estaBuscandoImagenRecibido;}

    public byte[] getBytesImagen() {return this.bytesImagen;}
    public void setBytesImagen(byte[] bytesImagenRecibido) {this.bytesImagen = bytesImagenRecibido;}

    @NonNull
    @Override
    public String toString()
    {

        String objetoString =
                " Nombre: "+this.nombre+
                " USD: "+this.cotizacionUSD.toString()+
                " ARS: "+this.cotizacionARS.toString()+
                " EUR: "+this.cotizacionEUR.toString()+
                " UltimaVezActualizado: "+this.ultima_vez_actualizado;

        return objetoString;
    }
}