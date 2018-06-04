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
        Long l = db.insert(TreinoContract.UsuarioDb.TABLE_NAME, null, contentValues);

        return (l != -1L);
    }

    public ArrayList consultarUsuario(){
    //public Usuario consultarUsuario(Usuario usuario){
        ArrayList lista = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TreinoContract.UsuarioDb.TABLE_NAME, null);
        while (cursor.moveToNext()){
            new Usuario(cursor.getLong(cursor.getColumnIndex(TreinoContract.UsuarioDb._ID)),
                    cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_LOGIN)),
                    cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_NOME)),
                    cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_SENHA)));
        }
        return lista;
    }

    public Usuario login(String login, String senha) {
        /* Variáveis do BD */
        SQLiteDatabase db = this.getWritableDatabase();

        /* Variáveis do Método*/
        // criando usuário com valor incorreto para erro
        Usuario usuario = new Usuario(-1L, "erro", "erro", "erro");

        String colunas[] = {TreinoContract.UsuarioDb._ID, TreinoContract.UsuarioDb.COLUMN_NOME,
                TreinoContract.UsuarioDb.COLUMN_LOGIN, TreinoContract.UsuarioDb.COLUMN_SENHA};
        String selecao = TreinoContract.UsuarioDb.COLUMN_LOGIN + " =? AND " + TreinoContract.UsuarioDb.COLUMN_SENHA + " =? ";
        String valores[] = {login,senha};

        /* Realizando busca no banco de dados */
        Cursor cursor =  db.query(TreinoContract.UsuarioDb.TABLE_NAME, colunas, selecao, valores,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            usuario.setId(cursor.getLong(cursor.getColumnIndex(TreinoContract.UsuarioDb._ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_NOME)));
            usuario.setLogin(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_LOGIN)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_SENHA)));
        }
        return usuario;
    }

    public Usuario nome(String login, String senha) {
        /* Variáveis do BD */
        SQLiteDatabase db = this.getWritableDatabase();

        /* Variáveis do Método*/
        // criando usuário com valor incorreto para erro
        Usuario usuario = new Usuario(-1L, "erro", "erro", "erro");

        String colunas[] = {TreinoContract.UsuarioDb._ID, TreinoContract.UsuarioDb.COLUMN_NOME,
                TreinoContract.UsuarioDb.COLUMN_LOGIN, TreinoContract.UsuarioDb.COLUMN_SENHA};
        String selecao = TreinoContract.UsuarioDb.COLUMN_LOGIN + " =? AND " + TreinoContract.UsuarioDb.COLUMN_SENHA + " =? ";
        String valores[] = {login,senha};

        /* Realizando busca no banco de dados */
        Cursor cursor =  db.query(TreinoContract.UsuarioDb.TABLE_NAME, colunas, selecao, valores,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            usuario.setId(cursor.getLong(cursor.getColumnIndex(TreinoContract.UsuarioDb._ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_NOME)));
            usuario.setLogin(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_LOGIN)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_SENHA)));
        }
        return usuario;
    }

    /*public Usuario validaUsuario(Usuario usuario){
        usuario = consultarUsuario(usuario);

        if(usuario != null
                && usuario.getLogin().equals(usuario.getLogin())
                && usuario.getSenha().equals(usuario.getSenha())) {
            return usuario;
        }
        return null;
    }*/
}
