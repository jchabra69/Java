package Clases;

import Interfaces.ColeccionSimpleGenerica;
import java.util.Arrays;

public class PilaArray<T> implements ColeccionSimpleGenerica<T> {

    private T[] pilaArray;
    private int contadorInsertados;

    // En Java no se pueden hacer arrays genéricos
    @SuppressWarnings("unchecked")
    public PilaArray() {
        this.contadorInsertados = 0;
        this.pilaArray = (T[]) new Object[10];  // Tamaño inicial de 10
    }

    @Override
    public boolean estaVacia() {
        // Devuelve true si no hay elementos insertados
        return contadorInsertados == 0;
    }

    @Override
    public T extraer() {
        if (estaVacia()) {
            return null; // O puedes lanzar una excepción
        }

        // Extraer el último elemento (LIFO: Last In First Out)
        T elemento = pilaArray[--contadorInsertados];
        pilaArray[contadorInsertados] = null;  // Evitar mantener referencias innecesarias
        pilaArray = Arrays.copyOf(pilaArray, contadorInsertados);  // Redimensionar array

        return elemento;
    }

    @Override
    public T primero() {
        if (estaVacia()) {
            return null;  // O lanzar una excepción
        }
        return pilaArray[contadorInsertados - 1];  // Retorna el último elemento
    }

    @Override
    public void aniadir(T elemento) {

        // Si no hay más espacio en el array, lo redimensionamos
        if (contadorInsertados == pilaArray.length) {
            pilaArray = Arrays.copyOf(pilaArray, pilaArray.length * 2);  // Duplicar tamaño
        }

        // Añadir el elemento al final
        pilaArray[contadorInsertados++] = elemento;
    }
}
