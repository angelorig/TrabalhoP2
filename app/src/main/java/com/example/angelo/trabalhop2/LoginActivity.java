package com.example.angelo.trabalhop2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private UsuarioDbHelper base;
    private SQLiteDatabase db;

    private EditText edLogin, edSenha;
    private Button btEntrar, btNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void novoUsuario(View view){
        Intent intent = new Intent(getApplicationContext(),UsuarioActivity.class);
        startActivity(intent);
    }

    public void entrar(View view){
        //Usuario local = new Usuario();
       // local.setLogin(edLogin.getText());
       // local.setSenha(edSenha.getText());

       // Usuario user =

        Intent intent = new Intent(getApplicationContext(),CalendarioActivity.class);
        startActivity(intent);
    }
}
