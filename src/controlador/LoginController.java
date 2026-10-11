package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import vista.LoginView;
import vista.MenuBibliotecarioView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginView vista;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        this.vista.btnIngresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String rut = vista.txtRut.getText().trim();
        String contraseña = new String(vista.txtContraseña.getPassword()).trim();

        if (rut.isEmpty() || contraseña.isEmpty()) {
            vista.lblMensaje.setText("⚠️ Complete todos los campos");
            return;
        }

        Usuario usuario = usuarioDAO.validarLogin(rut, contraseña);

        if (usuario != null) {
            JOptionPane.showMessageDialog(vista,
                    "¡Bienvenido/a " + usuario.getNombre() + "!");

            vista.dispose(); // Cerrar el Login

            if (usuario.getRol().equals("bibliotecario")) {
                // Abrir el Menú del Bibliotecario
                MenuBibliotecarioView menu = new MenuBibliotecarioView();
                new MenuBibliotecarioController(menu);
                menu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Módulo Estudiante (por implementar)");
            }

        } else {
            vista.lblMensaje.setText("❌ RUT o contraseña incorrectos");
        }
    }
}