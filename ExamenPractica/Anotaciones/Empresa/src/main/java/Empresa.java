import Anotaciones.AnotacionEmpleado;
import Anotaciones.ContenedorEmpleados;
import Empleados.Empleado;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;



@AnotacionEmpleado(
        nombre = "Amancio",
        apellidos = "Ortega",
        dni = "66554433F",
        direccion = "AV.DIPUTACION S/N, P.I. SABON 15142, ARTEIXO, LA CORUÑA",
        telefono = "981185596",
        clase = "Directivo",
        codigoDespacho = 1
)

@AnotacionEmpleado(
        nombre = "Juan",
        apellidos = "Pérez López",
        direccion = "Calle Mayor, 123",
        dni = "12345678A",
        clase = "Mecánico",
        telefono = "654321098",
        codigoTaller = 101,
        codigoDespacho = 0,
        perfil = "Mantenimiento de vehículos",
        categoria = "Oficial de Primera"

)

@AnotacionEmpleado(
        nombre = "María",
        apellidos = "López Fernández",
        direccion = "Calle del Sol, 56",
        dni = "33445566D",
        clase = "Administrativa",
        telefono = "693847562",
        codigoTaller = 0,
        codigoDespacho = 305,
        perfil = "Gestión administrativa",
        categoria = "Administrativa Senior"
)

@AnotacionEmpleado(
        nombre = "Ana",
        apellidos = "García Martínez",
        direccion = "Avenida de la Constitución, 45",
        dni = "87654321B",
        clase = "Ingeniera",
        telefono = "612345678",
        codigoTaller = 0,
        codigoDespacho = 201,
        perfil = "Ingeniería y diseño",
        categoria = "Ingeniera Jefe"
)



public class Empresa {

    private List<Empleado> misEmpleados;
    private String nombre;

    public Empresa(String nombre) {
        misEmpleados = new ArrayList<Empleado>();
        this.nombre = nombre;

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

        for (Empleado e : misEmpleados) {

            sb.append("Empresa: ").append(getNombre()).append(e.toString());

        }

        return sb.toString();

    }

    public static Empresa cargadorDeContexto() {

        Empresa miEmpresa = new Empresa("Modas Loli");

        //De ese objeto empresa, coge su clase para usar Reflection
        Class<?> claseEmpresa = miEmpresa.getClass();

        //Y lee sus anotaciones, para eso haz un array de tipo AnnotationEmpleado y coge de la clase solo las anotaciones de TIPO X
        //Si uso el getAnnotation a secas, todo tiene que ser Annotations y usar ContenedorEmpleados.value para recorrer
        AnotacionEmpleado[] anotacionesEmpleados = claseEmpresa.getAnnotationsByType(AnotacionEmpleado.class);

        //Para cada objeto anotacion
        for (AnotacionEmpleado anotacionEmpleado: anotacionesEmpleados) {

            //Convierte la anotacion a AnotacionEmpleado

            //Crea estos atributos en base a lo que cojas de la anotación empleado
            String nombre = anotacionEmpleado.nombre();
            String apellidos = anotacionEmpleado.apellidos();
            String dni = anotacionEmpleado.dni();
            String direccion = anotacionEmpleado.direccion();
            String telefono = anotacionEmpleado.telefono();

            Empleado e = new Empleado(nombre, apellidos, dni, direccion, telefono);

            miEmpresa.misEmpleados.add(e);

        }

        return miEmpresa;

    }


}


