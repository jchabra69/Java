package Herencia;

public class Oficial extends Operario {

    private String categoria;

    public Oficial(String tlf, String dni, String direccion, String apellidos, String nombre, String clase, Integer codigoTaller, String categoria) {
        super(tlf, dni, direccion, apellidos, nombre, clase, codigoTaller);
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
        return "Oficial {" +
                "\n  " + super.toString() +
                ",\n  Categoría: '" + categoria + '\'' +
                "\n}";
    }

}
