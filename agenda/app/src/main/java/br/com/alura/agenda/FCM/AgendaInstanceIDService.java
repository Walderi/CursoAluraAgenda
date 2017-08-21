package br.com.alura.agenda.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import br.com.alura.agenda.RetrofitInializador;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Walderi Moraes Willy FIlho on 18/08/17.
 */

public class AgendaInstanceIDService extends FirebaseInstanceIdService {

    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "REFRESHED TOKEN!!!!!!!!!!!: " + refreshedToken);

        enviaTokenParaservidor(refreshedToken);
    }

    private void enviaTokenParaservidor(final String refreshedToken) {

        Call<Void> call = new RetrofitInializador().getDispositivoService().enviaToken(refreshedToken);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("token enviado", refreshedToken);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("token falhou", t.getMessage());
            }
        });

    }
}
