package com.techflowsupport.ui.tecnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techflowsupport.R;
import com.techflowsupport.ui.chamados.MeusChamadosActivity;
import com.techflowsupport.ui.faq.FaqActivity;
import com.techflowsupport.ui.perfil.PerfilActivity;
import com.techflowsupport.data.fake.FakeDatabase;
import com.techflowsupport.data.model.Chamado;

import java.util.List;


public class DashboardTecnicoActivity extends AppCompatActivity {

    Button btnPendentes, btnPerfilTecnico, btnFaqTecnico;

    LinearLayout cardPendentes, cardConcluidos;
    TextView txtQtdPendentes, txtQtdConcluidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_tecnico);


        btnPendentes = findViewById(R.id.btnPendentes);
        btnFaqTecnico = findViewById(R.id.btnFaqTecnico);
        btnPerfilTecnico = findViewById(R.id.btnPerfilTecnico);


        txtQtdPendentes = findViewById(R.id.txtQtdPendentes);
        txtQtdConcluidos = findViewById(R.id.txtQtdConcluidos);


        btnPendentes.setOnClickListener(v -> {
            Intent i = new Intent(this, MeusChamadosActivity.class);
            i.putExtra("filtro", "pendente");
            startActivity(i);
        });


        btnFaqTecnico.setOnClickListener(v ->
                startActivity(new Intent(this, FaqActivity.class))
        );


        btnPerfilTecnico.setOnClickListener(v ->
                startActivity(new Intent(this, PerfilActivity.class))
        );
    }

    protected void onResume() {
        super.onResume();


        txtQtdPendentes = findViewById(R.id.txtQtdPendentes);
        txtQtdConcluidos = findViewById(R.id.txtQtdConcluidos);


        List<Chamado> lista = FakeDatabase.getChamados(this);

        int pendentes = 0;
        int concluidos = 0;

        for (Chamado c : lista) {

            String status = c.getStatus().toLowerCase();


            if (status.equals("aberto") ||
                    status.equals("pendente") ||
                    status.equals("em atendimento") ||
                    status.equals("em andamento")) {

                pendentes++;
            }


            else if (status.equals("finalizado") ||
                    status.equals("resolvido") ||
                    status.equals("concluído") ||
                    status.equals("concluido")) {

                concluidos++;
            }
        }


        txtQtdPendentes.setText(String.format("%02d", pendentes));
        txtQtdConcluidos.setText(String.format("%02d", concluidos));
    }
}
