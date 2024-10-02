import Agenda.AgendaSemana;
import Agenda.Tarea;
import Anotaciones.AnotacionTarea;

import java.lang.annotation.Annotation;

public class Main {

    public static void main(String args[]) throws NoSuchMethodException {

        AgendaSemana miAgenda = cargadorAnotaciones();

        System.out.println(miAgenda.toString());


    }

    private static AgendaSemana cargadorAnotaciones() throws NoSuchMethodException, SecurityException {

        AgendaSemana miAgenda = new AgendaSemana();

        Class<?> claseAgenda = AgendaSemana.class;

        //Coge todas las anotaciones de tipo Tarea
        AnotacionTarea[] annotations = claseAgenda.getAnnotationsByType(AnotacionTarea.class);

        //Ahora recorre todas esas anotaciones
        for(AnotacionTarea anotacion : annotations) {

            //Y empieza a sacarle los datos
            String tituloTarea = anotacion.tituloTarea();
            String descripcionTarea = anotacion.descripcionTarea();
            int diaSemana = anotacion.diaSemana();
            String horaInicio = anotacion.horaInicio();

            //Crea un objeto tarea por cada anotación encontrada
            Tarea t = new Tarea(tituloTarea, descripcionTarea, diaSemana, horaInicio);

            //Y lo agregas a la listae

            miAgenda.agregarTarea(t);


        }

        return miAgenda;

    }

}



