package com.br.spellsoft.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.br.spellsoft.Entidades.Profissoes;
import com.br.spellsoft.Util.Mask;

import java.util.ArrayList;

public class PessoaActivity extends Activity {

    private Spinner spinnerProfissao;
    private EditText txtCpf;
    private RadioGroup rdgCpfCnpj;
    private RadioButton rdbCpf;
    private TextWatcher cpfMask,cnpjMask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        spinnerProfissao = (Spinner)findViewById(R.id.spnProfissao);
        txtCpf = (EditText) findViewById(R.id.txtCpfCnpj);
        rdgCpfCnpj = (RadioGroup) findViewById(R.id.rdgCpfCnpj);
        rdbCpf = (RadioButton) findViewById(R.id.rdbCpf);
        cpfMask = Mask.insert("###.###.###-##",txtCpf);
        txtCpf.addTextChangedListener(cpfMask);

        cnpjMask = Mask.insert("##.###.###/####-##",txtCpf );
        //txtCpf.addTextChangedListener(cpfMask);

        rdgCpfCnpj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int opcao = radioGroup.getCheckedRadioButtonId();
                if ( opcao == rdbCpf.getId()){
                    txtCpf.removeTextChangedListener(cnpjMask);
                    txtCpf.addTextChangedListener(cpfMask);
                    txtCpf.setText("");
                }
                else{
                    txtCpf.removeTextChangedListener(cpfMask);
                    txtCpf.addTextChangedListener(cnpjMask);
                    txtCpf.setText("");
                }
            }
        });

        this.IniciaProfissao();


    }
    private void IniciaProfissao(){
        ArrayList<String> profissoes = new ArrayList<>();
        for (Profissoes p : Profissoes.values() ){
            profissoes.add(p.getDescricao());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(PessoaActivity.this,android.R.layout.simple_spinner_item,profissoes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfissao.setAdapter(arrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pessoa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
