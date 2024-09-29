import java.util.ArrayList;

public class Empresa {

    private String nombre;
    private ArrayList<Empleado> misEmpleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.misEmpleados = new ArrayList<>();
    }
}
