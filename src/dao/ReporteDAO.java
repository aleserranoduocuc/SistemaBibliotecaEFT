package dao;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    // Reporte 1: Libros más prestados (Top 5)
    public List<Object[]> librosMasPrestados() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT l.titulo, l.autor, COUNT(p.id) AS total " +
                "FROM prestamos p " +
                "JOIN libros l ON p.id_libro = l.id " +
                "GROUP BY l.id, l.titulo, l.autor " +
                "ORDER BY total DESC LIMIT 5";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("total")
                });
            }
        } catch (SQLException e) {
            System.out.println("❌ Error reporte 1: " + e.getMessage());
        }
        return lista;
    }

    // Reporte 2: Historial por estudiante
    public List<Object[]> historialPorEstudiante(int idEstudiante) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT l.titulo, p.fecha_prestamo, p.fecha_devolucion, p.devuelto " +
                "FROM prestamos p " +
                "JOIN libros l ON p.id_libro = l.id " +
                "WHERE p.id_estudiante = ? " +
                "ORDER BY p.fecha_prestamo DESC";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString("titulo"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getBoolean("devuelto") ? "Devuelto" : "Prestado"
                });
            }
        } catch (SQLException e) {
            System.out.println("❌ Error reporte 2: " + e.getMessage());
        }
        return lista;
    }

    // Reporte 3: Libros actualmente en préstamo
    public List<Object[]> librosEnPrestamo() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.nombre AS estudiante, l.titulo AS libro, p.fecha_prestamo, p.fecha_devolucion " +
                "FROM prestamos p " +
                "JOIN estudiantes e ON p.id_estudiante = e.id " +
                "JOIN libros l ON p.id_libro = l.id " +
                "WHERE p.devuelto = FALSE " +
                "ORDER BY p.fecha_prestamo DESC";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString("estudiante"),
                        rs.getString("libro"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion")
                });
            }
        } catch (SQLException e) {
            System.out.println("❌ Error reporte 3: " + e.getMessage());
        }
        return lista;
    }
}