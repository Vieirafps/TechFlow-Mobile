package com.techflowsupport.ui.chamados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.techflowsupport.R;
import com.techflowsupport.data.fake.FakeDatabase;
import com.techflowsupport.data.model.Chamado;

import java.util.List;

public class MeusChamadosActivity extends AppCompatActivity {

    RecyclerView recyclerChamados;
    ChamadosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_chamados);

        recyclerChamados = findViewById(R.id.recyclerChamados);
        recyclerChamados.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Chamado> lista = FakeDatabase.getChamados(this);

        if (lista.isEmpty()) {
            Toast.makeText(this, "Nenhum chamado encontrado!", Toast.LENGTH_SHORT).show();
        }

        adapter = new ChamadosAdapter(this, lista);
        recyclerChamados.setAdapter(adapter);
    }
}
