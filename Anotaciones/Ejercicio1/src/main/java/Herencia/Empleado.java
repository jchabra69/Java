package Herencia;

public class Empleado {

    protected String nombre, apellidos, direccion, dni, tlf;

    public Empleado(String tlf, String dni, String direccion, String apellidos, String nombre) {
        this.tlf = tlf;
        this.dni = dni;
        this.direccion = direccion;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Empleado {Nombre: " + nombre + ", Apellidos: " + apellidos + ", Dirección: " + direccion +
                ", DNI: " + dni + ", Teléfono: " + tlf + "}";
    }
}
