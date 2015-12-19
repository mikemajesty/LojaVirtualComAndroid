package com.br.spellsoft.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.spellsoft.Activity.R;

public class loginActivity extends Activity {

    private EditText txtLogin,txtSenha;
    private Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().hide();
        txtLogin = (EditText)findViewById(R.id.txt_Login);
        txtSenha = (EditText)findViewById(R.id.txt_Senha);
        btnLogar = (Button)findViewById(R.id.btn_logar);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = txtLogin.getText().toString();
                String senha = txtSenha.getText().toString();
                if(!validaCampoLogin(login,senha)){
                    txtLogin.setError("Campo obrigatório");
                    txtSenha.setError("Campo obrigatório");
                    Toast.makeText(loginActivity.this,"Por vafor preencher todos os campos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean validaCampoLogin(String login,String senha){
        return login == "" || "".equals(login) ? false : senha == "" || "".equals(senha)? false :true;
    }



}
