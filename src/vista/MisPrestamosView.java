package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MisPrestamosView extends JFrame {

    public JTable tablaPrestamos;
    public DefaultTableModel modeloTabla;

    public MisPrestamosView(int idEstudiante) {
        setTitle("Mis Préstamos - Estudiante ID: " + idEstudiante);
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("📖 Historial de Mis Préstamos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 400, 30);
        add(lblTitulo);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Libro", "F. Préstamo", "F. Devolución", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPrestamos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        scroll.setBounds(20, 60, 850, 380);
        add(scroll);
    }
}