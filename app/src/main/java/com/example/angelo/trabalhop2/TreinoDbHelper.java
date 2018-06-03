package com.example.angelo.trabalhop2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TreinoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Treino.db";

    private static final String CREATETREINO = "create table " + TreinoContract.TreinoDb.TABLE_NAME + "( "
            + TreinoContract.TreinoDb._ID + " integer primary key autoincrement, "
            + TreinoContract.TreinoDb.COLUMN_EXERCICIO + " text, "
            + TreinoContract.TreinoDb.COLUMN_REPETICAO + " text, "
            + TreinoContract.TreinoDb.COLUMN_CARGA + " text, "
            + TreinoContract.TreinoDb.COLUMN_INTERVALO + " text)";
    private static final String DELETETREINO = "drop table if exists " + TreinoContract.TreinoDb.TABLE_NAME;

    public TreinoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETREINO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETETREINO);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public boolean salvarTreino(Treino treino){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TreinoContract.TreinoDb.COLUMN_EXERCICIO, treino.getExercico());
        contentValues.put(TreinoContract.TreinoDb.COLUMN_REPETICAO, treino.getRepeticao());
        contentValues.put(TreinoContract.TreinoDb.COLUMN_CARGA, treino.getCarga());
        contentValues.put(TreinoContract.TreinoDb.COLUMN_INTERVALO, treino.getIntervalo());
        long id = db.insert(TreinoContract.TreinoDb.TABLE_NAME, null, contentValues);
        treino.setId(id);
        return true;
    }

    public ArrayList consultarTreino(){
        ArrayList lista = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TreinoContract.TreinoDb.TABLE_NAME, null);
        while (cursor.moveToNext()){
            lista.add(new Treino(cursor.getLong(cursor.getColumnIndex(TreinoContract.TreinoDb._ID)),
                    cursor.getString(cursor.getColumnIndex(TreinoContract.TreinoDb.COLUMN_EXERCICIO)),
                    cursor.getInt(cursor.getColumnIndex(TreinoContract.TreinoDb.COLUMN_REPETICAO)),
                    cursor.getInt(cursor.getColumnIndex(TreinoContract.TreinoDb.COLUMN_CARGA)),
                    cursor.getInt(cursor.getColumnIndex(TreinoContract.TreinoDb.COLUMN_INTERVALO))));
        }
        return  lista;
    }

}
