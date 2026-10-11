package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionPrestamosView extends JFrame {

    public JTextField txtIdEstudiante, txtIdLibro, txtFechaPrestamo, txtFechaDevolucion;
    public JButton btnPrestar, btnDevolver, btnRefrescar;
    public JTable tablaPrestamos;
    public DefaultTableModel modeloTabla;

    public GestionPrestamosView() {
        setTitle("Préstamos y Devoluciones");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Formulario
        JLabel lblEst = new JLabel("ID Estudiante:");
        lblEst.setBounds(20, 20, 100, 25);
        add(lblEst);
        txtIdEstudiante = new JTextField();
        txtIdEstudiante.setBounds(130, 20, 100, 25);
        add(txtIdEstudiante);

        JLabel lblLib = new JLabel("ID Libro:");
        lblLib.setBounds(250, 20, 80, 25);
        add(lblLib);
        txtIdLibro = new JTextField();
        txtIdLibro.setBounds(330, 20, 100, 25);
        add(txtIdLibro);

        JLabel lblFp = new JLabel("Fecha Préstamo:");
        lblFp.setBounds(20, 60, 110, 25);
        add(lblFp);
        txtFechaPrestamo = new JTextField();
        txtFechaPrestamo.setBounds(130, 60, 120, 25);
        txtFechaPrestamo.setToolTipText("Formato: YYYY-MM-DD");
        add(txtFechaPrestamo);

        JLabel lblFd = new JLabel("Fecha Devolución:");
        lblFd.setBounds(260, 60, 120, 25);
        add(lblFd);
        txtFechaDevolucion = new JTextField();
        txtFechaDevolucion.setBounds(380, 60, 120, 25);
        txtFechaDevolucion.setToolTipText("Formato: YYYY-MM-DD");
        add(txtFechaDevolucion);

        btnPrestar = new JButton("📖 Realizar Préstamo");
        btnPrestar.setBounds(550, 20, 200, 30);
        add(btnPrestar);

        btnDevolver = new JButton("↩️ Registrar Devolución");
        btnDevolver.setBounds(550, 60, 200, 30);
        add(btnDevolver);

        btnRefrescar = new JButton("🔄 Refrescar");
        btnRefrescar.setBounds(770, 20, 150, 30);
        add(btnRefrescar);

        // Tabla
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Estudiante", "Libro", "F. Préstamo", "F. Devolución", "Estado"}, 0);
        tablaPrestamos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        scroll.setBounds(20, 120, 940, 430);
        add(scroll);
    }

    // Método público para que el hilo pueda refrescar la tabla
    public void cargarTabla() {
        // Este método será llamado por el controlador
        // pero desde el hilo usamos invokeLater
    }
}