package com.bolsadeideas.springboot.datajpa.app.paginator;

public class PageItem {

    private int numero;
    private boolean isActual;

    public PageItem(int numero, boolean isActual) {
        this.numero = numero;
        this.isActual = isActual;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isActual() {
        return isActual;
    }
}
