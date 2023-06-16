package com.example.parcial_2_labo_iv;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ParserJSON
{
    //Tenemos que saber si el objeto que recibimos es un objeto o una lista
    //Es decir si la respuesta de la api comienza con "{}" o con "[]"
    public static List<CryptoModel> parsearJSON(String strRecibido, Boolean empiezaConOBJ)
    {
        List<CryptoModel> lista_cryptoModels = new ArrayList<>();

        try
        {
            //if (empiezaConOBJ == false) //Comienza con []
            //{return new List() {};}

            if (empiezaConOBJ == true) //Comienza con {}
            {
                //El string recibido es un JSON
                JSONObject objJSON = new JSONObject(strRecibido);

                //Se obtiene cada uno de los objetos y se crea un modelo y se setean sus datos para finalmente agregar a todos a la lista

                //--------------- Se hardcodean los links de iconos ---------------//
                //https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579
                //https://assets.coingecko.com/coins/images/975/large/cardano.png?1547034860
                //https://assets.coingecko.com/coins/images/5/large/dogecoin.png?1547792256
                //https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880
                //https://assets.coingecko.com/coins/images/69/large/monero_logo.png?1547033729
                //https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1605778731
                //https://assets.coingecko.com/coins/images/325/large/Tether.png?1668148663
                //-------------------------------------------------------//

                JSONObject objBitcoin = objJSON.getJSONObject("bitcoin");
                CryptoModel crypto_bitcoin =
                        new CryptoModel(
                                "Bitcoin",
                                Float.parseFloat(objBitcoin.getString("usd")),
                                Float.parseFloat(objBitcoin.getString("ars")),
                                Float.parseFloat(objBitcoin.getString("eur")),
                                objBitcoin.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579");

                JSONObject objCardano = objJSON.getJSONObject("cardano");
                CryptoModel crypto_cardano =
                        new CryptoModel(
                                "Cardano",
                                Float.parseFloat(objCardano.getString("usd")),
                                Float.parseFloat(objCardano.getString("ars")),
                                Float.parseFloat(objCardano.getString("eur")),
                                objCardano.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/975/large/cardano.png?1547034860");
                ;

                JSONObject objDogecoin = objJSON.getJSONObject("dogecoin");
                CryptoModel crypto_dogecoin =
                        new CryptoModel(
                                "Dogecoin",
                                Float.parseFloat(objDogecoin.getString("usd")),
                                Float.parseFloat(objDogecoin.getString("ars")),
                                Float.parseFloat(objDogecoin.getString("eur")),
                                objDogecoin.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/5/large/dogecoin.png?1547792256");

                JSONObject objEthereum = objJSON.getJSONObject("ethereum");
                CryptoModel crypto_ethereum =
                        new CryptoModel(
                                "Ethereum",
                                Float.parseFloat(objEthereum.getString("usd")),
                                Float.parseFloat(objEthereum.getString("ars")),
                                Float.parseFloat(objEthereum.getString("eur")),
                                objEthereum.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880");

                JSONObject objMonero = objJSON.getJSONObject("monero");
                CryptoModel crypto_monero =
                        new CryptoModel(
                                "Monero",
                                Float.parseFloat(objMonero.getString("usd")),
                                Float.parseFloat(objMonero.getString("ars")),
                                Float.parseFloat(objMonero.getString("eur")),
                                objMonero.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/69/large/monero_logo.png?1547033729");

                JSONObject objRipple = objJSON.getJSONObject("ripple");
                CryptoModel crypto_ripple =
                        new CryptoModel(
                                "Ripple",
                                Float.parseFloat(objRipple.getString("usd")),
                                Float.parseFloat(objRipple.getString("ars")),
                                Float.parseFloat(objRipple.getString("eur")),
                                objRipple.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1605778731");

                JSONObject objTether = objJSON.getJSONObject("tether");
                CryptoModel crypto_tether =
                        new CryptoModel(
                                "Tether",
                                Float.parseFloat(objTether.getString("usd")),
                                Float.parseFloat(objTether.getString("ars")),
                                Float.parseFloat(objTether.getString("eur")),
                                objTether.getString("last_updated_at"),
                                "https://assets.coingecko.com/coins/images/325/large/Tether.png?1668148663");

                //Seteo en estado false a todos los "estaBuscandoImagen" para que el adapter sepa si tiene que lanzar un hilo o no para buscar la img
                //Al construirse se setea automaticamente

                lista_cryptoModels.add(crypto_bitcoin);
                lista_cryptoModels.add(crypto_cardano);
                lista_cryptoModels.add(crypto_dogecoin);
                lista_cryptoModels.add(crypto_ethereum);
                lista_cryptoModels.add(crypto_monero);
                lista_cryptoModels.add(crypto_ripple);
                lista_cryptoModels.add(crypto_tether);

                Log.d("----------------","----------------------------o---");
                Log.d("Lista de cryptos terminada de parsear",lista_cryptoModels.toString());
                MainActivity.cryptosAuxList.addAll(lista_cryptoModels); //IMPORTANTE!!! ME GUARDO LA LISTA AUXILIAR EN AUX
                Log.d("----------------","----------------------------o---");
            }
        }
        catch (Exception e) {e.printStackTrace();}

        return lista_cryptoModels;
    }
}
