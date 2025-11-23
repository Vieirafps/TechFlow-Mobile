package com.techflowsupport.data.model;

public class Message {
    private String texto;
    private boolean isUser;

    public Message(String texto, boolean isUser) {
        this.texto = texto;
        this.isUser = isUser;
    }

    public String getTexto() { return texto; }
    public boolean isUser() { return isUser; }
}
