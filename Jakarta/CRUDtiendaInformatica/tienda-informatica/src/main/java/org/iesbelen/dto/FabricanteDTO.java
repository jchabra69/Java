package org.iesbelen.dto;

import org.iesbelen.dao.FabricanteDAO;
import org.iesbelen.dao.FabricanteDAOImpl;
import org.iesbelen.model.Fabricante;
import org.iesbelen.model.Producto;

public class FabricanteDTO {

    private int idFabricante;
    private String nombre;
    private Integer totalProductos;

    public FabricanteDTO(Fabricante f) {

        FabricanteDAOImpl fdao = new FabricanteDAOImpl();

        this.idFabricante = f.getIdFabricante();
        this.nombre = f.getNombre();
        this.totalProductos = fdao.getCountProductos(f.getIdFabricante()).orElse(0);

    }

    public Integer getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Integer totalProductos) {
        this.totalProductos = totalProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }
}
