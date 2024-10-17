package Anotaciones;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Repeatable(ContenedorEmpleados.class)

public @interface AnotacionEmpleado {

    String nombre() default "";
    String apellidos() default "";
    String direccion() default "";
    String dni() default "";
    String clase()default "";
    String telefono() default "";
    int codigoTaller() default 0;
    int codigoDespacho() default 0;
    String perfil() default "";
    String categoria() default "";

}
