package com.carlesramos.infocovid.interficies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Juan Carlos Ramos.
 * Interfaz para el framework Retrofit
 */
public interface IApiInterface {

    /**
     * LLama al metodo de comprobar si el nickName existe.
     * @param nickName
     * @return si existe o no.
     */
    @GET("ApiJuegoDef/rest/inicio/nickName")
    Call<String> nickNameExists(@Query("nickName") String nickName);

    /**
     * Devuelve la jugada de la CPU
     * @return Un objeto de la clase JugadaCpu
     */
}
