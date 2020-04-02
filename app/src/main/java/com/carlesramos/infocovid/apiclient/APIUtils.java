package com.carlesramos.infocovid.apiclient;


import com.carlesramos.infocovid.interficies.IApiInterface;

/**
 * @author Juan Carlos Ramos
 * Clase encargada de crear una instancia de la interfaz de Retrofit
 */
public class APIUtils {

    private APIUtils(){

    }

    public static final String BASE_URL = "http://192.168.1.111:8080";
    public static IApiInterface getIApiInterface(){
        return ApiRestClient.getInstance().getRetrofit().create(IApiInterface.class);
    }


}