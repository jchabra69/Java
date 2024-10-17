package Empleados;

public class Directivo extends Empleado {

    private Integer codigoDespacho;

    public Directivo(String nombre, String apellidos, String direccion, String dni, String telefono, Integer codigoDespacho) {
        super(nombre, apellidos, direccion, dni, telefono);
        this.codigoDespacho = codigoDespacho;
    }

    public Integer getCodigoDespacho() {
        return codigoDespacho;
    }
    public void setCodigoDespacho(Integer codigoDespacho) {
        this.codigoDespacho = codigoDespacho;
    }

    @Override
    public String toString() {
        return "Directivo{" +
                "codigoDespacho=" + codigoDespacho +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
