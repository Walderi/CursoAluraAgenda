package br.com.alura.agenda.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.print.PrinterCapabilitiesInfo;

/**
 * Created by Walderi Moraes Willy Filho on 22/08/17.
 */

public class AlunoPreferences {
    private static final String VERSÃO_DO_DADO = "versão_do_dado";
    private static final String BR_COM_ALURA_AGENDA_PREFERENCES_ALUNO_PREFERENCES = "br.com.alura.agenda.preferences.AlunoPreferences";
    private Context context;



    public AlunoPreferences(Context context) {
        
    }

    public void salvarVersao(String versao) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(VERSÃO_DO_DADO, versao);
        editor.commit();
    }

    private SharedPreferences getSharedPreferences(){
        return context.getSharedPreferences(BR_COM_ALURA_AGENDA_PREFERENCES_ALUNO_PREFERENCES,context.MODE_PRIVATE);
    }

    public String getVersao() {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getString(VERSÃO_DO_DADO, "");
    }
}
