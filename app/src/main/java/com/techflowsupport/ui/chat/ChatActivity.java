package com.techflowsupport.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.techflowsupport.R;
import com.techflowsupport.data.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerChat;
    private EditText edtMensagem;
    private Button btnEnviar;

    private List<Message> mensagens = new ArrayList<>();
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerChat = findViewById(R.id.recyclerChat);
        edtMensagem = findViewById(R.id.edtMensagem);
        btnEnviar = findViewById(R.id.btnEnviar);

        adapter = new ChatAdapter(this, mensagens);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> enviar());
    }

    private void enviar() {
        String texto = edtMensagem.getText().toString().trim();

        if (texto.isEmpty()) return;

        mensagens.add(new Message(texto, true));
        adapter.notifyItemInserted(mensagens.size() - 1);
        recyclerChat.scrollToPosition(mensagens.size() - 1);

        edtMensagem.setText("");

        // RESPOSTA FICTÍCIA DO TÉCNICO
        recyclerChat.postDelayed(() -> {
            mensagens.add(new Message("Recebido! Estou analisando.", false));
            adapter.notifyItemInserted(mensagens.size() - 1);
            recyclerChat.scrollToPosition(mensagens.size() - 1);
        }, 700);
    }
}
