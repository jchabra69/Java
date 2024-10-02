package Herencia;

public abstract class Operario extends Empleado {

    protected Integer codigoTaller;

    public Operario(String tlf, String dni, String direccion, String apellidos, String nombre, String clase, Integer codigoTaller) {
        super(tlf, dni, direccion, apellidos, nombre, clase);
        this.codigoTaller = codigoTaller;
    }

    public Integer getCodigoTaller() {
        return codigoTaller;
    }

    public void setCodigoTaller(Integer codigoTaller) {
        this.codigoTaller = codigoTaller;
    }

    @Override
    public String toString() {
        return "Operario {" +
                "\n  " + super.toString() +
                ",\n  Código Taller: " + codigoTaller +
                "\n}";
    }


}
