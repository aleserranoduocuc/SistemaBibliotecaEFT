package controlador;

import dao.PrestamoDAO;
import hilos.TareaPrestamo;
import vista.GestionPrestamosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class GestionPrestamosController implements ActionListener {

    private GestionPrestamosView vista;
    private PrestamoDAO prestamoDAO;

    public GestionPrestamosController(GestionPrestamosView vista) {
        this.vista = vista;
        this.prestamoDAO = new PrestamoDAO();
        this.vista.btnPrestar.addActionListener(this);
        this.vista.btnDevolver.addActionListener(this);
        this.vista.btnRefrescar.addActionListener(this);
        cargarTabla();
    }

    public void cargarTabla() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);
        List<Object[]> lista = prestamoDAO.listar();
        for (Object[] fila : lista) {
            modelo.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnPrestar) {
            try {
                int idEst = Integer.parseInt(vista.txtIdEstudiante.getText().trim());
                int idLib = Integer.parseInt(vista.txtIdLibro.getText().trim());
                Date fPrestamo = Date.valueOf(vista.txtFechaPrestamo.getText().trim());
                Date fDevolucion = Date.valueOf(vista.txtFechaDevolucion.getText().trim());

                // Crear y lanzar el HILO
                TareaPrestamo tarea = new TareaPrestamo(vista, idEst, idLib, fPrestamo, fDevolucion);
                new Thread(tarea).start();

                JOptionPane.showMessageDialog(vista,
                        "⏳ Procesando préstamo en segundo plano...");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(vista,
                        "❌ Verifique los datos.\nFechas: YYYY-MM-DD\nIDs: números");
            }

        } else if (e.getSource() == vista.btnDevolver) {
            int fila = vista.tablaPrestamos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un préstamo");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (prestamoDAO.marcarDevuelto(id)) {
                JOptionPane.showMessageDialog(vista, "✅ Devolución registrada");
                cargarTabla();
            }

        } else if (e.getSource() == vista.btnRefrescar) {
            cargarTabla();
        }
    }
}