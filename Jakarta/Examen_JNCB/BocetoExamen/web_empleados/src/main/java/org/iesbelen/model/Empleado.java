package org.iesbelen.model;

import java.util.Objects;

public class Empleado {

    private int codigoEmpleado, codigoDepartamento;
    private String dni, nombre, apellido1, apellido2;

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return codigoEmpleado == empleado.codigoEmpleado && codigoDepartamento == empleado.codigoDepartamento && Objects.equals(dni, empleado.dni) && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellido1, empleado.apellido1) && Objects.equals(apellido2, empleado.apellido2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEmpleado, codigoDepartamento, dni, nombre, apellido1, apellido2);
    }
}
