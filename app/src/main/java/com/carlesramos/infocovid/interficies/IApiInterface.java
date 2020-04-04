package com.carlesramos.infocovid.interficies;

import com.carlesramos.infocovid.model.AllCountriesInfo;
import com.carlesramos.infocovid.model.CountryInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Juan Carlos Ramos.
 * Interfaz para el framework Retrofit
 */
public interface IApiInterface {

    @GET("/all")
    Call<AllCountriesInfo> getGlobalInfo();

    /**
     * Obtiene la informacion de un pais por el nombre
     * @param countryName
     * @return Informació del pais en concret
     */
    @GET("countries/{countryName}")
    Call<CountryInfo> getCountryByName(@Path("countryName") String countryName);

    @GET("countries")
    Call<ArrayList<CountryInfo>> getAllCountries();
}
