package com.example.admin.appclean.utils;

import android.widget.EditText;

public class Validaciones {


    public boolean isEmptyEditText(EditText edText) {
        return !(edText != null && !edText.getText().toString().isEmpty());
    }


    public String getValorString(EditText campo) {

        if (campo != null & !isEmptyEditText(campo)) {
            String valor = campo.getText().toString().trim();
            return valor;
        } else {
            return "";
        }
    }


    public Double getValorDouble(EditText campo) {
        double valor;
        String cadena;

        if (campo != null & !isEmptyEditText(campo)) {
            cadena = campo.getText().toString().trim();
            valor = Double.parseDouble(cadena);
        } else {
            valor = 0.0;
        }

        return valor;
    }

}
