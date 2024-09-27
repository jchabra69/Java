public class Tecnico extends Operario {

    private String perfil;

    public Tecnico(String tlf, String dni, String direccion, String apellidos, String nombre) {
        super(tlf, dni, direccion, apellidos, nombre);
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "perfil='" + perfil + '\'' +
                '}';
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
