package org.iesbelen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesbelen.model.Departamento;
import org.iesbelen.model.Empleado;

public class EmpleadoDAOImpl extends AbstractDAOImpl implements EmpleadoDAO {

    @Override
    public void create(Empleado empleado) {


    }

    @Override
    public List<Empleado> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Empleado> listEmp = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM empleado");
            while (rs.next()) {

                Empleado emp = new Empleado();
                int idx = 1;

                emp.setCodigoEmpleado(rs.getInt(idx++));
                emp.setDni(rs.getString(idx++));
                emp.setNombre(rs.getString(idx++));
                emp.setApellido1(rs.getString(idx++));
                emp.setApellido2(rs.getString(idx++));
                emp.setCodigoDepartamento(rs.getInt(idx++));

                listEmp.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return listEmp;


    }

    //Para editar luego
    @Override
    public void update(Empleado empleado) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE producto SET nif = ?,nombre = ?,apellido1 = ?, apellido2 = ?  WHERE codigo = ?");
            int idx = 1;
            ps.setString(idx++, empleado.getDni());
            ps.setString(idx++, empleado.getNombre());
            ps.setString(idx++, empleado.getApellido1());
            ps.setString(idx++, empleado.getApellido2());


            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de empleado con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public Optional<Empleado> find(int id) {
        return Optional.empty();
    }


    @Override
    public void delete(int id) {

    }
}
