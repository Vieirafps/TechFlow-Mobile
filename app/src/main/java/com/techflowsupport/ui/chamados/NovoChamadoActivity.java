package com.techflowsupport.ui.chamados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.techflowsupport.R;
import com.techflowsupport.data.fake.FakeDatabase;
import com.techflowsupport.data.model.Chamado;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NovoChamadoActivity extends AppCompatActivity {

    private EditText edtTitulo, edtDescricao;
    private Spinner spCategoria, spPrioridade;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);

        iniciarComponentes();
        configurarSpinners();
        configurarEventos();
    }

    private void iniciarComponentes() {
        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescricao = findViewById(R.id.edtDescricao);
        spCategoria = findViewById(R.id.spCategoria);
        spPrioridade = findViewById(R.id.spPrioridade);
        btnSalvar = findViewById(R.id.btnSalvar);
    }

    private void configurarSpinners() {
        String[] categorias = {"Rede", "Impressora", "Computador", "Servidor", "Outros"};
        String[] prioridades = {"Baixa", "Média", "Alta", "Crítica"};

        spCategoria.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categorias));

        spPrioridade.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, prioridades));
    }

    private void configurarEventos() {

        btnSalvar.setOnClickListener(v -> {

            String titulo = edtTitulo.getText().toString().trim();
            String descricao = edtDescricao.getText().toString().trim();
            String categoria = spCategoria.getSelectedItem().toString();
            String prioridade = spPrioridade.getSelectedItem().toString();

            if (titulo.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            String dataAtual = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .format(new Date());

            Chamado chamado = new Chamado(
                    titulo,
                    descricao,
                    categoria,
                    prioridade,
                    "Aberto",
                    dataAtual
            );

            FakeDatabase.salvarChamado(this, chamado);

            Toast.makeText(this, "Chamado criado com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
