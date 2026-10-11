package controlador;

import util.DatabaseConnection;
import vista.MisPrestamosView;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MisPrestamosController {

    private MisPrestamosView vista;
    private int idEstudiante;

    public MisPrestamosController(MisPrestamosView vista, int idEstudiante) {
        this.vista = vista;
        this.idEstudiante = idEstudiante;
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);

        String sql = "SELECT p.id, l.titulo, p.fecha_prestamo, p.fecha_devolucion, p.devuelto " +
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
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getBoolean("devuelto") ? "Devuelto" : "Prestado"
                });
            }
        } catch (Exception e) {
            System.out.println("❌ Error al cargar mis préstamos: " + e.getMessage());
        }
    }
}