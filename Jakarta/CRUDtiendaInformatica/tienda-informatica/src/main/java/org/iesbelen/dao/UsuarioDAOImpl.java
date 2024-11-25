package org.iesbelen.dao;

import org.iesbelen.model.Producto;
import org.iesbelen.model.Usuario;
import org.iesbelen.utils.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.iesbelen.dao.AbstractDAOImpl.closeDb;
import static org.iesbelen.dao.AbstractDAOImpl.connectDB;

public class UsuarioDAOImpl implements UsuarioDAO {


    /**
     * Inserta en base de datos el nuevo USUARIO, actualizando el id en el bean usuario.
     */

    @Override
    public synchronized void create(Usuario usuario) throws NoSuchAlgorithmException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        // Generar el hash de la contraseña
        //String hashedPassword = HashUtil.hashPassword(usuario.getPassword());  // Aquí generamos el hash

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO usuarios (usuario,password,rol) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, usuario.getNombreUsuario());
            ps.setString(idx++, usuario.getPassword()); //Aquí debería generar la contraseña con la clase HashUtil no?
            ps.setString(idx++, usuario.getRol());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de usuario con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                usuario.setIdUsuario(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Devuelve lista con todos los usuarios.
     */
    @Override
    public List<Usuario> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Usuario> listUsers = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setIdUsuario(rs.getInt(idx++));
                usuario.setNombreUsuario(rs.getString(idx++));
                usuario.setPassword(rs.getString(idx++));
                usuario.setRol(rs.getString(idx++));
                listUsers.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listUsers;

    }

    /**
     * Devuelve Optional de usuario con el ID dado.
     */
    @Override
    public Optional<Usuario> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM usuarios WHERE idUsuario = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setIdUsuario(rs.getInt(idx++));
                usuario.setNombreUsuario(rs.getString(idx++));
                usuario.setPassword(rs.getString(idx++));
                usuario.setRol(rs.getString(idx));

                return Optional.of(usuario);
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

    /**
     * Actualiza usuario con campos del bean usuario según ID del mismo.
     */
    @Override
    public void update(Usuario usuario) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();

            // Obtener la contraseña sin hashear del objeto Usuario
            String passwordToUpdate = usuario.getPassword();

            /*EN BASES DE DATOS, TODO SE RECIBE COMO STRING, POR LO TANTO LA CONTRASEÑA YA DEBE VENIR
            //HASHEADA DE FUERA*/

            ps = conn.prepareStatement("UPDATE usuarios SET usuario = ?, password = ?, rol = ? WHERE idUsuario = ?");
            int idx = 1;
            ps.setString(idx++, usuario.getNombreUsuario());
            ps.setString(idx++, passwordToUpdate); // Aquí se setea la contraseña sin hashear
            ps.setString(idx++, usuario.getRol());
            ps.setInt(idx, usuario.getIdUsuario());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Update de usuario con 0 registros actualizados.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }



    /**
     * Borra usuario con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("DELETE FROM usuarios WHERE idUsuario = ?");
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Delete de usuario con 0 registros eliminados.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

}
