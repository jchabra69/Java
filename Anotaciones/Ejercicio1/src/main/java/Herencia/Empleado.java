package Herencia;


public class Empleado {

    protected String nombre, apellidos, direccion, dni, tlf, clase;

    public Empleado(String tlf, String dni, String direccion, String apellidos, String nombre, String clase) {
        this.tlf = tlf;
        this.dni = dni;
        this.direccion = direccion;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.clase = this.getClass().getSimpleName(); // Inicializando el atributo clase
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

    // Nuevo getter para el atributo 'clase'
    public String getClase() {
        return clase;
    }

    // Nuevo setter para el atributo 'clase'
    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Empleado {" +
                "Nombre: '" + nombre + '\'' +
                ", Apellidos: '" + apellidos + '\'' +
                ", DNI: '" + dni + '\'' +
                ", Dirección: '" + direccion + '\'' +
                ", Teléfono: '" + tlf + '\'' +
                ", Clase: '" + clase + '\'' + // Incluyendo clase en la salida
                '}';
    }
}


