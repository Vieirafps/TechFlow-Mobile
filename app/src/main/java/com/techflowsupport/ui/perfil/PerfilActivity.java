package com.techflowsupport.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.techflowsupport.R;

public class PerfilActivity extends AppCompatActivity {

    TextView txtNome, txtEmail, txtTipo;
    Button btnEditar, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtTipo = findViewById(R.id.txtTipo);
        btnEditar = findViewById(R.id.btnEditar);
        btnSair = findViewById(R.id.btnSair);

        txtNome.setText("João Silva");
        txtEmail.setText("cliente@techflow.com");
        txtTipo.setText("Usuário");

        btnEditar.setOnClickListener(v ->
                startActivity(new Intent(this, EditarPerfilActivity.class)));

        btnSair.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}
