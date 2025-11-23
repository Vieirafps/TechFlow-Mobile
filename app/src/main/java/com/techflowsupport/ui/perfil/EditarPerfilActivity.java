package com.techflowsupport.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techflowsupport.R;

public class EditarPerfilActivity extends AppCompatActivity {

    EditText edtNome, edtEmail;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        btnSalvar = findViewById(R.id.btnSalvar);

        edtNome.setText("João Silva");
        edtEmail.setText("cliente@techflow.com");

        btnSalvar.setOnClickListener(v -> {
            Toast.makeText(this, "Dados atualizados!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
