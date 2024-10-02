import Anotaciones.AnotacionDirectivo;
import Anotaciones.AnotacionOficial;
import Anotaciones.AnotacionTecnico;
import Herencia.Empleado;

import java.util.ArrayList;

//Ejercicio2
@AnotacionOficial(
        nombre = "Laura",
        apellidos = "Gómez Sánchez",
        dni = "87654321B",
        direccion = "Avenida de la Libertad 45, 03003, Alicante",
        telefono = "965123456",
        codigoTaller = 303,
        categoria = "Jefa de Taller"
)

@AnotacionDirectivo(
        nombre = "Carlos",
        apellidos = "Pérez Fernández",
        dni = "12345678A",
        direccion = "Calle Falsa 123, 28080, Madrid",
        telefono = "911223344",
        codigoDespacho = 202
)

@AnotacionTecnico(
        nombre = "Amancio",
        apellidos = "Ortega",
        dni = "66554433F",
        direccion = "AV. DIPUTACION S/N, P.I. SABON 15142, ARTEIXO, LA CORUÑA",
        telefono = "981185596",
        codigoTaller = 101,
        perfil = "Desarrollador de Software"
)

/* Ejercicio1

//Herencia.Directivo
@AnotacionEmpleado(
        nombre = "Amancio",
        apellidos = "Ortega",
        dni = "66554433F",
        direccion = "AV.DIPUTACION S/N, P.I. SABON 15142, ARTEIXO, LA CORUÑA",
        telefono = "981185596",
        clase = "Herencia.Directivo",
        codigoDespacho = 1
)

@AnotacionEmpleado(
        nombre = "Carlos",
        apellidos = "Pérez Fernández",
        dni = "12345678A",
        direccion = "Calle Falsa 123, 28080, Madrid",
        telefono = "911223344",
        clase = "Herencia.Tecnico",
        codigoDespacho = 5
)


//Herencia.Oficial
@AnotacionEmpleado(
        nombre = "Laura",
        apellidos = "Gómez Sánchez",
        dni = "87654321B",
        direccion = "Avenida de la Libertad 45, 03003, Alicante",
        telefono = "965123456",
        clase = "Herencia.Oficial",
        codigoDespacho = 8
)

*/

public class Empresa {

    private String nombre;
    private final ArrayList<Empleado> misEmpleados;


    public Empresa(String nombre) {
        this.nombre = nombre;
        this.misEmpleados = new ArrayList<>();
    }

    public boolean agregarEmpleado(Empleado empleado) {

        return misEmpleados.add(empleado);

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Empleado empleado : misEmpleados) {

            sb.append(empleado).append("\n");

        }

        return sb.toString();

    }
}
