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
         StringBuilder query = new StringBuilder()
        .append("CREATE TABLE TB_LOGIN")
        .append("(ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT,")
        .append("USUARIO TEXT(15) NOT NULL,")
        .append("SENHA TEXT(15) NOT NULL)");
         db.execSQL(query.toString());
         //PopularDB();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
    public  void PopularDB(){
        StringBuilder query = new StringBuilder()
                .append("INSERT INTO TB_LOGIN(USUARIO,SENHA) VALUES(?,?)");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query.toString(),new String[]{"admin","admin"});

    }
}
