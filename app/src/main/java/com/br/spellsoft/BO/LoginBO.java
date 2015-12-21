package com.br.spellsoft.BO;

import android.content.SharedPreferences;

/**
 * Created by mike on 21/12/15.
 */
public class LoginBO  {

    public static boolean validaCampoLogin(String login,String senha){
        return login == "" || "".equals(login) ? false : senha == "" || "".equals(senha)? false :true;
    }
    public static boolean existeLogin(String login,String senha, SharedPreferences preferences ) {
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
