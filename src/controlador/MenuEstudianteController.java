package controlador;

import vista.LoginView;
import vista.MenuEstudianteView;
import vista.VerLibrosView;
import vista.MisPrestamosView;
import vista.PedirPrestamoView;
import vista.DevolverLibroView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEstudianteController implements ActionListener {

    private MenuEstudianteView vista;

    public MenuEstudianteController(MenuEstudianteView vista) {
        this.vista = vista;
        this.vista.btnVerLibros.addActionListener(this);
        this.vista.btnPedirPrestamo.addActionListener(this);
        this.vista.btnDevolver.addActionListener(this);
        this.vista.btnMisPrestamos.addActionListener(this);
        this.vista.btnCerrarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnVerLibros) {
            VerLibrosView vl = new VerLibrosView();
            new VerLibrosController(vl);
            vl.setVisible(true);

        } else if (e.getSource() == vista.btnPedirPrestamo) {
            PedirPrestamoView pp = new PedirPrestamoView(vista.getIdEstudiante());
            new PedirPrestamoController(pp, vista.getIdEstudiante());
            pp.setVisible(true);

        } else if (e.getSource() == vista.btnDevolver) {
            DevolverLibroView dv = new DevolverLibroView(vista.getIdEstudiante());
            new DevolverLibroController(dv, vista.getIdEstudiante());
            dv.setVisible(true);

        } else if (e.getSource() == vista.btnMisPrestamos) {
            MisPrestamosView mp = new MisPrestamosView(vista.getIdEstudiante());
            new MisPrestamosController(mp, vista.getIdEstudiante());
            mp.setVisible(true);

        } else if (e.getSource() == vista.btnCerrarSesion) {
            vista.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        }
    }
}