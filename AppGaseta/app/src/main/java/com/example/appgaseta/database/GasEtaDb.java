package com.example.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GasEtaDb extends SQLiteOpenHelper {

    public static final String DB_NAME = "gaseta.db";
    public static final int DB_VERSION = 9;

    Cursor cursor;

    SQLiteDatabase db;

    public GasEtaDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTabelaCombustivel
                = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "precoGasolina REAL," +
                "precoEtanol REAL," +
                "resultado TEXT)";

        db.execSQL(sqlTabelaCombustivel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String sqlTabelaCombustivel
                    = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "precoGasolina REAL," +
                    "precoEtanol REAL," +
                    "resultado TEXT)";

            db.execSQL(sqlTabelaCombustivel);
        }
    }

    public void salvarDados(String tabela, ContentValues dados){
        db.insert(tabela, null,dados);

    }

    //TODO: criar metodos para implementar o CRUD
}
