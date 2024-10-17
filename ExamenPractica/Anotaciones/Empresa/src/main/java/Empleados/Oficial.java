package Empleados;

public class Oficial extends Operario {

    private String categoria;

    public Oficial(String nombre, String apellidos, String direccion, String dni, String telefono, Integer codigoTaller, String categoria) {
        super(nombre, apellidos, direccion, dni, telefono, codigoTaller);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Oficial{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", codigoTaller=" + codigoTaller +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
