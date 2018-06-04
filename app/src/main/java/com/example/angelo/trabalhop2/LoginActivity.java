package com.example.angelo.trabalhop2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private UsuarioDbHelper base;
    private SQLiteDatabase db;

    private EditText edUsuario, edSenha;
    private Button entrar, novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = (Button)findViewById(R.id.btEntrar);
        novo = (Button)findViewById(R.id.btNovo);
        edUsuario = (EditText)findViewById(R.id.edUsuario);
        edSenha = (EditText)findViewById(R.id.edSenha);

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UsuarioActivity.class);
                startActivity(intent);
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar(LoginActivity.this, edUsuario.getText().toString(), edSenha.getText().toString());
            }
        });
    }

    private void entrar (Context context, String login, String senha){
        try {
            Usuario usuario = new UsuarioDbHelper(context).login(login, senha);
            Toast.makeText(context, "id " + usuario.getId() + "!", Toast.LENGTH_LONG).show();
            if (usuario.getId() != -1L) {
                Toast.makeText(context, "Bem Vindo " + usuario.getNome() + "!", Toast.LENGTH_LONG).show();
                ir_calendario(this, usuario);
            } else {
                Toast.makeText(context, "Usuário ou Senha incorretos", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Erro - Não foi possível logar", Toast.LENGTH_LONG).show();
        }
    }

    private void ir_calendario(Context context, Usuario usuario){
        Bundle params = new Bundle();
        /* carregando paramentros de uma tela a outra */
        params.putLong("usuarioId", usuario.getId());
        params.putString("usuarioNome", usuario.getNome());
        params.putString("usuarioLogin", usuario.getLogin());
        params.putString("usuarioSenha", usuario.getSenha());
        /* iniciando nova tela*/
        Intent iLogin = new Intent(this, CalendarioActivity.class);
        iLogin.putExtras(params);
        startActivity(iLogin);
    }
}
