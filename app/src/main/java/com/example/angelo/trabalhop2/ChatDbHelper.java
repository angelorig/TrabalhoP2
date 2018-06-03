package com.example.angelo.trabalhop2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ChatDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Chat.db";

    private static final String CREATECHAT = "create table " + TreinoContract.ChatDb.TABLE_NAME + "( "
            + TreinoContract.ChatDb._ID + " integer primary key autoincrement, "
            + TreinoContract.ChatDb.COLUMN_MENSAGEM + " text, "
            + TreinoContract.ChatDb.COLUMN_DESTINATARIO + " text, "
            + TreinoContract.ChatDb.COLUMN_REMETENTE + " text)";
    private static final String DELETECHAT = "drop table if exists " + TreinoContract.ChatDb.TABLE_NAME;

    public ChatDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATECHAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETECHAT);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

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
                    cursor.getString(cursor.getColumnIndex(TreinoContract.ChatDb.COLUMN_DESTINATARIO))),
                    cursor.getInt(cursor.getColumnIndex(TreinoContract.ChatDb.COLUMN_REMETENTE))));
        }
        return  lista;
    }*/
}
