package com.br.spellsoft.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.spellsoft.Entidades.Pessoa;
import com.br.spellsoft.Entidades.Profissoes;
import com.br.spellsoft.Entidades.Sexo;
import com.br.spellsoft.Entidades.TipoPessoa;
import com.br.spellsoft.Util.Constantes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mike on 02/01/16.
 */
public class PessoaRepository extends SQLiteOpenHelper {
    public PessoaRepository(Context context) {
        super(context, Constantes.DB_NOME, null, Constantes.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder query = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS TB_PESSOA")
                .append("(ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("NOME TEXT(30) NOT NULL,")
                .append("ENDERECO TEXT(50),")
                .append("CPF TEXT(14),")
                .append("CNPJ TEXT(18),")
                .append("SEXO INTEGER(1) NOT NULL,")
                .append("PROFISSAO INTEGER(3) NOT NULL,")
                .append("DT_NASC INTEGER NOT NULL)");
        db.execSQL(query.toString());
    }

    public void SalvarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", pessoa.getNome());
        contentValues.put("ENDERECO", pessoa.getEndereco());
        contentValues.put(pessoa.getTipoPessoa() == TipoPessoa.FISICA ? "CPF" : "CNPJ", pessoa.getCpfCnpj());
        contentValues.put("SEXO", pessoa.getSexo().ordinal());
        contentValues.put("PROFISSAO", pessoa.getProfissao().ordinal());
        contentValues.put("DT_NASC", pessoa.getDtNasc().getTime());
        long result = db.insert("TB_PESSOA", null, contentValues);
    }

    public List<Pessoa> ListarPessoa() {
        List<Pessoa> listar = new ArrayList<Pessoa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PESSOA", null, null, null, null, null, "NOME");
        Pessoa pessoa;
        while (cursor.moveToNext()) {
            pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(cursor.getColumnIndex("ID_PESSOA")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            pessoa.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
            pessoa.setTipoPessoa(cursor.getString(cursor.getColumnIndex("CPF"))
                    != "" ? TipoPessoa.FISICA : TipoPessoa.JURIDICA);
            pessoa.setCpfCnpj(cursor.getString(cursor.getColumnIndex("CPF")) != null ? cursor.getString(cursor.getColumnIndex("CPF")) :
                    cursor.getString(cursor.getColumnIndex("CNPJ")));
            pessoa.setSexo(Sexo.GetSexo(cursor.getInt(cursor.getColumnIndex("SEXO"))));
            pessoa.setProfissao(Profissoes.GetProfissao(cursor.getInt(cursor.getColumnIndex("PROFISSAO"))));
            long time = cursor.getLong(cursor.getColumnIndex("DT_NASC"));
            Date dtNasc = new Date();
            dtNasc.setTime(time);
            pessoa.setDtNasc(dtNasc);
            listar.add(pessoa);

        }
        return listar;
    }
    public Pessoa ConsultarPessoaPorID(int id){
        Pessoa pessoa = new Pessoa();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PESSOA", null, "ID_PESSOA=?", new String[]{String.valueOf(id)}, null, null, "NOME");
        if (cursor.moveToNext()){
            pessoa.setId(cursor.getInt(cursor.getColumnIndex("ID_PESSOA")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            pessoa.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
            pessoa.setTipoPessoa(cursor.getString(cursor.getColumnIndex("CPF"))
                    != "" ? TipoPessoa.FISICA : TipoPessoa.JURIDICA);
            pessoa.setCpfCnpj(cursor.getString(cursor.getColumnIndex("CPF")) != null ? cursor.getString(cursor.getColumnIndex("CPF")) :
                    cursor.getString(cursor.getColumnIndex("CNPJ")));
            pessoa.setSexo(Sexo.GetSexo(cursor.getInt(cursor.getColumnIndex("SEXO"))));
            pessoa.setProfissao(Profissoes.GetProfissao(cursor.getInt(cursor.getColumnIndex("PROFISSAO"))));
            long time = cursor.getLong(cursor.getColumnIndex("DT_NASC"));
            Date dtNasc = new Date();
            dtNasc.setTime(time);
            pessoa.setDtNasc(dtNasc);
        }
        return pessoa;
    }

}
