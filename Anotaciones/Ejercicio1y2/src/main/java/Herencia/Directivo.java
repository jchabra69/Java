package Herencia;

public class Directivo extends Empleado {

    private Integer codigoDespacho;

    public Directivo(String tlf, String dni, String direccion, String apellidos, String nombre, String clase, Integer codigoDespacho) {
        super(tlf, dni, direccion, apellidos, nombre, clase);
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
        return "Directivo {" +
                "\n  " + super.toString() +
                ",\n  Código Despacho: " + codigoDespacho +
                "\n}";
    }

}
