public class Oficial extends Operario {

    private String categoria;


    public Oficial(String tlf, String dni, String direccion, String apellidos, String nombre) {
        super(tlf, dni, direccion, apellidos, nombre);
    }

    @Override
    public String toString() {
        return "Oficial{" +
                "categoria='" + categoria + '\'' +
                '}';
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
