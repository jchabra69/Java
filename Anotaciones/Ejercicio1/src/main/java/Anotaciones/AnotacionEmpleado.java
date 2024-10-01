package Anotaciones;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited //Herencia.Empleado Anotacion
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AnotacionEmpleados.class)

public @interface AnotacionEmpleado {

    String nombre() default "None";

    String apellidos() default "None";

    String dni() default "None";

    String direccion() default "None";

    String telefono() default "None";

    String clase() default "None";

    int codigoDespacho() default 0; //Herencia.Directivo

    int codigoTaller() default 0; //Operativo

    String perfil() default "None"; //Técnico

    String categoria() default "None"; //Herencia.Oficial


}
