package br.com.alura.agenda.services;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Walderi Moraes Willy Filho on 21/08/17.
 */

public interface DispositivoService {
    @POST("firebase/dispositivo")
    Call<Void> enviaToken(@Header("token") String token);
}
