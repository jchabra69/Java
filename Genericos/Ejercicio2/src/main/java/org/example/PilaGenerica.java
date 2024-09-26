package org.example;

import java.util.LinkedList;

public class PilaGenerica<T> implements ColeccionSimpleGenerica<T> {

    private LinkedList<T> lista;

    public PilaGenerica() {

        lista = new LinkedList<>();
    }

    @Override
    public boolean estaVacia() {

        return lista.isEmpty();

    }

    @Override
    public T extraer() {

        return lista.removeFirst();

    }

    @Override
    public T primero() {

        return lista.getFirst();

    }

    @Override
    public void aniadir(T e) {

        lista.addFirst(e);

    }

    @Override
    public String toString() {

        String cadena = "";

        for (T elemento: lista) {

            cadena += elemento.toString() + ", ";

        }

        return cadena;

    }
}
