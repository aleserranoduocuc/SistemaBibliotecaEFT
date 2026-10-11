package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DevolverLibroView extends JFrame {

    public JTable tablaPrestamos;
    public DefaultTableModel modeloTabla;
    public JButton btnDevolver;

    public DevolverLibroView(int idEstudiante) {
        setTitle("Devolver Libro - Estudiante ID: " + idEstudiante);
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("↩️ Selecciona un préstamo para devolver");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(20, 15, 500, 30);
        add(lblTitulo);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Libro", "F. Préstamo", "F. Devolución", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPrestamos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        scroll.setBounds(20, 60, 850, 320);
        add(scroll);

        btnDevolver = new JButton("↩️ Devolver Libro Seleccionado");
        btnDevolver.setBounds(300, 400, 300, 35);
        add(btnDevolver);
    }
}