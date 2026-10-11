package dao;

import modelo.Prestamo;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    // Listar todos los préstamos con info del libro y estudiante
    public List<Object[]> listar() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT p.id, e.nombre AS estudiante, l.titulo AS libro, " +
                "p.fecha_prestamo, p.fecha_devolucion, p.devuelto " +
                "FROM prestamos p " +
                "JOIN estudiantes e ON p.id_estudiante = e.id " +
                "JOIN libros l ON p.id_libro = l.id";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("estudiante"),
                        rs.getString("libro"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getBoolean("devuelto") ? "Devuelto" : "Prestado"
                });
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar préstamos: " + e.getMessage());
        }
        return lista;
    }

    // Insertar un préstamo
    public boolean insertar(Prestamo p) {
        String sql = "INSERT INTO prestamos (id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getIdEstudiante());
            ps.setInt(2, p.getIdLibro());
            ps.setDate(3, p.getFechaPrestamo());
            ps.setDate(4, p.getFechaDevolucion());
            ps.setBoolean(5, p.isDevuelto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar préstamo: " + e.getMessage());
            return false;
        }
    }

    // Marcar un préstamo como devuelto
    public boolean marcarDevuelto(int id) {
        String sql = "UPDATE prestamos SET devuelto = TRUE WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al marcar devuelto: " + e.getMessage());
            return false;
        }
    }

    // Obtener el stock de un libro
    public int obtenerStock(int idLibro) {
        String sql = "SELECT stock FROM libros WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener stock: " + e.getMessage());
        }
        return 0;
    }

    // Actualizar el stock de un libro
    public boolean actualizarStock(int idLibro, int nuevoStock) {
        String sql = "UPDATE libros SET stock = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, nuevoStock);
            ps.setInt(2, idLibro);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }
}