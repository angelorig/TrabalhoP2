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

    //criação da tabela treino
    private static final String CREATETREINO = "create table " + TreinoContract.TreinoDb.TABLE_NAME + "( "
            + TreinoContract.TreinoDb._ID + " integer primary key autoincrement, "
            + TreinoContract.TreinoDb.COLUMN_EXERCICIO + " text, "
            + TreinoContract.TreinoDb.COLUMN_REPETICAO + " text, "
            + TreinoContract.TreinoDb.COLUMN_CARGA + " text, "
            + TreinoContract.TreinoDb.COLUMN_INTERVALO + " text, "
            + TreinoContract.TreinoDb.COLUMN_USUARIO + " integer)";
    private static final String DELETETREINO = "drop table if exists " + TreinoContract.TreinoDb.TABLE_NAME;

    public TreinoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETREINO);
        db.execSQL(CREATEUSUARIO);
        db.execSQL(CREATECHAT);
//        db.execSQL(CREATECATEGORIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETETREINO);
        db.execSQL(DELETEUSUARIO);
        db.execSQL(DELETECHAT);
        db.execSQL(DELETECATEGORIA);
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
        contentValues.put(TreinoContract.TreinoDb.COLUMN_USUARIO,treino.getUsuario().getId());
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

    //criação da tabela usuario
    private static final String CREATEUSUARIO = "create table " + TreinoContract.UsuarioDb.TABLE_NAME + "( "
            + TreinoContract.UsuarioDb._ID + " integer primary key autoincrement, "
            + TreinoContract.UsuarioDb.COLUMN_NOME + " text, "
            + TreinoContract.UsuarioDb.COLUMN_LOGIN + " text, "
            + TreinoContract.UsuarioDb.COLUMN_SENHA + " text)";
    private static final String DELETEUSUARIO = "drop table if exists " + TreinoContract.UsuarioDb.TABLE_NAME;

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
        SQLiteDatabase db = this.getWritableDatabase();

        Usuario usuario = new Usuario(-1L, "erro", "erro", "erro");

        String colunas[] = {TreinoContract.UsuarioDb._ID, TreinoContract.UsuarioDb.COLUMN_NOME,
                TreinoContract.UsuarioDb.COLUMN_LOGIN, TreinoContract.UsuarioDb.COLUMN_SENHA};
        String selecao = TreinoContract.UsuarioDb.COLUMN_LOGIN + " =? AND "
                + TreinoContract.UsuarioDb.COLUMN_SENHA + " =? ";
        String valores[] = {login,senha};

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

        SQLiteDatabase db = this.getWritableDatabase();

        Usuario usuario = new Usuario(-1L, "erro", "erro", "erro");

        String colunas[] = {TreinoContract.UsuarioDb._ID, TreinoContract.UsuarioDb.COLUMN_NOME,
                TreinoContract.UsuarioDb.COLUMN_LOGIN, TreinoContract.UsuarioDb.COLUMN_SENHA};
        String selecao = TreinoContract.UsuarioDb.COLUMN_LOGIN + " =? AND "
                + TreinoContract.UsuarioDb.COLUMN_SENHA + " =? ";
        String valores[] = {login,senha};

        Cursor cursor =  db.query(TreinoContract.UsuarioDb.TABLE_NAME, colunas, selecao, valores,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            usuario.setId(cursor.getLong(cursor.getColumnIndex(TreinoContract.UsuarioDb._ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_NOME)));
            usuario.setLogin(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_LOGIN)));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex(TreinoContract.UsuarioDb.COLUMN_SENHA)));
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

    //criação tabela chat
    private static final String CREATECHAT = "create table " + TreinoContract.ChatDb.TABLE_NAME + "( "
            + TreinoContract.ChatDb._ID + " integer primary key autoincrement, "
            + TreinoContract.ChatDb.COLUMN_MENSAGEM + " text, "
            + TreinoContract.ChatDb.COLUMN_DESTINATARIO + " text, "
            + TreinoContract.ChatDb.COLUMN_REMETENTE + " text)";
    private static final String DELETECHAT = "drop table if exists " + TreinoContract.ChatDb.TABLE_NAME;

    public boolean salvarChat(Chat chat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TreinoContract.ChatDb.COLUMN_MENSAGEM, chat.getMensagem());
        contentValues.put(TreinoContract.ChatDb.COLUMN_DESTINATARIO, chat.getDestinatario().toString());
        contentValues.put(TreinoContract.ChatDb.COLUMN_REMETENTE, chat.getRemetente().toString());
        long id = db.insert(TreinoContract.ChatDb.TABLE_NAME, null, contentValues);
        chat.setId(id);
        return true;
    }

    /*public ArrayList consultarChat(){
        ArrayList lista = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TreinoContract.ChatDb.TABLE_NAME, null);
        while (cursor.moveToNext()){
            lista.add(new Chat(cursor.getLong(cursor.getColumnIndex(TreinoContract.ChatDb._ID)),
                    cursor.getString(cursor.getColumnIndex(TreinoContract.ChatDb.COLUMN_MENSAGEM)),
                    cursor.getLong(cursor.getColumnIndex(TreinoContract.ChatDb.COLUMN_DESTINATARIO))),
                    cursor.getLong(cursor.getColumnIndex(TreinoContract.ChatDb.COLUMN_REMETENTE))));
        }
        return  lista;
    }*/

    //criação tabela categoria
    private static final String CREATECATEGORIA = "create table " + TreinoContract.CategoriaDb.TABLE_NAME + "( "
            + TreinoContract.CategoriaDb._ID + "integer primary key autoincrement, "
            + TreinoContract.CategoriaDb.COLUMN_DESCRICAO + " text)";
    private static final String DELETECATEGORIA = "drop table if exists " + TreinoContract.CategoriaDb.TABLE_NAME;


}


