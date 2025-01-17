package org.iesbelen.dao;

import org.iesbelen.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOImpl extends AbstractDAOImpl implements ProductoDAO{


    @Override
    public synchronized void create(Producto producto) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rsGenKeys = null;

        try {

            conn = connectDB();


            String sql = "INSERT INTO Productos (nombre, descripcion, precio, imagen, categoriaID) VALUES (?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setString(idx++, producto.getDescripcion());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setString(idx++, producto.getImagen());
            ps.setInt(idx++, producto.getCategoriaID());


            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("INSERT del producto con 0 filas insertadas.");
            }


            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
                producto.setIdProducto(rsGenKeys.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
        } finally {

            closeDb(conn, ps, rsGenKeys);
        }
    }


    @Override
    public List<Producto> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Producto> listProd = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                Producto prod = new Producto();
                int idx = 1;
                prod.setIdProducto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setImagen(rs.getString(idx++));
                prod.setCategoriaID(rs.getInt(idx));
                listProd.add(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listProd;

    }



    @Override
    public Optional<Producto> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM productos WHERE id = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Producto prod = new Producto();
                idx = 1;
                prod.setIdProducto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setImagen(rs.getString(idx++));
                prod.setCategoriaID(rs.getInt(idx));

                return Optional.of(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public void update(Producto producto) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, imagen = ? WHERE id = ?");
            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setString(idx++, producto.getDescripcion());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setString(idx++, producto.getImagen());
            ps.setInt(idx, producto.getIdProducto());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de producto con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Productos WHERE id = ?";

        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se eliminó el producto con ID " + id);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Producto> getOrdenadoPorNombreDesc() {
        return List.of();
    }

    @Override
    public List<Producto> getOrdenadoPorNombreAsc() {
        return List.of();
    }

    @Override
    public List<Producto> getOrdenadoPorPrecioAsc() {
        return List.of();
    }

    @Override
    public List<Producto> getOrdenadoPorPrecioDesc() {
        return List.of();
    }
}
