package com.techflowsupport.ui.faq;

public class PerguntaFaq {
    private String pergunta;
    private String resposta;

    public PerguntaFaq(String pergunta, String resposta) {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }
}
