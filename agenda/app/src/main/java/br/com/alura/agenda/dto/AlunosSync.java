package br.com.alura.agenda.dto;

import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Walderi Moraes Willy Filho on 17/08/17.
 */

public class AlunosSync {
    private List<Aluno> alunos;
    private String momentoDaUltimaModificacao;


    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getMomentoDaUltimaModificacao() {
        return momentoDaUltimaModificacao;
    }

    public void setMomentoDaUltimaModificacao(String momentoDaUltimaModificacao) {
        this.momentoDaUltimaModificacao = momentoDaUltimaModificacao;
    }
}
