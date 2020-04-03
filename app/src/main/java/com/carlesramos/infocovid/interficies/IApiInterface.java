package com.carlesramos.infocovid.interficies;

import com.carlesramos.infocovid.model.CountryInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Juan Carlos Ramos.
 * Interfaz para el framework Retrofit
 */
public interface IApiInterface {

    /**
     * Obtiene la informacion de un pais por el nombre
     * @param countryName
     * @return Informació del pais en concret
     */
    @GET("countries/countryName")
    Call<CountryInfo> getCountryByName(@Query("countryName") String countryName);

    /**
     * Devuelve la jugada de la CPU
     * @return Un objeto de la clase JugadaCpu
     */
}
