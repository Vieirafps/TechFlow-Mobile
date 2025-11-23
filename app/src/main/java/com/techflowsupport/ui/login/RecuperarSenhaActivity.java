package com.techflowsupport.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techflowsupport.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    EditText edtEmailRecuperacao;
    Button btnEnviarRecuperacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        edtEmailRecuperacao = findViewById(R.id.edtEmailRecuperacao);
        btnEnviarRecuperacao = findViewById(R.id.btnEnviarRecuperacao);

        btnEnviarRecuperacao.setOnClickListener(v -> {

            String email = edtEmailRecuperacao.getText().toString().trim();

            if (email.isEmpty()) {
                edtEmailRecuperacao.setError("Digite seu e-mail");
                return;
            }

            Toast.makeText(this, "Se este e-mail existir, enviaremos o link de recuperação.", Toast.LENGTH_LONG).show();
        });
    }
}
