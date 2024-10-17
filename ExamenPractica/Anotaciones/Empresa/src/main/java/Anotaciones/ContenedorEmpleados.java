package Anotaciones;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)

public @interface ContenedorEmpleados {

    AnotacionEmpleado[] value();

}
