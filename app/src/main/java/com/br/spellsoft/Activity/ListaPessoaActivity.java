package com.br.spellsoft.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.spellsoft.Activity.R;
import com.br.spellsoft.Entidades.Pessoa;
import com.br.spellsoft.Repository.PessoaRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListaPessoaActivity extends Activity {

    private ListView lstPessoa;
    private PessoaRepository pessoaRepository;
    private List<Pessoa> listPessoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);

        this.getActionBar().setTitle("Lista de Pessoa");
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setHomeButtonEnabled(true);


        lstPessoa = (ListView) findViewById(R.id.lstPessoa);
        pessoaRepository = new PessoaRepository(this);

        listPessoas = pessoaRepository.ListarPessoa();
        List<String> valores = new ArrayList<String>();
        for (Pessoa pessoa: listPessoas)
        {
            valores.add(pessoa.getNome());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,valores);
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lstPessoa.setAdapter(arrayAdapter);
        lstPessoa.setOnItemClickListener(clickListenerPessoas);
    }
    private AdapterView.OnItemClickListener clickListenerPessoas = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

          Pessoa pessoa =   listPessoas.get(position);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

          StringBuilder info = new StringBuilder().append("Nome: "+pessoa.getNome() )
                  .append("\nEndereço: "+pessoa.getEndereco())
                  .append("\nCpf:Cnpj: "+pessoa.getCpfCnpj())
                  .append("\nSexo: "+pessoa.getSexo().getDescricao())
                  .append("\nPorfissão:" + pessoa.getProfissao().getDescricao())
                  .append("\nDt_Nasc:" +dateFormat.format(pessoa.getDtNasc()));

            Toast.makeText(ListaPessoaActivity.this,info,Toast.LENGTH_LONG).show();
        }
    };

    public void btnAddPessoa_Click(View view){
        startActivity(new Intent(this,PessoaActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_pessoa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

      switch (id){
          case android.R.id.home:
              finish();
              break;
      }

        return super.onOptionsItemSelected(item);
    }
}
