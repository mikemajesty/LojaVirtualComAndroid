package com.br.spellsoft.Repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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

         PopularDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
    private void PopularDB(SQLiteDatabase db){
        StringBuilder query = new StringBuilder()
                .append("INSERT INTO TB_LOGIN(USUARIO,SENHA) VALUES(?,?)");

        db.execSQL(query.toString(),new String[]{"admin","admin"});

    }
    public void ListarLogin(Activity activity){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("TB_LOGIN", null, "ID_LOGIN=? and USUARIO=?", new String[]{"1","admin"}, null, null, "USUARIO");
        while (cursor.moveToNext()){

                 String message = "ID: "+String.valueOf(cursor.getInt(cursor.getColumnIndex("ID_LOGIN")))+"\nUsuario: "+
                    cursor.getString(cursor.getColumnIndex("USUARIO"))+"\nSenha: "+
                    cursor.getString(cursor.getColumnIndex("SENHA"));

                    Toast.makeText(activity, message,Toast.LENGTH_LONG ).show();

        }
    }
    public Boolean CadastrarLogin(String login,String senha){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("USUARIO",login);
        content.put("SENHA", senha);
        return db.insert("TB_LOGIN",null,content) > 0 ? true : false;
    }
    public  Boolean AtualizarLogin(String login,String senha){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("USUARIO",login);
        content.put("SENHA", senha);
        return db.update("TB_LOGIN", content, "ID_LOGIN > 1", null) > 0 ? true : false;
    }
    public  Boolean Deletar(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("TB_LOGIN","ID_LOGIN > 1",null) > 0 ? true: false;
    }
}
