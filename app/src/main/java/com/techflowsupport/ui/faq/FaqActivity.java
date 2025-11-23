package com.techflowsupport.ui.faq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;



import com.techflowsupport.R;
import com.techflowsupport.data.IA.OpenAIClient;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    private EditText edtBuscar;
    private Button btnPerguntarIA;
    private LinearLayout blocoIA;
    private TextView txtRespostaIA;
    private LinearLayout listaPerguntas;

    private List<PerguntaFaq> perguntasFixas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        edtBuscar = findViewById(R.id.edtBuscar);
        btnPerguntarIA = findViewById(R.id.btnPerguntarIA);
        blocoIA = findViewById(R.id.blocoIA);
        txtRespostaIA = findViewById(R.id.txtRespostaIA);
        listaPerguntas = findViewById(R.id.listaPerguntas);

        carregarPerguntasFixas();

        adicionarPerguntasNaTela();

        OpenAIClient ia = new OpenAIClient(com.techflowsupport.BuildConfig.OPENAI_API_KEY);


        btnPerguntarIA.setOnClickListener(v -> {
            String pergunta = edtBuscar.getText().toString().trim();

            if (pergunta.isEmpty()) {
                edtBuscar.setError("Digite uma dúvida!");
                return;
            }

            blocoIA.setVisibility(View.VISIBLE);
            txtRespostaIA.setText("Consultando IA...");

            ia.perguntarIA(pergunta, new OpenAIClient.IAListener() {
                @Override
                public void onSuccess(String resposta) {
                    runOnUiThread(() -> txtRespostaIA.setText(resposta));
                }

                @Override
                public void onError(String erro) {
                    runOnUiThread(() -> txtRespostaIA.setText("Erro: " + erro));
                }
            });
        });
    }

    private void carregarPerguntasFixas() {
        perguntasFixas = new ArrayList<>();

        perguntasFixas.add(new PerguntaFaq(
                "Como resolver problema de internet?",
                "1. Verifique os cabos.\n2. Reinicie o modem.\n3. Teste outro aparelho.\n4. Se persistir, abra um chamado."
        ));

        perguntasFixas.add(new PerguntaFaq(
                "A impressora não está imprimindo. O que fazer?",
                "1. Verifique conexão USB ou rede.\n2. Reinicie a impressora.\n3. Verifique papel e toner.\n4. Teste impressão de outro computador."
        ));

        perguntasFixas.add(new PerguntaFaq(
                "O computador está lento. Como otimizar?",
                "1. Feche programas desnecessários.\n2. Verifique antivírus.\n3. Limpe arquivos temporários.\n4. Reinicie o computador.\n5. Se persistir, solicite assistência/técnico."
        ));

        perguntasFixas.add(new PerguntaFaq(
                "O e-mail não sincroniza. O que devo fazer?",
                "1. Verifique conexão com internet.\n2. Confira usuário e senha.\n3. Atualize configurações do app de e-mail.\n4. Reinicie o celular ou PC."
        ));
    }

    private void adicionarPerguntasNaTela() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (PerguntaFaq p : perguntasFixas) {

            View card = inflater.inflate(R.layout.item_pergunta, listaPerguntas, false);

            TextView txtTitulo = card.findViewById(R.id.txtTituloPergunta);
            TextView txtResposta = card.findViewById(R.id.txtRespostaPergunta);

            txtTitulo.setText(p.getPergunta());
            txtResposta.setText(p.getResposta());

            txtResposta.setVisibility(View.GONE);

            card.setOnClickListener(v -> {
                if (txtResposta.getVisibility() == View.GONE) {
                    txtResposta.setVisibility(View.VISIBLE);
                } else {
                    txtResposta.setVisibility(View.GONE);
                }
            });

            listaPerguntas.addView(card);
        }
    }
}
