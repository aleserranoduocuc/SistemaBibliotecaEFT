package hilos;

import dao.PrestamoDAO;
import modelo.Prestamo;
import vista.GestionPrestamosView;

import javax.swing.*;
import java.sql.Date;

/**
 * Hilo que ejecuta el préstamo en segundo plano.
 * Usa "synchronized" para evitar condiciones de carrera cuando varios
 * usuarios piden el mismo libro al mismo tiempo.
 */
public class TareaPrestamo implements Runnable {

    // Candado estático: garantiza que solo un hilo a la vez
    // pueda modificar el stock
    private static final Object LOCK_STOCK = new Object();

    private PrestamoDAO prestamoDAO;
    private GestionPrestamosView vista;
    private int idEstudiante;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public TareaPrestamo(GestionPrestamosView vista, int idEstudiante, int idLibro,
                         Date fechaPrestamo, Date fechaDevolucion) {
        this.vista = vista;
        this.prestamoDAO = new PrestamoDAO();
        this.idEstudiante = idEstudiante;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public void run() {
        // Bloque sincronizado: solo un hilo puede ejecutar esto a la vez
        synchronized (LOCK_STOCK) {

            // 1. Verificar stock
            int stockActual = prestamoDAO.obtenerStock(idLibro);

            if (stockActual <= 0) {
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(vista,
                                "❌ No hay stock disponible para este libro."));
                return;
            }

            // 2. Simulamos un pequeño retraso (proceso lento del servidor)
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // 3. Registrar el préstamo
            Prestamo p = new Prestamo();
            p.setIdEstudiante(idEstudiante);
            p.setIdLibro(idLibro);
            p.setFechaPrestamo(fechaPrestamo);
            p.setFechaDevolucion(fechaDevolucion);
            p.setDevuelto(false);

            boolean ok = prestamoDAO.insertar(p);

            // 4. Actualizar stock
            if (ok) {
                prestamoDAO.actualizarStock(idLibro, stockActual - 1);
            }

            // 5. Avisar a la interfaz (en el hilo de Swing)
            final int stockFinal = stockActual - 1;
            SwingUtilities.invokeLater(() -> {
                if (ok) {
                    JOptionPane.showMessageDialog(vista,
                            "✅ Préstamo registrado.\nStock restante: " + stockFinal);
// El usuario puede presionar "Refrescar" para ver el cambio
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "❌ Error al registrar préstamo.");
                }
            });
        }
    }
}