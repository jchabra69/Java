package Agenda;

import Anotaciones.AnotacionTarea;

import java.util.ArrayList;
import java.util.List;

@AnotacionTarea(

        tituloTarea = "Deberes",
        descripcionTarea = "Terminar los ejercicios de Javascript",
        diaSemana = 4,
        horaInicio = "17:00"

)

@AnotacionTarea(

        tituloTarea = "Dentista",
        descripcionTarea = "Octogésima revisión de los brackets",
        diaSemana = 5,
        horaInicio = "15:30"

)

@AnotacionTarea(

        tituloTarea = "Encargo",
        descripcionTarea = "Reparar portátil en Carranque",
        diaSemana = 4,
        horaInicio = "18:00"

)


public class AgendaSemana {

    //Lista de tareas semanales
    private List<Tarea> misTareas;

    public AgendaSemana() {
        this.misTareas = new ArrayList<>();
    }

    public boolean agregarTarea(Tarea t) {

        return this.misTareas.add(t);

    }

    //Pa mostrar las tareas
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Tarea t : misTareas) {

            sb.append(t.toString()).append("\n");

        }

        return sb.toString();

    }
}
