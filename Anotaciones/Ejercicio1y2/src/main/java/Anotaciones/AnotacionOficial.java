package Anotaciones;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//No me piden que se repita

public @interface AnotacionOficial {

    //Debo poner los atributos de empleado y OPERARIO tanto como Oficial
    String nombre() default "None";

    String apellidos() default "None";

    String dni() default "None";

    String direccion() default "None";

    String telefono() default "None";

    String clase() default "Directivo";

    int codigoTaller() default 0;

    String categoria() default "None";

}
