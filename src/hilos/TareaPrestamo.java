package hilos;

import dao.PrestamoDAO;
import modelo.Prestamo;
import vista.GestionPrestamosView;
import vista.PedirPrestamoView;

import javax.swing.*;
import java.sql.Date;

public class TareaPrestamo implements Runnable {

    private static final Object LOCK_STOCK = new Object();

    private PrestamoDAO prestamoDAO;
    private GestionPrestamosView vistaBibliotecario;
    private PedirPrestamoView vistaEstudiante; // modo estudiante
    private int idEstudiante;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public TareaPrestamo(GestionPrestamosView vista, int idEstudiante, int idLibro,
                         Date fechaPrestamo, Date fechaDevolucion) {
        this.vistaBibliotecario = vista;
        this.prestamoDAO = new PrestamoDAO();
        this.idEstudiante = idEstudiante;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setModoSilencioso(PedirPrestamoView vista) {
        this.vistaEstudiante = vista;
    }

    @Override
    public void run() {
        synchronized (LOCK_STOCK) {
            int stockActual = prestamoDAO.obtenerStock(idLibro);

            if (stockActual <= 0) {
                SwingUtilities.invokeLater(() -> {
                    if (vistaEstudiante != null) {
                        vistaEstudiante.lblEstado.setText("❌ No hay stock disponible.");
                    } else {
                        JOptionPane.showMessageDialog(vistaBibliotecario,
                                "❌ No hay stock disponible.");
                    }
                });
                return;
            }

            try { Thread.sleep(1500); } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            Prestamo p = new Prestamo();
            p.setIdEstudiante(idEstudiante);
            p.setIdLibro(idLibro);
            p.setFechaPrestamo(fechaPrestamo);
            p.setFechaDevolucion(fechaDevolucion);
            p.setDevuelto(false);

            boolean ok = prestamoDAO.insertar(p);
            if (ok) prestamoDAO.actualizarStock(idLibro, stockActual - 1);

            final int stockFinal = stockActual - 1;
            SwingUtilities.invokeLater(() -> {
                if (vistaEstudiante != null) {
                    if (ok) {
                        vistaEstudiante.lblEstado.setText(
                                "✅ Préstamo registrado. Stock restante: " + stockFinal);
                    } else {
                        vistaEstudiante.lblEstado.setText("❌ Error al registrar.");
                    }
                } else {
                    if (ok) {
                        JOptionPane.showMessageDialog(vistaBibliotecario,
                                "✅ Préstamo registrado.\nStock restante: " + stockFinal);
                    } else {
                        JOptionPane.showMessageDialog(vistaBibliotecario,
                                "❌ Error al registrar préstamo.");
                    }
                }
            });
        }
    }
}