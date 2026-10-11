package controlador;

import hilos.TareaPrestamo;
import vista.PedirPrestamoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class PedirPrestamoController implements ActionListener {

    private PedirPrestamoView vista;
    private int idEstudiante;

    public PedirPrestamoController(PedirPrestamoView vista, int idEstudiante) {
        this.vista = vista;
        this.idEstudiante = idEstudiante;
        this.vista.btnPedir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idLibro = Integer.parseInt(vista.txtIdLibro.getText().trim());
            Date fPrestamo = Date.valueOf(vista.txtFechaPrestamo.getText().trim());
            Date fDevolucion = Date.valueOf(vista.txtFechaDevolucion.getText().trim());

            // Crear la tarea con el hilo (SINCRONIZADO)
            TareaPrestamo tarea = new TareaPrestamo(
                    null, idEstudiante, idLibro, fPrestamo, fDevolucion);

            // Modo "silencioso" para que no muestre la ventana del bibliotecario
            tarea.setModoSilencioso(vista);

            new Thread(tarea).start();

            vista.lblEstado.setText("⏳ Procesando préstamo...");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista,
                    "❌ Verifique los datos.\nFechas: YYYY-MM-DD\nID: número");
        }
    }
}