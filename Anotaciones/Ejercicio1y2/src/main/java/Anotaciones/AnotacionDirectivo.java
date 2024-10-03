package Anotaciones;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//No me piden que se repita

public @interface AnotacionDirectivo {

    //Debo poner los atributos de empleado tanto como Directivo
    String nombre() default "None";

    String apellidos() default "None";

    String dni() default "None";

    String direccion() default "None";

    String telefono() default "None";

    String clase() default "Directivo";

    int codigoDespacho() default 0;

}
