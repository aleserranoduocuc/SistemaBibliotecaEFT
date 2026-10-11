package controlador;

import dao.PrestamoDAO;
import util.DatabaseConnection;
import vista.DevolverLibroView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DevolverLibroController implements ActionListener {

    private DevolverLibroView vista;
    private int idEstudiante;
    private PrestamoDAO prestamoDAO;

    public DevolverLibroController(DevolverLibroView vista, int idEstudiante) {
        this.vista = vista;
        this.idEstudiante = idEstudiante;
        this.prestamoDAO = new PrestamoDAO();
        this.vista.btnDevolver.addActionListener(this);
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);

        String sql = "SELECT p.id, l.titulo, p.fecha_prestamo, p.fecha_devolucion, p.devuelto " +
                "FROM prestamos p " +
                "JOIN libros l ON p.id_libro = l.id " +
                "WHERE p.id_estudiante = ? AND p.devuelto = FALSE " +
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
                        "Prestado"
                });
            }
        } catch (Exception e) {
            System.out.println("❌ Error al cargar préstamos: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int fila = vista.tablaPrestamos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un préstamo");
            return;
        }
        int idPrestamo = (int) vista.modeloTabla.getValueAt(fila, 0);
        if (prestamoDAO.marcarDevuelto(idPrestamo)) {
            JOptionPane.showMessageDialog(vista, "✅ Libro devuelto correctamente");
            cargarTabla();
        }
    }
}