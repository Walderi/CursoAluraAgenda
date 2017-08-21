package br.com.alura.agenda.services;


import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.dto.AlunosSync;
import br.com.alura.agenda.event.AtualizaListaAlunoEvent;

public class AgendaMessageService extends FirebaseMessagingService{
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
        Map<String,String> message = remoteMessage.getData();
        Log.i("mensagem recebida", String.valueOf(message));
        converteParaAluno(message);
    }
    public void converteParaAluno(Map<String,String> messagem){
        String chaveDeAcesso = "alunoSync";
        if(messagem.containsKey(chaveDeAcesso)){
            String json = messagem.get(chaveDeAcesso);
            ObjectMapper mapper = new ObjectMapper();
            try {
                AlunosSync alunosSync = mapper.readValue(json, AlunosSync.class);
                AlunoDAO alunoDAO = new AlunoDAO(this);
                alunoDAO.sincroniza(alunosSync.getAlunos());
                alunoDAO.close();
                EventBus eventBus = EventBus.getDefault();
                eventBus.post(new AtualizaListaAlunoEvent());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

