package com.example.angelo.trabalhop2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class CalendarioActivity extends AppCompatActivity  {

    private TreinoDbHelper base;
    private SQLiteDatabase db;
    private EditText exercicio, repeticao, carga, intervalo;
    private CalendarView calendario;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Usuario usuario;
    private Button mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        base = new TreinoDbHelper(getApplicationContext());
        exercicio = (EditText)findViewById(R.id.edExercicio);
        repeticao = (EditText)findViewById(R.id.edRepeticao);
        carga = (EditText)findViewById(R.id.edCarga);
        intervalo = (EditText)findViewById(R.id.edIntervalo);

        CalendarView calendario;
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");



        calendario = (CalendarView)findViewById(R.id.calendarView);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull final CalendarView view, int year, int month, int dayOfMonth) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarioActivity.this);
                CharSequence[]itens = new  CharSequence[3];
                itens[0]="Novo exercício";
                itens[1]="Consulta treino";
                itens[2]="Cancelar";

                final int dia, mes, ano;
                dia = dayOfMonth;
                mes = month+1;
                ano = year;

                builder.setTitle("Selecione uma opção").setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){

                            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dia", dia);
                            bundle.putInt("mes", mes);
                            bundle.putInt("ano", ano);
                            bundle.putSerializable("usuario",usuario);

                            intent.putExtras(bundle);
                            startActivity(intent);
                        }if (which==1){
                            visualizarTreino(view);
                        } else{
                            return;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        mensagem = (Button) findViewById(R.id.btMensagem);
        mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagem(CalendarioActivity.this);
            }
        });
    }

    public void visualizarTreino(View view){
        ListView lista = (ListView) findViewById(R.id.ltvListaTreino);
        ArrayAdapter<Treino> arrayAd = new ArrayAdapter<Treino>(getApplicationContext(), android.R.layout.simple_list_item_1, base.consultarTreino());
        lista.setAdapter(arrayAd);
    }

    private void enviarMensagem (Context context){
        try {
            ir_mensagem(this, usuario);
        } catch (Exception e) {
            Toast.makeText(context, "Erro - Não é possível enviar mensagem", Toast.LENGTH_LONG).show();
        }
    }

    private void ir_mensagem(Context context, Usuario usuario){
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario",usuario);
        /* iniciando nova tela*/
        Intent iMensagem = new Intent(this, MensagemActivity.class);
        iMensagem.putExtras(bundle);
        startActivity(iMensagem);
    }
}
