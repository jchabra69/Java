package Anotaciones;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited //Herencia.Empleado Anotacion
@Retention(RetentionPolicy.RUNTIME)

public @interface AnotacionEmpleados {

    /* En Java, para hacer una anotación repetible, debo crear otra
    anotación contenedora, es decir, que guarde todas las variables en un array
    * */

    AnotacionEmpleado[] value();

}
