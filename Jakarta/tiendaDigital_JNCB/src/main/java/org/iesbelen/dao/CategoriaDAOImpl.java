package org.iesbelen.dao;

import org.iesbelen.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDAOImpl extends AbstractDAOImpl implements CategoriaDAO {

    @Override
    public synchronized void create(Categoria categoria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rsGenKeys = null;

        try {
            // Establece la conexión con la base de datos
            conn = connectDB();

            // Consulta SQL para insertar una nueva categoría
            String sql = "INSERT INTO Categorias (nombre, descripcion) VALUES (?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Establecer los valores en la consulta SQL
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());

            // Ejecutar la consulta
            int rows = ps.executeUpdate();

            // Verifica si la inserción fue exitosa
            if (rows == 0) {
                System.out.println("INSERT de la categoría con 0 filas insertadas.");
            }

            // Obtener la clave generada para la nueva categoría
            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
                categoria.setIdCategoria(rsGenKeys.getInt(1)); // Asigna el ID generado
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rsGenKeys);
        }
    }

    @Override
    public List<Categoria> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Categoria> listCat = new ArrayList<>();

        try {
            conn = connectDB();

            // Consulta SQL para obtener todas las categorías
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM Categorias");

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                cat.setDescripcion(rs.getString("descripcion"));
                listCat.add(cat);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listCat;
    }

    @Override
    public Optional<Categoria> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            // Consulta SQL para buscar una categoría por su ID
            String sql = "SELECT * FROM Categorias WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                cat.setDescripcion(rs.getString("descripcion"));
                return Optional.of(cat);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public void update(Categoria categoria) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();

            // Consulta SQL para actualizar una categoría
            String sql = "UPDATE Categorias SET nombre = ?, descripcion = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getIdCategoria());

            // Ejecutar la actualización
            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("UPDATE de la categoría con 0 registros actualizados.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();

            // Consulta SQL para eliminar una categoría por su ID
            String sql = "DELETE FROM Categorias WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Ejecutar la eliminación
            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("No se eliminó la categoría con ID " + id);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }
}
