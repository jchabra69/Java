package org.example;

public interface ColeccionSimpleGenerica <T> {

    public boolean estaVacia();
    public T extraer();
    public T primero();
    public void aniadir (T e);

}
