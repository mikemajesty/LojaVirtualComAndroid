package com.br.spellsoft.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.spellsoft.Activity.R;
import com.br.spellsoft.Entidades.Pessoa;
import com.br.spellsoft.Entidades.TipoMessage;
import com.br.spellsoft.Repository.PessoaRepository;
import com.br.spellsoft.Util.CustomMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListaPessoaActivity extends Activity {

    private ListView lstPessoa;
    private PessoaRepository pessoaRepository;
    private List<Pessoa> listPessoas;
    private int posicaoSelecionada = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);

        this.getActionBar().setTitle("Lista de Pessoa");
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setHomeButtonEnabled(true);


        lstPessoa = (ListView) findViewById(R.id.lstPessoa);
        pessoaRepository = new PessoaRepository(this);

        CarregarLista();
    }

    private void CarregarLista() {
        listPessoas = pessoaRepository.ListarPessoa();
        List<String> valores = new ArrayList<String>();
        for (Pessoa pessoa: listPessoas)
        {
            valores.add(pessoa.getNome());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lstPessoa.setAdapter(arrayAdapter);
        lstPessoa.setOnItemClickListener(clickListenerPessoas);

        lstPessoa.setOnCreateContextMenuListener(contextMenuItem);
        lstPessoa.setOnItemLongClickListener(clickLongItem);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 10:/*editar*/
                Pessoa pessoa =  pessoaRepository.ConsultarPessoaPorID(listPessoas.get(posicaoSelecionada).getId());
                startActivity(new Intent(this,EditarPessoaActivity.class)
                .putExtra("pessoa",pessoa));
                finish();
            break;
            case 20:/*Deletar*/
                CustomMessage.showMsgConfirm(ListaPessoaActivity.this, "Deseja remover a pessoa?", "Remover pessoa", TipoMessage.ALERTA, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Pessoa pessoa =  pessoaRepository.ConsultarPessoaPorID(listPessoas.get(posicaoSelecionada).getId());
                        pessoaRepository.RemovePessoaPorID(pessoa.getId());
                        Toast.makeText(ListaPessoaActivity.this, "Pessoa:  " + listPessoas.get(posicaoSelecionada).getNome()+" Deletado com sucesso", Toast.LENGTH_LONG).show();
                        CarregarLista();
                    }
                });

                break;
        }
        return super.onContextItemSelected(item);
    }
    /*Pega a posição do click long em um menu*/
 private  AdapterView.OnItemLongClickListener clickLongItem = new AdapterView.OnItemLongClickListener() {
     @Override
     public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
         posicaoSelecionada =  position;
       return  false;
     }
 };
    /*Cria o menu de context*/
    private View.OnCreateContextMenuListener contextMenuItem = new View.OnCreateContextMenuListener() {
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
              /*Group,id,posicao*/
            contextMenu.add(1,10,1,"Editar").setIcon(R.drawable.edit).setTitle("Editar Pessoa");
            contextMenu.add(1,20,2,"Deletar").setIcon(R.drawable.delete).setTitle("Deletar Pessoa");
        }
    };
    /*metodo executado apos o click em um listview*/
    private AdapterView.OnItemClickListener clickListenerPessoas = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

          Pessoa pessoa =  pessoaRepository.ConsultarPessoaPorID(listPessoas.get(position).getId()) ;

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
