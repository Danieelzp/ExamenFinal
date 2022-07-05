package com.hellokh.sovary.examenfinal;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Mesa implements Serializable
{

    @Exclude
    private String key;
    private String numero;
    private boolean ocupado;

    public Mesa(){}
    public Mesa(String numero, boolean ocupado)
    {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}