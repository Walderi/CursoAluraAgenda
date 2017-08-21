package br.com.alura.agenda.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by Walderi Moraes Willy FIlho on 18/08/17.
 */

public class AgendaInstanceIDService extends FirebaseInstanceIdService {

    public void OnTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "REFRESHED TOKEN!!!!!!!!!!!: " + refreshedToken);

        enviaTokenParaservidor(refreshedToken);
    }

    private void enviaTokenParaservidor(String refreshedToken) {
    }
}
