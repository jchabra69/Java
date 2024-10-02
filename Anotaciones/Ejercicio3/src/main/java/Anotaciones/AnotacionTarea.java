package Anotaciones;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ContenedorTareas.class)

public @interface AnotacionTarea {

    String tituloTarea() default "";
    String descripcionTarea() default "";
    int diaSemana() default 1;
    String horaInicio() default "";


}
