package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VerLibrosView extends JFrame {

    public JTable tablaLibros;
    public DefaultTableModel modeloTabla;

    public VerLibrosView() {
        setTitle("Libros Disponibles");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("📚 Catálogo de Libros");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 400, 30);
        add(lblTitulo);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Título", "Autor", "ISBN", "Editorial", "Stock"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Solo lectura
            }
        };
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaLibros);
        scroll.setBounds(20, 60, 850, 380);
        add(scroll);
    }
}