package org.iesbelen.dao;

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

            ps = conn.prepareStatement("INSERT INTO usuarios (nombre, email, password, rol) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            int idx = 1;
            ps.setString(idx++, usuario.getNombre());
            ps.setString(idx++, usuario.getEmail());
            ps.setString(idx++, usuario.getPassword());
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

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setIdUsuario(rs.getInt(idx++));
                usuario.setNombre(rs.getString(idx++));
                usuario.setEmail(rs.getString(idx++));
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
            ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setIdUsuario(rs.getInt(idx++));
                usuario.setNombre(rs.getString(idx++));
                usuario.setEmail(rs.getString(idx++));
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

            // Aquí pasamos la contraseña hasheada ya desde el Servlet
            String passwordToUpdate = usuario.getPassword(); // Ya está hasheada

            ps = conn.prepareStatement("UPDATE usuarios SET nombre = ?, email = ?, password = ?, rol = ? WHERE id = ?");
            int idx = 1;
            ps.setString(idx++, usuario.getNombre());
            ps.setString(idx++, usuario.getEmail());
            ps.setString(idx++, passwordToUpdate); // Se actualiza con la contraseña hasheada
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
            ps = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
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

    @Override
    public Optional<Usuario> validarUsuario(String nombreUsuario, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ?");
            ps.setString(1, nombreUsuario);

            rs = ps.executeQuery();

            if (rs.next()) {

                String hashedPassword = HashUtil.hashPassword(password);

                // Contraseña de la base de datos
                String storedPassword = rs.getString("password");

                // Comparo los hashes para ver si coincide la contraseña
                if (hashedPassword.equals(storedPassword)) {
                    Usuario usuario = new Usuario();
                    int idx = 1;
                    usuario.setIdUsuario(rs.getInt(idx++));
                    usuario.setNombre(rs.getString(idx++));
                    usuario.setEmail(rs.getString(idx++));
                    usuario.setPassword(rs.getString(idx++));
                    usuario.setRol(rs.getString(idx));

                    return Optional.of(usuario);
                }
            }
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }



}
