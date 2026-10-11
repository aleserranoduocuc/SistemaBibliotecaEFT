package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportesView extends JFrame {

    public JButton btnTop5, btnHistorial, btnEnPrestamo;
    public JTextField txtIdEstudiante;
    public JTable tablaReportes;
    public DefaultTableModel modeloTabla;
    public JLabel lblTitulo;

    public ReportesView() {
        setTitle("Reportes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Botón Reporte 1
        btnTop5 = new JButton("📊 Libros más prestados (Top 5)");
        btnTop5.setBounds(20, 20, 280, 35);
        add(btnTop5);

        // Botón Reporte 2
        btnHistorial = new JButton("📖 Historial por Estudiante");
        btnHistorial.setBounds(20, 70, 220, 35);
        add(btnHistorial);

        JLabel lblId = new JLabel("ID Estudiante:");
        lblId.setBounds(250, 70, 100, 35);
        add(lblId);

        txtIdEstudiante = new JTextField();
        txtIdEstudiante.setBounds(350, 70, 60, 35);
        add(txtIdEstudiante);

        // Botón Reporte 3
        btnEnPrestamo = new JButton("📚 Libros actualmente en préstamo");
        btnEnPrestamo.setBounds(430, 20, 280, 35);
        add(btnEnPrestamo);

        // Título del reporte actual
        lblTitulo = new JLabel("Seleccione un reporte");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setBounds(20, 120, 500, 25);
        add(lblTitulo);

        // Tabla
        modeloTabla = new DefaultTableModel();
        tablaReportes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaReportes);
        scroll.setBounds(20, 160, 850, 380);
        add(scroll);
    }
}