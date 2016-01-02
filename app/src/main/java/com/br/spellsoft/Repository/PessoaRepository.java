package com.br.spellsoft.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.spellsoft.Util.Constantes;

/**
 * Created by mike on 02/01/16.
 */
public class PessoaRepository extends SQLiteOpenHelper {
    public PessoaRepository(Context context) {
        super(context, Constantes.DB_NOME , null, Constantes.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder query = new StringBuilder()
                .append("CREATE TABLE TB_PESSOA")
                .append("(ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("NOME TEXT(30) NOT NULL,")
                .append("ENDERECO TEXT(50),")
                .append("CPF TEXT(14),")
                .append("CNPJ TEXT(14),")
                .append("SEXO INTEGER(1) NOT NULL,")
                .append("PROFISSAO INTEGER(3) NOT NULL,")
                .append("DT_NASC INTEGER NOT NULL)");
        db.execSQL(query.toString());

    }
}
