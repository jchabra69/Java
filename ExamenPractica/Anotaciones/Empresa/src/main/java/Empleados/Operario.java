package Empleados;

public class Operario extends Empleado {

    protected Integer codigoTaller;

    public Operario(String nombre, String apellidos, String direccion, String dni, String telefono, Integer codigoTaller) {
        super(nombre, apellidos, direccion, dni, telefono);
        this.codigoTaller = codigoTaller;
    }

    public Integer getCodigoTaller() {
        return codigoTaller;
    }
    public void setCodigoTaller(Integer codigoTaller) {
        this.codigoTaller = codigoTaller;
    }

    @Override
    public String toString() {
        return "Operario{" +
                "codigoTaller=" + codigoTaller +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
