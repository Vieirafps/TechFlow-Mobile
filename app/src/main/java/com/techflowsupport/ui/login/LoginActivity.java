package com.techflowsupport.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techflowsupport.R;
import com.techflowsupport.ui.home.HomeUsuarioActivity;
import com.techflowsupport.ui.tecnico.DashboardTecnicoActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnRecuperarSenha).setOnClickListener(v ->
                startActivity(new Intent(this, RecuperarSenhaActivity.class))
        );


        iniciarComponentes();
        configurarEventos();
    }

    private void iniciarComponentes() {
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void configurarEventos() {
        btnLogin.setOnClickListener(v -> {

            String email = edtEmail.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
                Toast.makeText(this, "Preencha e-mail e senha", Toast.LENGTH_SHORT).show();
                return;
            }


            if (email.equals("cliente@techflow.com") && senha.equals("123")) {
                Intent intent = new Intent(this, HomeUsuarioActivity.class);
                startActivity(intent);
                finish();
                return;
            }

            if (email.equals("tecnico@techflow.com") && senha.equals("123")) {
                Intent intent = new Intent(this, DashboardTecnicoActivity.class);
                startActivity(intent);
                finish();
                return;
            }


            Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
        });
    }
}
