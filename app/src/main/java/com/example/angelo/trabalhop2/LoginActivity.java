package com.example.angelo.trabalhop2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    private UsuarioDbHelper base;
    private SQLiteDatabase db;

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
        Intent intent = new Intent(getApplicationContext(),CalendarioActivity.class);
        startActivity(intent);
    }
}
