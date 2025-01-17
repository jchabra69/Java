package org.iesbelen.model;

import java.util.Objects;

public class Categoria {

    private int idCategoria;
    private String nombre, descripcion;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return getIdCategoria() == categoria.getIdCategoria();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdCategoria());
    }
}
