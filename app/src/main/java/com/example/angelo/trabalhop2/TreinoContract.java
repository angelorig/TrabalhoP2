package com.example.angelo.trabalhop2;

import android.provider.BaseColumns;

public class TreinoContract {

    public TreinoContract(){
    }

    public static abstract class TreinoDb implements BaseColumns {
        public static final String TABLE_NAME = "treino";
        public static final String COLUMN_EXERCICIO = "exercicio";
        public static final String COLUMN_REPETICAO = "repeticao";
        public static final String COLUMN_CARGA = "carga";
        public static final String COLUMN_INTERVALO = "intervalo";
        public static final String COLUMN_USUARIO = "usuario";
    }

    public static abstract class UsuarioDb implements BaseColumns {
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_SENHA = "senha";
        //public static final String COLUMN_CATEGORIA = "categoria";
    }

    public static abstract class CategoriaDb implements BaseColumns {
        public static final String TABLE_NAME = "categoria";
        public static final String COLUMN_DESCRICAO = "descricao";
    }

    public static abstract class ChatDb implements BaseColumns {
        public static final String TABLE_NAME = "chat";
        public static final String COLUMN_MENSAGEM = "mensagem";
        public static final String COLUMN_REMETENTE = "remetente";
        public static final String COLUMN_DESTINATARIO = "destinatario";
    }
}