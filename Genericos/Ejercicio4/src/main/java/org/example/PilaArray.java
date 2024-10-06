package org.example;

import org.example.ColeccionSimpleGenerica;

public class PilaArray<T> implements ColeccionSimpleGenerica {

    private T[] arrayGenerico;
    private Integer contadorObjetosInsertados;

    public PilaArray(Integer tamano) {

        if (tamano >= 0) {

            arrayGenerico = (T[]) new Object[tamano];

        }

        contadorObjetosInsertados = 0;
    }

    @Override
    public boolean estaVacia() {
        return contadorObjetosInsertados == 0;
    }

    @Override
    public T extraer() {

        T copia = null;

        //Hago una copia de lo que hay para devolverla después
        copia = arrayGenerico[contadorObjetosInsertados - 1];

        //Elimino el último eloemento que se ha insertado
        arrayGenerico[contadorObjetosInsertados - 1] = null;

        return copia;


    }

    //Devuelve el primer elemento de la colección
    @Override
    public T primero() {

        return arrayGenerico[0];

    }


    //Añade un objeto por el extremo que corresponda
    @Override
    public void aniadir(Object e) {

        if (e != null) {

            arrayGenerico[0] = (T) e;

        }


    }
}



