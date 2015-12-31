package com.br.spellsoft.Repository;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by mike on 31/12/15.
 */
public class DatePickerFragmant extends DialogFragment {

    private  DatePickerDialog.OnDateSetListener _onDateSetListener;
    private int ano,mes,dia;


    public void  setDateListiner( DatePickerDialog.OnDateSetListener onDateSetListener){
        this._onDateSetListener = onDateSetListener;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        ano = args.getInt("Ano");
        mes = args.getInt("Mes");
        dia = args.getInt("Dia");
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(),_onDateSetListener,ano,mes,dia);
    }
}
