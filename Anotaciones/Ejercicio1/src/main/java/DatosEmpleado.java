import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited //Empleado Anotacion
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Empleados.class)

public @interface DatosEmpleado {

    String nombre() default "None";

    String apellidos() default "None";

    String dni() default "None";

    String direccion() default "None";

    String telefono() default "None";

    String clase() default "None";

    int codigoDespacho() default 0;

}