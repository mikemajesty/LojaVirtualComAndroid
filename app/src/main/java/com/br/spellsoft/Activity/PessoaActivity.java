package com.br.spellsoft.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.br.spellsoft.Entidades.Pessoa;
import com.br.spellsoft.Entidades.Profissoes;
import com.br.spellsoft.Entidades.Sexo;
import com.br.spellsoft.Entidades.TipoPessoa;
import com.br.spellsoft.Repository.DatePickerFragmant;
import com.br.spellsoft.Repository.PessoaRepository;
import com.br.spellsoft.Util.Mask;
import  com.br.spellsoft.Activity.ListaPessoaActivity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PessoaActivity extends Activity {

    private Spinner spinnerProfissao;
    private EditText txtCpf,txtDataNasc,txtNome,txtEndereco;
    private RadioGroup rdgCpfCnpj;
    private RadioButton rdbCpf;
    private TextWatcher cpfMask,cnpjMask;
    private int cpfCnpjSelecionado;
    private TextView lblCpfCnpj;
    private RadioGroup rdgSexo;
    private PessoaRepository pessoaRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        pessoaRepository = new PessoaRepository(this);
        spinnerProfissao = (Spinner)findViewById(R.id.spnProfissao);
        txtCpf = (EditText) findViewById(R.id.txtCpfCnpj);
        txtDataNasc = (EditText) findViewById(R.id.txtDataNasc);
        txtNome = (EditText) findViewById(R.id.txt_Nome);
        txtEndereco  = (EditText)findViewById(R.id.txt_Endereco);
        rdgCpfCnpj = (RadioGroup) findViewById(R.id.rdgCpfCnpj);
        rdbCpf = (RadioButton) findViewById(R.id.rdbCpf);
        cpfMask = Mask.insert("###.###.###-##",txtCpf);
        txtCpf.addTextChangedListener(cpfMask);
        lblCpfCnpj = (TextView) findViewById(R.id.lblCpfCnpj);
        cnpjMask = Mask.insert("##.###.###/####-##",txtCpf );
        rdgSexo = (RadioGroup) findViewById(R.id.rdgSexo);

        rdgCpfCnpj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                cpfCnpjSelecionado = radioGroup.getCheckedRadioButtonId();
                if ( cpfCnpjSelecionado == rdbCpf.getId()){
                    txtCpf.removeTextChangedListener(cnpjMask);
                    txtCpf.addTextChangedListener(cpfMask);
                    txtCpf.setText("");
                    txtCpf.requestFocus();
                    lblCpfCnpj.setText("CPF:");
                }
                else{
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
        this.getActionBar().setTitle("Cadastro de Pessoa");
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setHomeButtonEnabled(true);
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
        DatePickerFragmant datePickerFragmant = new DatePickerFragmant();
        Bundle bundle  = new Bundle();
        Calendar c = Calendar.getInstance();
        bundle.putInt("Mia",c.get(Calendar.DAY_OF_MONTH));
        bundle.putInt("Mes",c.get(Calendar.MONTH));
        bundle.putInt("Ano",c.get(Calendar.YEAR));
        datePickerFragmant.setArguments(bundle);
        datePickerFragmant.setDateListiner(dateListener);
        datePickerFragmant.show(getFragmentManager(),"Data Nasc");
    }


    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            txtDataNasc.setText(i+"/"+(i1+1)+"/"+i2);

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
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private Pessoa  PopularPesoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(txtNome.getText().toString());
        pessoa.setEndereco(txtEndereco.getText().toString());
        pessoa.setCpfCnpj(txtCpf.getText().toString());
        pessoa.setTipoPessoa(rdgCpfCnpj.getCheckedRadioButtonId()
                == R.id.rdbCnpj ? TipoPessoa.JURIDICA : TipoPessoa.FISICA);
        pessoa.setSexo(rdgSexo.getCheckedRadioButtonId()
                == R.id.rdbMasculino ? Sexo.MASCULINO : Sexo.FEMININO);
        Profissoes profissoes = Profissoes.GetProfissao(spinnerProfissao.getSelectedItemPosition());
        pessoa.setProfissao(profissoes);
        DateFormat dataDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
           Date nasc = dataDateFormat.parse(txtDataNasc.getText().toString());
            pessoa.setDtNasc(nasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  pessoa;

    }
    public void btnEnviar_Click(View view){
      Pessoa pessoa =  PopularPesoa();
        if (ValidarPessoa(pessoa) == false ){
            pessoaRepository.SalvarPessoa(pessoa);
            startActivity(new Intent(this,ListaPessoaActivity.class));
            finish();
        }

    }
    public  void SalvarPessoa(Pessoa pessoa){

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
}
