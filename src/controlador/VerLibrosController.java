package controlador;

import dao.LibroDAO;
import modelo.Libro;
import vista.VerLibrosView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VerLibrosController {

    private VerLibrosView vista;
    private LibroDAO libroDAO;

    public VerLibrosController(VerLibrosView vista) {
        this.vista = vista;
        this.libroDAO = new LibroDAO();
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);
        List<Libro> libros = libroDAO.listar();
        for (Libro l : libros) {
            modelo.addRow(new Object[]{
                    l.getId(), l.getTitulo(), l.getAutor(),
                    l.getIsbn(), l.getEditorial(), l.getStock()
            });
        }
    }
}