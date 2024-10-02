package Herencia;

public class Tecnico extends Operario {

    private String perfil;

    public Tecnico(String tlf, String dni, String direccion, String apellidos, String nombre, String clase, Integer codigoTaller, String perfil) {
        super(tlf, dni, direccion, apellidos, nombre, clase, codigoTaller);
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Técnico {" +
                "\n  " + super.toString() +
                ",\n  Perfil: '" + perfil + '\'' +
                "\n}";
    }
}
