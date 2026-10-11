package vista;

import javax.swing.*;
import java.awt.*;

public class MenuEstudianteView extends JFrame {

    public JButton btnVerLibros;
    public JButton btnPedirPrestamo;
    public JButton btnDevolver;
    public JButton btnMisPrestamos;
    public JButton btnCerrarSesion;
    private String nombreEstudiante;
    private int idEstudiante;

    public MenuEstudianteView(String nombreEstudiante, int idEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
        this.idEstudiante = idEstudiante;

        setTitle("Menú Estudiante");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("MENÚ ESTUDIANTE");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(140, 20, 300, 30);
        add(lblTitulo);

        JLabel lblBienvenida = new JLabel("Bienvenido/a: " + nombreEstudiante);
        lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBienvenida.setBounds(140, 55, 300, 25);
        add(lblBienvenida);

        btnVerLibros = new JButton("📚 Ver Libros Disponibles");
        btnVerLibros.setBounds(100, 100, 300, 40);
        add(btnVerLibros);

        btnPedirPrestamo = new JButton("📖 Pedir Préstamo");
        btnPedirPrestamo.setBounds(100, 155, 300, 40);
        add(btnPedirPrestamo);

        btnDevolver = new JButton("↩️ Devolver Libro");
        btnDevolver.setBounds(100, 210, 300, 40);
        add(btnDevolver);

        btnMisPrestamos = new JButton("📋 Mis Préstamos");
        btnMisPrestamos.setBounds(100, 265, 300, 40);
        add(btnMisPrestamos);

        btnCerrarSesion = new JButton("🚪 Cerrar Sesión");
        btnCerrarSesion.setBounds(100, 320, 300, 40);
        add(btnCerrarSesion);
    }

    public String getNombreEstudiante() { return nombreEstudiante; }
    public int getIdEstudiante() { return idEstudiante; }
}