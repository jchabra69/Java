package Tests;

import Clases.PilaArray;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PilaArrayTest {

    private PilaArray<Integer> pila;

    @BeforeEach
    void setUp() {
        pila = new PilaArray<>();
    }

    @Test
    void testEstaVacia() {
        // Al crear una pila, debería estar vacía
        assertTrue(pila.estaVacia(), "La pila debería estar vacía al inicio.");

        // Añadir un elemento
        pila.aniadir(10);
        assertFalse(pila.estaVacia(), "La pila no debería estar vacía después de añadir un elemento.");

        // Extraer el único elemento
        pila.extraer();
        assertTrue(pila.estaVacia(), "La pila debería estar vacía después de extraer el único elemento.");
    }

    @Test
    void testExtraer() {
        // Extraer de una pila vacía debería devolver null
        assertNull(pila.extraer(), "Extraer de una pila vacía debería devolver null.");

        // Añadir elementos a la pila
        pila.aniadir(10);
        pila.aniadir(20);
        pila.aniadir(30);

        // Extraer debería devolver el último elemento (LIFO)
        assertEquals(30, pila.extraer(), "El primer elemento extraído debería ser el último añadido (30).");
        assertEquals(20, pila.extraer(), "El segundo elemento extraído debería ser 20.");
        assertEquals(10, pila.extraer(), "El tercer elemento extraído debería ser 10.");

        // Ahora la pila debería estar vacía
        assertNull(pila.extraer(), "Extraer de una pila vacía debería devolver null.");
    }

    @Test
    void testPrimero() {
        // Primero en una pila vacía debería devolver null
        assertNull(pila.primero(), "Primero de una pila vacía debería devolver null.");

        // Añadir elementos
        pila.aniadir(10);
        pila.aniadir(20);

        // Primero debería devolver el último elemento añadido sin extraerlo
        assertEquals(20, pila.primero(), "Primero debería devolver el último elemento añadido (20).");

        // Extraer el elemento
        pila.extraer();

        // Ahora el primero debería ser el 10
        assertEquals(10, pila.primero(), "Después de extraer, primero debería devolver 10.");
    }

    @Test
    void testAniadir() {
        // Añadir elementos a la pila y verificar si están correctamente
        pila.aniadir(10);
        assertEquals(10, pila.primero(), "Después de añadir 10, primero debería devolver 10.");

        pila.aniadir(20);
        assertEquals(20, pila.primero(), "Después de añadir 20, primero debería devolver 20.");

        pila.aniadir(30);
        assertEquals(30, pila.primero(), "Después de añadir 30, primero debería devolver 30.");

        // Verificar que los elementos son extraídos en orden LIFO
        assertEquals(30, pila.extraer(), "Debería extraer 30.");
        assertEquals(20, pila.extraer(), "Debería extraer 20.");
        assertEquals(10, pila.extraer(), "Debería extraer 10.");
    }
}
