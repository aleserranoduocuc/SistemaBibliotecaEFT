package dao;

import modelo.Estudiante;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setNombre(rs.getString("nombre"));
                est.setRut(rs.getString("rut"));
                est.setCurso(rs.getString("curso"));
                est.setCorreo(rs.getString("correo"));
                lista.add(est);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar estudiantes: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Estudiante est) {
        String sql = "INSERT INTO estudiantes (nombre, rut, curso, correo) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getRut());
            ps.setString(3, est.getCurso());
            ps.setString(4, est.getCorreo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Estudiante est) {
        String sql = "UPDATE estudiantes SET nombre=?, rut=?, curso=?, correo=? WHERE id=?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getRut());
            ps.setString(3, est.getCurso());
            ps.setString(4, est.getCorreo());
            ps.setInt(5, est.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id=?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }
}