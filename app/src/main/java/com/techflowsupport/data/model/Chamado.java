package com.techflowsupport.data.model;

public class Chamado {

    private String titulo;
    private String descricao;
    private String categoria;
    private String prioridade;
    private String status;
    private String dataAbertura;

    public Chamado(String titulo, String descricao, String categoria, String prioridade, String status, String dataAbertura) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.status = status;
        this.dataAbertura = dataAbertura;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getCategoria() { return categoria; }
    public String getPrioridade() { return prioridade; }
    public String getStatus() { return status; }
    public String getDataAbertura() { return dataAbertura; }
}
