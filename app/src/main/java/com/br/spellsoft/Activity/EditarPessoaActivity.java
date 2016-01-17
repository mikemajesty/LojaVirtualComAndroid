package com.br.spellsoft.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.TextView;

import com.br.spellsoft.Activity.R;
import com.br.spellsoft.Entidades.Pessoa;
import com.br.spellsoft.Entidades.Profissoes;
import com.br.spellsoft.Entidades.TipoPessoa;
import com.br.spellsoft.Repository.DatePickerFragmant;
import com.br.spellsoft.Repository.PessoaRepository;
import com.br.spellsoft.Util.Mask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditarPessoaActivity extends Activity {

    private Pessoa pessoa;
    private Spinner spinnerProfissao;
    private EditText txtCpf,txtDataNasc,txtNome,txtEndereco;
    private RadioGroup rdgCpfCnpj;
    private RadioButton rdbCpf,rdbCnpj,rdbMas,rdbFem;
    private TextWatcher cpfMask,cnpjMask;
    private int cpfCnpjSelecionado;
    private TextView lblCpfCnpj;
    private RadioGroup rdgSexo;
    private PessoaRepository pessoaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pessoa);

        pessoaRepository = new PessoaRepository(this);
        spinnerProfissao = (Spinner)findViewById(R.id.spnProfissao);
        txtCpf = (EditText) findViewById(R.id.txtCpfCnpj);
        txtDataNasc = (EditText) findViewById(R.id.txtDataNasc);
        txtNome = (EditText) findViewById(R.id.txt_Nome);
        txtEndereco  = (EditText)findViewById(R.id.txt_Endereco);
        rdgCpfCnpj = (RadioGroup) findViewById(R.id.rdgCpfCnpj);
        rdbCpf = (RadioButton) findViewById(R.id.rdbCpf);
        rdbCnpj = (RadioButton) findViewById(R.id.rdbCnpj);

        rdbMas = (RadioButton) findViewById(R.id.rdbMasculino);
        rdbFem = (RadioButton) findViewById(R.id.rdbFeminino);

        cpfMask = Mask.insert("###.###.###-##", txtCpf);
        txtCpf.addTextChangedListener(cpfMask);
        lblCpfCnpj = (TextView) findViewById(R.id.lblCpfCnpj);
        cnpjMask = Mask.insert("##.###.###/####-##",txtCpf );
        rdgSexo = (RadioGroup) findViewById(R.id.rdgSexo);

        rdgCpfCnpj.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        cpfCnpjSelecionado = radioGroup.getCheckedRadioButtonId();
                        if (cpfCnpjSelecionado == rdbCpf.getId()) {
                            txtCpf.removeTextChangedListener(cnpjMask);
                            txtCpf.addTextChangedListener(cpfMask);
                            txtCpf.setText("");
                            txtCpf.requestFocus();
                            lblCpfCnpj.setText("CPF:");
                        } else {
                            txtCpf.removeTextChangedListener(cpfMask);
                            txtCpf.addTextChangedListener(cnpjMask);
                            txtCpf.setText("");
                            txtCpf.requestFocus();
                            lblCpfCnpj.setText("CNPJ:");
                        }
                    }
                }
        );

        this.IniciaProfissao();

        PreencherCampos();
        txtCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (rdgCpfCnpj.getCheckedRadioButtonId() == rdbCpf.getId()) {
                    if (txtCpf.getText().length() < 14) {
                        txtCpf.setText("");
                    }
                } else {
                    if (txtCpf.getText().length() < 18) {
                        txtCpf.setText("");

                    }
                }
            }
        });
        getActionBar().setTitle("Editar Pessoa");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);




    }
    private void IniciaProfissao(){
        ArrayList<String> profissoes = new ArrayList<>();
        for (Profissoes p : Profissoes.values() ){
            profissoes.add(p.getDescricao());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(EditarPessoaActivity.this,android.R.layout.simple_spinner_item,profissoes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfissao.setAdapter(arrayAdapter);
    }
   private  void PreencherCampos(){
       pessoa = (Pessoa) getIntent().getExtras().getSerializable("pessoa");
       txtNome.setText(pessoa.getNome());
       txtEndereco.setText(pessoa.getEndereco());
       switch (pessoa.getTipoPessoa()){
           case FISICA:
               lblCpfCnpj.setText("CPF: ");
               rdbCpf.setChecked(true);
               break;
           case JURIDICA:
               lblCpfCnpj.setText("CNPJ: ");
               rdbCnpj.setChecked(true);
               break;
       }
       switch (pessoa.getSexo())
       {
           case FEMININO:
               rdbFem.setChecked(true);
               break;
           case MASCULINO:
               rdbMas.setChecked(true);
               break;
       }
       txtCpf.setText(pessoa.getCpfCnpj());
        spinnerProfissao.setSelection(pessoa.getProfissao().ordinal());
       DateFormat date =new SimpleDateFormat("dd/MM/yyyy");
       txtDataNasc.setText(date.format(pessoa.getDtNasc()));
   }
    public  void setData(View v){
        DatePickerFragmant datePickerFragmant = new DatePickerFragmant();
        Bundle bundle  = new Bundle();
        Calendar c = Calendar.getInstance();
        bundle.putInt("Dia",c.get(Calendar.DAY_OF_MONTH));
        bundle.putInt("Mes",c.get(Calendar.MONTH));
        bundle.putInt("Ano",c.get(Calendar.YEAR));
        datePickerFragmant.setArguments(bundle);
        datePickerFragmant.setDateListiner(dateListener);
        datePickerFragmant.show(getFragmentManager(),"Data Nasc");
    }
    private boolean ValidarPessoa(Pessoa pessoa){
        boolean erro = false;
        if(pessoa.getNome() == null ||  "".equals(pessoa.getNome())){
            txtNome.setError("Campo obrigatório");
            erro = true;
        }
        if(pessoa.getEndereco() == null ||  "".equals(pessoa.getEndereco())){
            txtEndereco.setError("Campo obrigatório");
            erro = true;
        }
        if(pessoa.getCpfCnpj() == null ||  "".equals(pessoa.getCpfCnpj())){
            txtCpf.setError(rdgCpfCnpj.getCheckedRadioButtonId()
                    == R.id.rdbCnpj ? "Campo CNPJ obrigatório" : "Campo CPF obrigatório");
            erro = true;
        }
        else
        {
            switch (rdgCpfCnpj.getCheckedRadioButtonId()){
                case R.id.rdbCpf:
                    if (pessoa.getCpfCnpj().length() != 14)
                    {
                        erro = true;
                        txtCpf.setError("Campo deve ter 11 caracteres");
                    }
                    break;
                case R.id.rdbCnpj:
                    if (pessoa.getCpfCnpj().length() != 18)
                    {
                        erro = true;
                        txtCpf.setError("Campo deve ter 14 caracteres");
                    }
                    break;

            }

        }
        if(pessoa.getDtNasc() == null || txtDataNasc.getText().equals("")){
            txtDataNasc.setError("Campo obrigatório");
            erro = true;
        }
        else {
            txtDataNasc.setError(null);
        }
        return  erro    ;
    }
    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            txtDataNasc.setText(day+"/"+(month+1)+"/"+year);

        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar_pessoa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
