import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited //Empleado Anotacion
@Retention(RetentionPolicy.RUNTIME)

public @interface Empleados {

    DatosEmpleado[] value();

}
