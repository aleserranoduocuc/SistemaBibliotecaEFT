package vista;

import javax.swing.*;
import java.awt.*;

public class MenuBibliotecarioView extends JFrame {

    public JButton btnGestionLibros;
    public JButton btnGestionEstudiantes;
    public JButton btnPrestamos;
    public JButton btnReportes;
    public JButton btnCerrarSesion;

    public MenuBibliotecarioView() {
        setTitle("Menú Bibliotecario");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("MENÚ BIBLIOTECARIO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(130, 20, 300, 30);
        add(lblTitulo);

        btnGestionLibros = new JButton("📚 Gestión de Libros");
        btnGestionLibros.setBounds(100, 80, 300, 40);
        add(btnGestionLibros);

        btnGestionEstudiantes = new JButton("👨‍🎓 Gestión de Estudiantes");
        btnGestionEstudiantes.setBounds(100, 140, 300, 40);
        add(btnGestionEstudiantes);

        btnPrestamos = new JButton("📖 Préstamos y Devoluciones");
        btnPrestamos.setBounds(100, 200, 300, 40);
        add(btnPrestamos);

        btnReportes = new JButton("📊 Reportes");
        btnReportes.setBounds(100, 260, 300, 40);
        add(btnReportes);

        btnCerrarSesion = new JButton("🚪 Cerrar Sesión");
        btnCerrarSesion.setBounds(100, 320, 300, 40);
        add(btnCerrarSesion);
    }
}