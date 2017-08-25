package br.com.alura.agenda.sinc;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import br.com.alura.agenda.ListaAlunosActivity;
import br.com.alura.agenda.RetrofitInializador;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.dto.AlunosSync;
import br.com.alura.agenda.event.AtualizaListaAlunoEvent;
import br.com.alura.agenda.preferences.AlunoPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoSincronizador {
    private final Context context;
    private EventBus bus = EventBus.getDefault();
    private AlunoPreferences preferences;

    public AlunoSincronizador(Context context) {
        this.context = context;
        preferences = new AlunoPreferences(context);
    }

    public void buscaAlunos() {
        Call<AlunosSync> call = new RetrofitInializador().getAlunoService().lista();
        call.enqueue(new Callback<AlunosSync>() {
            @Override
            public void onResponse(Call<AlunosSync> call, Response<AlunosSync> response) {
                AlunosSync alunos = response.body();
                String versao = alunos.getMomentoDaUltimaModificacao();
                if(preferences.temVersao()){
                    buscaNovos();
                }
                preferences.salvarVersao(versao);

                AlunoDAO alunoDAO = new AlunoDAO(context);
                alunoDAO.sincroniza(alunos.getAlunos());
                alunoDAO.close();

                Log.i("versao", preferences.getVersao());
                bus.post(new AtualizaListaAlunoEvent());
            }

            @Override
            public void onFailure(Call<AlunosSync> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
                bus.post(new AtualizaListaAlunoEvent());
            }
        });
    }

    private void buscaNovos() {
        new RetrofitInializador().getAlunoService().novos();
    }
}