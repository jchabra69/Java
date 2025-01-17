package org.iesbelen.dto;

import org.iesbelen.dao.DepartamentoDAOImpl;
import org.iesbelen.model.Departamento;

public class DepartamentoDTO {

    private int codigoDepartamento, totalEmpleado;
    private String nombre;
    private double presupuesto, gastos;

    public DepartamentoDTO(Departamento d) {

        DepartamentoDAOImpl depDao = new DepartamentoDAOImpl();

        this.codigoDepartamento = d.getCodigoDepartamento();
        this.nombre = d.getNombre();
        this.totalEmpleado = depDao.getCountEmpleados(d.getCodigoDepartamento()).orElse(0);

    }

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public int getTotalEmpleado() {
        return totalEmpleado;
    }

    public void setTotalEmpleado(int totalEmpleado) {
        this.totalEmpleado = totalEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }
}
