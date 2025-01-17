package org.iesbelen.utils;

import java.sql.*;

public class TestConexion {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/comercio";
        String username = "root";
        String password = "12345678";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Conexión exitosa a la base de datos.");

            String query = "SELECT * FROM usuarios";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                System.out.println("Usuarios en la base de datos:");
                while (rs.next()) {
                    int idUsuario = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String password2 = rs.getString("password");
                    String rol = rs.getString("rol");

                    System.out.println("ID Usuario: " + idUsuario + ", Nombre: " + nombre +
                            ", Email: " + email + ", Rol: " + rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
