package controlador;

import dao.ReporteDAO;
import vista.ReportesView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportesController implements ActionListener {

    private ReportesView vista;
    private ReporteDAO reporteDAO;

    public ReportesController(ReportesView vista) {
        this.vista = vista;
        this.reporteDAO = new ReporteDAO();
        this.vista.btnTop5.addActionListener(this);
        this.vista.btnHistorial.addActionListener(this);
        this.vista.btnEnPrestamo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnTop5) {
            mostrarTop5();
        } else if (e.getSource() == vista.btnHistorial) {
            mostrarHistorial();
        } else if (e.getSource() == vista.btnEnPrestamo) {
            mostrarEnPrestamo();
        }
    }

    private void mostrarTop5() {
        vista.lblTitulo.setText("📊 Top 5 Libros Más Prestados");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Título", "Autor", "Veces Prestado"}, 0);
        List<Object[]> datos = reporteDAO.librosMasPrestados();
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
        vista.tablaReportes.setModel(modelo);
    }

    private void mostrarHistorial() {
        try {
            int idEst = Integer.parseInt(vista.txtIdEstudiante.getText().trim());
            vista.lblTitulo.setText("📖 Historial del Estudiante ID: " + idEst);
            DefaultTableModel modelo = new DefaultTableModel(
                    new Object[]{"Libro", "F. Préstamo", "F. Devolución", "Estado"}, 0);
            List<Object[]> datos = reporteDAO.historialPorEstudiante(idEst);
            for (Object[] fila : datos) {
                modelo.addRow(fila);
            }
            vista.tablaReportes.setModel(modelo);
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Ingrese un ID numérico");
        }
    }

    private void mostrarEnPrestamo() {
        vista.lblTitulo.setText("📚 Libros Actualmente en Préstamo");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Estudiante", "Libro", "F. Préstamo", "F. Devolución"}, 0);
        List<Object[]> datos = reporteDAO.librosEnPrestamo();
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
        vista.tablaReportes.setModel(modelo);
    }
}