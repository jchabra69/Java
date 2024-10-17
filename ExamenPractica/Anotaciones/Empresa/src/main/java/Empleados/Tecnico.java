package Empleados;

public class Tecnico extends Operario{

    private String perfil;


    public Tecnico(String nombre, String apellidos, String direccion, String dni, String telefono, Integer codigoTaller) {
        super(nombre, apellidos, direccion, dni, telefono, codigoTaller);
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", codigoTaller=" + codigoTaller +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
