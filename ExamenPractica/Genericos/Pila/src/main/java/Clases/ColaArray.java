package Clases;

import Interfaces.ColeccionSimpleGenerica;
import java.util.Arrays;

public class ColaArray<T> implements ColeccionSimpleGenerica<T> {

    private T[] colaArray;
    private int contadorInsertados;

    @SuppressWarnings("unchecked")
    public ColaArray(int capacidad) {
        this.colaArray = (T[]) new Object[capacidad];  // Tamaño fijo
        this.contadorInsertados = 0;
    }

    @Override
    public boolean estaVacia() {
        return contadorInsertados == 0;
    }

    @Override
    public T extraer() {
        if (estaVacia()) {
            return null;
        }


        T elemento = colaArray[0];

        // Desplaza todos los elementos a la izquierda
        for (int i = 1; i < contadorInsertados; i++) {
            colaArray[i - 1] = colaArray[i];
        }

        colaArray[--contadorInsertados] = null;  // Eliminar al último elemento
        return elemento;
    }

    @Override
    public T primero() {
        if (estaVacia()) {
            return null;
        }
        return colaArray[0];
    }

    @Override
    public void aniadir(T elemento) {

        if (contadorInsertados == colaArray.length) {

            System.out.println("La cola está llena");
            return;
        }

        // Añade el elemento al final de la cola
        colaArray[contadorInsertados++] = elemento;
    }
}
