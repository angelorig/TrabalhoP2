package com.example.angelo.trabalhop2;

public class Chat {

    private long id;
    private String mensagem;
    private Usuario remetente, destinatario;

    public Chat(long id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    public Chat(String mensagem) {
        this.mensagem = mensagem;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
}
