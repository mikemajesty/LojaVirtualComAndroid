package com.br.spellsoft.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.WindowManager;

import com.br.spellsoft.Activity.R;
import com.br.spellsoft.Entidades.TipoMessage;

/**
 * Created by mike on 16/01/16.
 */
public class CustomMessage {

    public static void showMsgConfirm(final Activity activity, String titulo, String txt, TipoMessage tipoMsg, DialogInterface.OnClickListener listener) {
        /*int theme = 0, icone = 0;
        switch (tipoMsg) {
            case Sucesso:
                theme = R.style.AppTheme_Dark_Dialog_Sucesso;
                icone = R.drawable.success;
                break;
            case INFO:
                theme = R.style.AppTheme_Dark_Dialog_Info;
                icone = R.drawable.info;
                break;
            case ERRO:
                theme = R.style.AppTheme_Dark_Dialog_Error;
                icone = R.drawable.error;
                break;
            case ALERTA:
                theme = R.style.AppTheme_Dark_Dialog_Alert;
                icone = R.drawable.alert;
                break;
        }*/
        final AlertDialog alertDialog = new AlertDialog.Builder(activity/*, theme*/).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
       // alertDialog.setIcon(icone);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", listener);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);

    }

}
