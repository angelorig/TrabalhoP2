package com.example.angelo.trabalhop2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private TreinoDbHelper base;
    private EditText exercicio, repeticao, carga, intervalo;
    private Button salvar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        base = new TreinoDbHelper(getApplicationContext());

        exercicio = (EditText)findViewById(R.id.edExercicio);
        repeticao = (EditText)findViewById(R.id.edRepeticao);
        carga = (EditText)findViewById(R.id.edCarga);
        intervalo = (EditText)findViewById(R.id.edIntervalo);

        salvar = (Button)findViewById(R.id.btSalvar);
        cancelar = (Button)findViewById(R.id.btCancelar);

        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    public void salvarTreino(View view){
        Treino treino = new Treino(exercicio.getText().toString(), Integer.parseInt(repeticao.getText().toString()),Integer.parseInt(carga.getText().toString()),
                Integer.parseInt(intervalo.getText().toString()));
        base.salvarTreino(treino);
        exercicio.setText("");
        repeticao.setText("");
        carga.setText("");
        intervalo.setText("");
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==salvar.getId()){
            Treino treino = new Treino(exercicio.getText().toString(), Integer.parseInt(repeticao.getText().toString()),Integer.parseInt(carga.getText().toString()),
                    Integer.parseInt(intervalo.getText().toString()));
            base.salvarTreino(treino);
            exercicio.setText("");
            repeticao.setText("");
            carga.setText("");
            intervalo.setText("");
        }else {
            this.finish();
            return;
        }
    }
}