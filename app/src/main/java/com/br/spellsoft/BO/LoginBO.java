package com.br.spellsoft.BO;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.br.spellsoft.Repository.LoginRepository;

/**
 * Created by mike on 21/12/15.
 */
public class LoginBO  {



    private LoginRepository loginRepository;

    public LoginBO(Activity activity){
        loginRepository = new LoginRepository(activity);
        loginRepository.ListarLogin(activity);
    }
    public static boolean validaCampoLogin(String login,String senha){
        return login == "" || "".equals(login) ? false : senha == "" || "".equals(senha)? false :true;
    }
    public boolean existeLogin(String login,String senha, SharedPreferences preferences ) {
        boolean result = loginRepository.Deletar();
        Log.v("Detetado",""+result);
        if (!login.equals("admin") || !senha.equals("admin")) {

            return false;
        }
        else {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("login",login);
            editor.putString("senha",senha).commit();
            return true;
        }
    }
    public static void deslogar(String login,String senha, SharedPreferences preferences){

    }
}
