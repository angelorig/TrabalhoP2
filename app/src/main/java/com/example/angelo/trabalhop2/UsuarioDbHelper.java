package com.example.angelo.trabalhop2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UsuarioDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usuario.db";

    private static final String CREATEUSUARIO = "create table " + TreinoContract.UsuarioDb.TABLE_NAME + "( "
            + TreinoContract.UsuarioDb._ID + " integer primary key autoincrement, "
            + TreinoContract.UsuarioDb.COLUMN_NOME + " text, "
            + TreinoContract.UsuarioDb.COLUMN_LOGIN + " text, "
            + TreinoContract.UsuarioDb.COLUMN_SENHA + " text)";
    private static final String DELETEUSUARIO = "drop table if exists " + TreinoContract.UsuarioDb.TABLE_NAME;

    public UsuarioDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATEUSUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETEUSUARIO);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public boolean salvarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TreinoContract.UsuarioDb.COLUMN_NOME, usuario.getNome());
        contentValues.put(TreinoContract.UsuarioDb.COLUMN_LOGIN, usuario.getLogin());
        contentValues.put(TreinoContract.UsuarioDb.COLUMN_SENHA, usuario.getSenha());
        long id = db.insert(TreinoContract.UsuarioDb.TABLE_NAME, null, contentValues);
        usuario.setId(id);
        return true;
    }
}
