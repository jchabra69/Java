package org.example;

public class PilaArray<T> implements ColeccionSimpleGenerica {

    private T[] arrayGenerico;
    private Integer contadorObjetosInsertados;

    public PilaArray(Integer tamano) {
        arrayGenerico = (T[]) new Object[tamano];
        contadorObjetosInsertados = 0;
    }

    @Override
    public boolean estaVacia() {
        return contadorObjetosInsertados == 0;
    }

    @Override
    public T extraer() {

        //En una pila, sale primero el último
        T elementoAExtraer = arrayGenerico[0];

        arrayGenerico[0] = null;

        return elementoAExtraer;
    }

    @Override
    public T primero() {

        return arrayGenerico[0];

    }

    @Override
    public void aniadir(Object e) {

        if (e != null) {

            arrayGenerico[0] = (T) e;

        }


    }
}
