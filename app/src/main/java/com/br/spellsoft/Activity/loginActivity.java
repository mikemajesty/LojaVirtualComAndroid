package com.br.spellsoft.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.br.spellsoft.BO.LoginBO;

public class loginActivity extends Activity {

    private EditText txtLogin,txtSenha;
    private Button btnLogar;
    private SharedPreferences preferences;
   private  LoginBO loginBO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().hide();

        startarComPreferences();
        mapearControles();
        loginBO = new LoginBO(this);
        btnLogarClick();
    }

    private void startarComPreferences() {
        preferences = getSharedPreferences("pref",Context.MODE_PRIVATE);
        String login = preferences.getString("login",null);
        String senha = preferences.getString("senha",null);

        if ( login != null && senha != null   ){
            startActivity(new Intent(loginActivity.this, DashBoardActivity.class));
            finish();
        }
    }

    private void btnLogarClick() {
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = txtLogin.getText().toString();
                String senha = txtSenha.getText().toString();
                if(!LoginBO.validaCampoLogin(login, senha)){
                    txtLogin.setError("Campo obrigatório");
                    txtSenha.setError("Campo obrigatório");
                }
                else{
                    if (new LoginBO(loginActivity.this).existeLogin(login, senha, preferences)) {
                        startActivity(new Intent(loginActivity.this, DashBoardActivity.class));
                        finish();
                    }
                    else {
                        txtLogin.setError("valor errado");
                        txtSenha.setError("valor errado");
                    }
                }
            }
        });
    }

    private void mapearControles() {
        txtLogin = (EditText)findViewById(R.id.txt_Login);
        txtSenha = (EditText)findViewById(R.id.txt_Senha);
        btnLogar = (Button)findViewById(R.id.btn_logar);
    }







}
