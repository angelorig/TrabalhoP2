package com.example.angelo.trabalhop2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    private UsuarioDbHelper base;
    private EditText nome, login, senha;
    private Button salvar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        base = new UsuarioDbHelper(getApplicationContext());

        nome = (EditText)findViewById(R.id.edNome);
        login = (EditText)findViewById(R.id.edLogin);
        senha = (EditText)findViewById(R.id.edSenha);

        salvar = (Button)findViewById(R.id.btSalvar);
        cancelar = (Button)findViewById(R.id.btCancelar);

        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    public void salvarUsuario(View view){
        Usuario usuario = new Usuario(nome.getText().toString(), login.getText().toString(),senha.getText().toString());
        base.salvarUsuario(usuario);
        nome.setText("");
        login.setText("");
        senha.setText("");

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==salvar.getId()){
            Usuario usuario = new Usuario(nome.getText().toString(), login.getText().toString(),senha.getText().toString());
            base.salvarUsuario(usuario);
            nome.setText("");
            login.setText("");
            senha.setText("");
        }else {
            this.finish();
            return;
        }
    }
}
