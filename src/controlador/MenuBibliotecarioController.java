package controlador;

import vista.GestionLibrosView;
import vista.LoginView;
import vista.MenuBibliotecarioView;
import vista.GestionEstudiantesView;
import vista.GestionPrestamosView;
import vista.ReportesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBibliotecarioController implements ActionListener {

    private MenuBibliotecarioView vista;

    public MenuBibliotecarioController(MenuBibliotecarioView vista) {
        this.vista = vista;
        this.vista.btnGestionLibros.addActionListener(this);
        this.vista.btnGestionEstudiantes.addActionListener(this);
        this.vista.btnPrestamos.addActionListener(this);
        this.vista.btnReportes.addActionListener(this);
        this.vista.btnCerrarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGestionLibros) {
            // Abrir ventana de gestión de libros
            GestionLibrosView gl = new GestionLibrosView();
            new GestionLibrosController(gl);
            gl.setVisible(true);

        } else if (e.getSource() == vista.btnGestionEstudiantes) {
            GestionEstudiantesView ge = new GestionEstudiantesView();
            new GestionEstudiantesController(ge);
            ge.setVisible(true);

        } else if (e.getSource() == vista.btnPrestamos) {
            GestionPrestamosView gp = new GestionPrestamosView();
            new GestionPrestamosController(gp);
            gp.setVisible(true);

        } else if (e.getSource() == vista.btnReportes) {
            ReportesView rv = new ReportesView();
            new ReportesController(rv);
            rv.setVisible(true);

        } else if (e.getSource() == vista.btnCerrarSesion) {
            vista.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        }
    }
}