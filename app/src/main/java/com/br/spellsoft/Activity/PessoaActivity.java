package com.br.spellsoft.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.spellsoft.Entidades.Profissoes;
import com.br.spellsoft.Util.Mask;

import java.util.ArrayList;
import java.util.Calendar;

public class PessoaActivity extends Activity {

    private Spinner spinnerProfissao;
    private EditText txtCpf;
    private RadioGroup rdgCpfCnpj;
    private RadioButton rdbCpf;
    private TextWatcher cpfMask,cnpjMask;
    private int cpfCnpjSelecionado;
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
                cpfCnpjSelecionado = radioGroup.getCheckedRadioButtonId();
                if ( cpfCnpjSelecionado == rdbCpf.getId()){
                    txtCpf.removeTextChangedListener(cnpjMask);
                    txtCpf.addTextChangedListener(cpfMask);
                    txtCpf.setText("");
                    txtCpf.requestFocus();
                }
                else{
                    txtCpf.removeTextChangedListener(cpfMask);
                    txtCpf.addTextChangedListener(cnpjMask);
                    txtCpf.setText("");
                    txtCpf.requestFocus();
                }
            }
        });

        this.IniciaProfissao();

        txtCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (rdgCpfCnpj.getCheckedRadioButtonId() == rdbCpf.getId()){
                   if( txtCpf.getText().length() < 14){
                       txtCpf.setText("");
                   }
                }
                else {
                    if (txtCpf.getText().length() < 18){
                        txtCpf.setText("");

                    }
                }
            }
        });

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

    public  void setData(View v){
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999){
            Calendar c = Calendar.getInstance();
            return new DatePickerDialog(this, dateListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Toast.makeText(PessoaActivity.this,"Ano "+i+",Mês,"+(i1+1) +",Dia "+i2,Toast.LENGTH_LONG).show();
        }

    };

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
