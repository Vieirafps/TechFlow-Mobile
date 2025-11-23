package com.techflowsupport.ui.chamados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.techflowsupport.R;
import com.techflowsupport.data.fake.FakeDatabase;
import com.techflowsupport.data.model.Chamado;

import java.util.List;

public class DetalhesChamadoActivity extends AppCompatActivity {

    TextView txtTitulo, txtDescricao, txtCategoria, txtPrioridade, txtData;
    Spinner spStatus;
    Button btnSalvar;

    int chamadoIndex;
    List<Chamado> listaChamados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_chamado);

        iniciarComponentes();

        chamadoIndex = getIntent().getIntExtra("index", -1);

        listaChamados = FakeDatabase.getChamados(this);

        Chamado chamado = listaChamados.get(chamadoIndex);

        preencherDados(chamado);
        configurarSpinner(chamado.getStatus());

        btnSalvar.setOnClickListener(v -> {
            String novoStatus = spStatus.getSelectedItem().toString();
            listaChamados.get(chamadoIndex).setStatus(novoStatus);

            FakeDatabase.salvarLista(this, listaChamados);

            finish();
        });
    }

    private void iniciarComponentes() {
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtPrioridade = findViewById(R.id.txtPrioridade);
        txtData = findViewById(R.id.txtData);
        spStatus = findViewById(R.id.spStatus);
        btnSalvar = findViewById(R.id.btnSalvarStatus);
    }

    private void preencherDados(Chamado c) {
        txtTitulo.setText(c.getTitulo());
        txtDescricao.setText(c.getDescricao());
        txtCategoria.setText(c.getCategoria());
        txtPrioridade.setText(c.getPrioridade());
        txtData.setText(c.getDataAbertura());
    }

    private void configurarSpinner(String statusAtual) {
        String[] statusList = {"Aberto", "Em andamento", "Resolvido"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                statusList
        );

        spStatus.setAdapter(adapter);

        for (int i = 0; i < statusList.length; i++) {
            if (statusList[i].equalsIgnoreCase(statusAtual)) {
                spStatus.setSelection(i);
                break;
            }
        }
    }
}
