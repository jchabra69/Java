package Clases;

import Interfaces.ColeccionSimpleGenerica;

import java.util.LinkedList;
import java.util.List;

public class PilaGenerica<T> implements ColeccionSimpleGenerica<T> {

    private List<T> miPila;

    //LinkedList mantiene orden de insercion en los elementos
    public PilaGenerica() {
        this.miPila = new LinkedList<T>();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (T elemento : miPila) {

            sb.append("Pila: ").append(elemento).append("\n");

        }

        return sb.toString();

    }

    @Override
    public boolean estaVacia() {

        return miPila.isEmpty();

    }


    @Override
    public T extraer() {

        T elemento = null;

        //Elimina el primer elemento de la colección y lo devuelve para saber cuál se ha eliminado
       elemento = miPila.removeFirst();


        return elemento;

    }

    @Override
    public T primero() {
        return miPila.getFirst();
    }

    @Override
    public void aniadir(T e) {

        //Se pone el primero el último en llegar
        miPila.addFirst(e);

    }

    }
