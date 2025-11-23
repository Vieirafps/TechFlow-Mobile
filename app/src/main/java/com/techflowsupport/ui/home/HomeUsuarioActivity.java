package com.techflowsupport.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.techflowsupport.R;
import com.techflowsupport.ui.chamados.MeusChamadosActivity;
import com.techflowsupport.ui.chamados.NovoChamadoActivity;
import com.techflowsupport.ui.faq.FaqActivity;
import com.techflowsupport.ui.perfil.PerfilActivity;

public class HomeUsuarioActivity extends AppCompatActivity {

    private Button btnAbrirChamado, btnMeusChamados, btnFaq, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);

        iniciarComponentes();
        configurarEventos();
    }

    private void iniciarComponentes() {
        btnAbrirChamado   = findViewById(R.id.btnAbrirChamado);
        btnMeusChamados   = findViewById(R.id.btnMeusChamados);
        btnFaq            = findViewById(R.id.btnFaq);
        btnPerfil         = findViewById(R.id.btnPerfilUsuario);
    }

    private void configurarEventos() {

        btnAbrirChamado.setOnClickListener(v ->
                startActivity(new Intent(this, NovoChamadoActivity.class))
        );

        btnMeusChamados.setOnClickListener(v ->
                startActivity(new Intent(this, MeusChamadosActivity.class))
        );

        btnFaq.setOnClickListener(v ->
                startActivity(new Intent(this, FaqActivity.class))
        );

        btnPerfil.setOnClickListener(v ->
                startActivity(new Intent(this, PerfilActivity.class))
        );
    }
}