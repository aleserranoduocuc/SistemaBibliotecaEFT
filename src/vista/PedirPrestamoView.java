package vista;

import javax.swing.*;
import java.awt.*;

public class PedirPrestamoView extends JFrame {

    public JTextField txtIdLibro;
    public JTextField txtFechaPrestamo;
    public JTextField txtFechaDevolucion;
    public JButton btnPedir;
    public JLabel lblEstado;

    public PedirPrestamoView(int idEstudiante) {
        setTitle("Pedir Préstamo - Estudiante ID: " + idEstudiante);
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("📖 Pedir un Libro Prestado");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(100, 15, 300, 30);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID del Libro:");
        lblId.setBounds(30, 70, 120, 25);
        add(lblId);
        txtIdLibro = new JTextField();
        txtIdLibro.setBounds(160, 70, 200, 25);
        add(txtIdLibro);

        JLabel lblFp = new JLabel("Fecha Préstamo:");
        lblFp.setBounds(30, 110, 120, 25);
        add(lblFp);
        txtFechaPrestamo = new JTextField();
        txtFechaPrestamo.setBounds(160, 110, 200, 25);
        txtFechaPrestamo.setToolTipText("Formato: YYYY-MM-DD");
        add(txtFechaPrestamo);

        JLabel lblFd = new JLabel("Fecha Devolución:");
        lblFd.setBounds(30, 150, 130, 25);
        add(lblFd);
        txtFechaDevolucion = new JTextField();
        txtFechaDevolucion.setBounds(160, 150, 200, 25);
        txtFechaDevolucion.setToolTipText("Formato: YYYY-MM-DD");
        add(txtFechaDevolucion);

        btnPedir = new JButton("📖 Pedir Préstamo");
        btnPedir.setBounds(130, 200, 180, 35);
        add(btnPedir);

        lblEstado = new JLabel("");
        lblEstado.setBounds(30, 245, 380, 25);
        lblEstado.setForeground(new Color(0, 102, 0));
        add(lblEstado);
    }
}