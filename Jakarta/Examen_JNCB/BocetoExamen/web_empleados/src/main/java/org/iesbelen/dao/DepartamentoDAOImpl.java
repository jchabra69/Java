package org.iesbelen.dao;

import org.iesbelen.model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartamentoDAOImpl extends AbstractDAOImpl implements DepartamentoDAO {

    @Override
    public synchronized void create(Departamento departamento) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO departamento (nombre, presupuesto, gastos) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, departamento.getNombre());
            ps.setDouble(idx++, departamento.getPresupuesto());
            ps.setDouble(idx++, departamento.getGastos());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de departamento con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                departamento.setCodigoDepartamento(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<Departamento> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Departamento> listDepart = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM departamento");
            while (rs.next()) {

                Departamento departamento = new Departamento();
                int idx = 1;

                departamento.setCodigoDepartamento(rs.getInt(idx++));
                departamento.setNombre(rs.getString(idx++));
                departamento.setPresupuesto(rs.getDouble(idx++));
                departamento.setGastos(rs.getDouble(idx++));

                listDepart.add(departamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return listDepart;


    }

    public Optional<Integer> getCountEmpleados(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Optional<Integer> totalEmpleados = Optional.empty();

        try {
            conn = connectDB();

            //El interrogante es para evitar SQL Injection
            ps = conn.prepareStatement("SELECT COUNT(codigo_departamento) FROM empleado WHERE codigo_departamento = ?");

            int idx =  1; //Primer interrogante
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if(rs.next()) {

                totalEmpleados = Optional.of(rs.getInt(1));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return totalEmpleados;

    }

    @Override
    public Optional<Departamento> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Departamento departamento) {

    }

    @Override
    public void delete(int id) {

    }

}
