package com.example.angelo.trabalhop2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MensagemActivity extends AppCompatActivity {

    private TreinoDbHelper base;
    private SQLiteDatabase db;
    private Spinner spinner;
    private EditText mensagem;
    private ListView listView;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);

        base = new TreinoDbHelper(getApplicationContext());
        mensagem = (EditText)findViewById(R.id.edMensagem);

        Spinner spinner = (Spinner)findViewById(R.id.spDestinatario);
        ArrayAdapter<Usuario> arrayAd = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, base.consultarUsuario());
        spinner.setAdapter(arrayAd);
    }
}
