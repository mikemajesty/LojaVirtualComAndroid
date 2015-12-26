package com.br.spellsoft.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.spellsoft.Util.Constantes;

/**
 * Created by mike on 26/12/15.
 */
public class LoginRepository extends SQLiteOpenHelper {


    public LoginRepository(Context context) {
        super(context, Constantes.DB_NOME, null, Constantes.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
