import controlador.LoginController;
import vista.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        });
    }
}