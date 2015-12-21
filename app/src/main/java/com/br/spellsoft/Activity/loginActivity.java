package com.br.spellsoft.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends Activity {

    private EditText txtLogin,txtSenha;
    private Button btnLogar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().hide();

        preferences = getPreferences(Context.MODE_PRIVATE);
        String login = preferences.getString("login",null);
        String senha = preferences.getString("senha",null);

        if ( login != null && senha != null   ){
            startActivity(new Intent(loginActivity.this, MainActivity.class));
            finish();
        }
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
                }
                else{
                    if (existeLogin(login,senha)) {
                        startActivity(new Intent(loginActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }
        });
    }

    private boolean existeLogin(String login,String senha) {
        if (!login.equals("admin") || !senha.equals("admin")) {
            txtLogin.setError("valor errado");
            txtSenha.setError("valor errado");
            return false;
        }
        else {
            Editor editor = preferences.edit();
            editor.putString("login",login);
            editor.putString("senha",senha).commit();
            return true;
        }
    }

    private boolean validaCampoLogin(String login,String senha){
        return login == "" || "".equals(login) ? false : senha == "" || "".equals(senha)? false :true;
    }



}
