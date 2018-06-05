package com.example.angelo.trabalhop2;

public class Treino {

    private long id;
    private String exercico;
    private int repeticao, carga, intervalo;
    private Usuario usuario;

    public Treino(long id, String exercico, int repeticao, int carga, int intervalo) {
        this.id = id;
        this.exercico = exercico;
        this.repeticao = repeticao;
        this.carga = carga;
        this.intervalo = intervalo;

    }

    public Treino(String exercico, int repeticao, int carga, int intervalo) {
        this.exercico = exercico;
        this.repeticao = repeticao;
        this.carga = carga;
        this.intervalo = intervalo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExercico() {
        return exercico;
    }

    public void setExercico(String exercico) {
        this.exercico = exercico;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return exercico + "\n" + repeticao + "\n" + carga + "\n" + intervalo;
    }
}
