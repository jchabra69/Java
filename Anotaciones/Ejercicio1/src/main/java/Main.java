import Anotaciones.AnotacionEmpleado;
import Anotaciones.AnotacionDirectivo;
import Anotaciones.AnotacionOficial;
import Anotaciones.AnotacionTecnico;
import Herencia.Directivo;
import Herencia.Empleado;
import Herencia.Oficial;
import Herencia.Tecnico;

import java.lang.annotation.Annotation;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {


        Empresa miEmpresa = cargadorDeContexto3();

        System.out.println(miEmpresa.toString());


    }


    //Ok, ten en cuenta que si hay varias anotaciones, este MÉTODO NO FUNCIONARÁ
    //Ya que el propio Java-if no sabe cuál elegir

    //APRENDIENDO JAVA REFLECTION CON ANOTACIONES
    //Procesa las anotaciones de Empresa, es decir, carga cada anotacion a un empleado y lo agrega al array
    public static Empresa cargadorDeContexto() throws NoSuchMethodException, SecurityException {

        /* ESQUEMA MENTAL
        1. Leer anotaciones
        2. Crea un empleado con la anotación
        3. Carga el empleado en el ArrayList de Empresa
         */

        //DEBO USAR JAVA RELECTION PARA LEER LAS ANOTACIONES

        Empresa miEmpresa = new Empresa("Modas Loli");

        //Cojo la clase del objeto ya que mi anotación es a nivel de CLASE
        Class<?> claseEmpresa = miEmpresa.getClass();

        //Si la clase Empresa tiene una anotación de Empleado
        if (claseEmpresa.isAnnotationPresent(AnotacionEmpleado.class)) {

            //Crea un objeto anotación a partir de la anotación que cojas de la clase Empresa
            AnotacionEmpleado anotacionEmpleado = claseEmpresa.getAnnotation(AnotacionEmpleado.class);

            //Ahora, para crear un empleado
            //Cojo cada atributo de la anotación y lo paso a una variable local aquí
            String nombre = anotacionEmpleado.nombre();
            String apellidos = anotacionEmpleado.apellidos();
            String dni = anotacionEmpleado.dni();
            String direccion = anotacionEmpleado.direccion();
            String telefono = anotacionEmpleado.telefono();
            String clase = anotacionEmpleado.clase();
            //String clase = anotacionEmpleado.clase(); //NO ES UN ATRIBUTO, SINO QUE ESPECIFICA DE QUÉ HIJO VIENE

            /* Estos pueden DAR ERROR PORQUE NO SON ATRIBUTOS DE EMPLEADO
            int codigoDespacho = anotacionEmpleado.codigoDespacho();
            int codigoTaller = anotacionEmpleado.codigoTaller();
            String perfil = anotacionEmpleado.perfil();
            String categoria = anotacionEmpleado.categoria(); */

            //Entonces creo un empleado con esas variables
            Empleado e = new Empleado(nombre, apellidos, dni, direccion, telefono, clase);

            miEmpresa.agregarEmpleado(e);

        }


        return miEmpresa;

    }

    //Por lo que debo crear uno, que lea todas las anotaciones

    //Hay que elevar excepción o me dará error en el main
    public static Empresa cargadorDeContexto2() throws NoSuchMethodException, SecurityException {

        Empresa miEmpresa = new Empresa("Modas Paca");

        //Necesito hacer el espejo empresa, esta anotación trabaja a nivel CLASE
        Class<?> claseEmpresa = miEmpresa.getClass();

        //Tengo que obtener todas las anotaciones de la clase Empresa

        AnotacionEmpleado[] anotacionesEmpresa = claseEmpresa.getAnnotationsByType(AnotacionEmpleado.class);

        //Para así poder recorrer todas las anotaciones
        for (AnotacionEmpleado anotacion : anotacionesEmpresa) {

            //Ahora tengo que extraer el dato de cada anotación
            //Ya no tengo que preguntar si es de tipo AnootacionEmpleado, ya que el getAnnotationsByType lo ha hecho

            //El tema ahora es que, hay anotaciones que tienen atributos diferentes o son de clases diferentes

            //ATRIBUTOS COMUNES DE CADA EMPLEADO
            String nombre = anotacion.nombre();
            String apellidos = anotacion.apellidos();
            String dni = anotacion.dni();
            String direccion = anotacion.direccion();
            String telefono = anotacion.telefono();
            String clase = anotacion.clase();

            //ES PARECIDO AL INSTANCEOF QUE HACÍA CON JEFE, GUERRERO ETC, TODOS PUEDEN ESTAR EN EL MISMO ARRAYLIST

            //Por lo tanto, voy a preguntar a esa anotación, si es de tipo Directivo, Tecnico u Oficial
            //Pero creo que esto ya es parte del ejercicio2...

            //Así que creo un empleado por cada anotación

            Empleado e = new Empleado(nombre, apellidos, dni, direccion, telefono, clase);

            miEmpresa.agregarEmpleado(e);



        }

        return miEmpresa;

    }

    //Ejercicio2
    public static Empresa cargadorDeContexto3() throws NoSuchMethodException, SecurityException {

        Empresa miEmpresa = new Empresa("Modas Zuripanta");

        Class<?> claseEmpresa = miEmpresa.getClass();

        //Cojo todas las anotaciones de la clase Empresa
        //En este caso, cojo todas las anotaciones ya que tengo distintas y no es como es en el ejercicio anterior que sabía el tipo
        Annotation[] anotaciones = claseEmpresa.getAnnotations();

        //Las leo y pregunto de qué tipo son
        for (Annotation anotacion: anotaciones) {

            //Si son de uno de esos tres tipos, coge sus datos y mételas en el Array
            if (anotacion instanceof AnotacionTecnico) {

                //Castea la anotación a Anotación de Técnico
                AnotacionTecnico Atecnico = (AnotacionTecnico) anotacion;

                String nombre = Atecnico.nombre();
                String apellidos = Atecnico.apellidos();
                String dni = Atecnico.dni();
                String direccion = Atecnico.direccion();
                String telefono = Atecnico.telefono();
                String clase = Atecnico.clase();
                int codigoTaller = Atecnico.codigoTaller();
                String perfil = Atecnico.perfil();

                Tecnico t = new Tecnico(nombre, apellidos, dni, direccion, telefono, clase, codigoTaller, perfil);

                miEmpresa.agregarEmpleado(t);


            } else if (anotacion instanceof AnotacionDirectivo) {

                //Castea la anotación a Anotación de Directivo
                AnotacionDirectivo Adirectivo = (AnotacionDirectivo) anotacion;

                String nombre = Adirectivo.nombre();
                String apellidos = Adirectivo.apellidos();
                String dni = Adirectivo.dni();
                String direccion = Adirectivo.direccion();
                String telefono = Adirectivo.telefono();
                String clase = Adirectivo.clase();
                int codigoDespacho = Adirectivo.codigoDespacho();

                Directivo d = new Directivo(nombre, apellidos, dni, direccion, telefono, clase, codigoDespacho);

                miEmpresa.agregarEmpleado(d);


            } else if (anotacion instanceof AnotacionOficial) {

                //Castea la anotación a Anotación de Oficial
                AnotacionOficial Aoficial = (AnotacionOficial) anotacion;

                String nombre = Aoficial.nombre();
                String apellidos = Aoficial.apellidos();
                String dni = Aoficial.dni();
                String direccion = Aoficial.direccion();
                String telefono = Aoficial.telefono();
                String clase = Aoficial.clase();
                int codigoTaller = Aoficial.codigoTaller();
                String categoria = Aoficial.categoria();

                Oficial o = new Oficial(nombre, apellidos, dni, direccion, telefono, clase, codigoTaller, categoria);

                miEmpresa.agregarEmpleado(o);


            }

            //En otro caso, lee la anotación independientemente de cual sea
            //Pero claro jejee, no puedo meterla en el ArrayList a menos que sea tipo Empleado
            else {

                //Igualmente este else no se aplica, ya que no tengo más anotaciones en mi código

            }

        }

        return miEmpresa;

    }

}


