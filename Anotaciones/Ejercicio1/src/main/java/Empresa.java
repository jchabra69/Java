import Anotaciones.AnotacionEmpleado;
import Herencia.Empleado;

import java.lang.reflect.Method;
import java.util.ArrayList;

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

public class Empresa {

    private String nombre;
    private ArrayList<Empleado> misEmpleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.misEmpleados = new ArrayList<>();
    }

    public boolean agregarEmpleado(Empleado empleado) {

        return misEmpleados.add(empleado);

    }

    //Procesa las anotaciones de Empresa, es decir, carga cada anotacion a un empleado y lo agrega al array
    public static Empresa cargadorDeContexto() throws NoSuchMethodException, SecurityException {

        Empresa miEmpresa = new Empresa("Testing");

        //Necesito este método para usarlo con las anotaciones y agregar lo que se lea de ellas al ArrayList
        Method m = miEmpresa.getClass().getMethod("agregarEmpleado", Empleado.class);

        //Leo la anotación
        AnotacionEmpleado miAnotacion = m.getAnnotation(AnotacionEmpleado.class);

        //Y uso el método de agregar de mi objeto Empresa junto a la lectura de la Anotación

        miEmpresa.agregarEmpleado((Empleado) miAnotacion); //No puedo hacer esto, comerme el tarro (?)

        return miEmpresa;
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
