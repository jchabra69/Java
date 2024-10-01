package Herencia;

public class Directivo extends Empleado {

    private Integer codigoDespacho;

    public Directivo(String tlf, String dni, String direccion, String apellidos, String nombre) {
        super(tlf, dni, direccion, apellidos, nombre);
    }

    public Integer getCodigoDespacho() {
        return codigoDespacho;
    }

    public void setCodigoDespacho(Integer codigoDespacho) {
        this.codigoDespacho = codigoDespacho;
    }
}
