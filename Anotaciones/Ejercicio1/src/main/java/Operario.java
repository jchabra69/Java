public class Operario extends Empleado {

    protected Integer codigoTaller;


    public Operario(String tlf, String dni, String direccion, String apellidos, String nombre) {
        super(tlf, dni, direccion, apellidos, nombre);
    }

    @Override
    public String toString() {
        return "Operario {" +
                "codigoTaller =" + codigoTaller +
                '}';
    }

    public Integer getCodigoTaller() {
        return codigoTaller;
    }

    public void setCodigoTaller(Integer codigoTaller) {
        this.codigoTaller = codigoTaller;
    }
}
