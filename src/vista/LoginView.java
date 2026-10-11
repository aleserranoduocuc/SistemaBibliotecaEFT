package vista;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public JTextField txtRut;
    public JPasswordField txtContraseña;
    public JButton btnIngresar;
    public JLabel lblMensaje;

    public LoginView() {
        setTitle("Sistema Biblioteca - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(120, 20, 200, 30);
        add(lblTitulo);

        JLabel lblRut = new JLabel("RUT:");
        lblRut.setBounds(50, 80, 100, 25);
        add(lblRut);

        txtRut = new JTextField();
        txtRut.setBounds(150, 80, 180, 25);
        add(txtRut);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(50, 120, 100, 25);
        add(lblContraseña);

        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(150, 120, 180, 25);
        add(txtContraseña);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(140, 170, 120, 30);
        add(btnIngresar);

        lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 210, 300, 25);
        lblMensaje.setForeground(Color.RED);
        add(lblMensaje);
    }
}