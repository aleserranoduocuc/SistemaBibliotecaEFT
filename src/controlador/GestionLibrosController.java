package controlador;

import dao.LibroDAO;
import modelo.Libro;
import vista.GestionLibrosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionLibrosController implements ActionListener {

    private GestionLibrosView vista;
    private LibroDAO libroDAO;

    public GestionLibrosController(GestionLibrosView vista) {
        this.vista = vista;
        this.libroDAO = new LibroDAO();

        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0); // limpiar

        List<Libro> libros = libroDAO.listar();
        for (Libro l : libros) {
            modelo.addRow(new Object[]{
                    l.getId(), l.getTitulo(), l.getAutor(),
                    l.getIsbn(), l.getEditorial(), l.getStock(), l.getIdCategoria()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            Libro libro = new Libro();
            libro.setTitulo(vista.txtTitulo.getText());
            libro.setAutor(vista.txtAutor.getText());
            libro.setIsbn(vista.txtIsbn.getText());
            libro.setEditorial(vista.txtEditorial.getText());
            libro.setStock(Integer.parseInt(vista.txtStock.getText()));
            libro.setIdCategoria(Integer.parseInt(vista.txtIdCategoria.getText()));

            if (libroDAO.insertar(libro)) {
                JOptionPane.showMessageDialog(vista, "✅ Libro agregado");
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ Error al agregar");
            }

        } else if (e.getSource() == vista.btnModificar) {
            int fila = vista.tablaLibros.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un libro");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);

            Libro libro = new Libro();
            libro.setId(id);
            libro.setTitulo(vista.txtTitulo.getText());
            libro.setAutor(vista.txtAutor.getText());
            libro.setIsbn(vista.txtIsbn.getText());
            libro.setEditorial(vista.txtEditorial.getText());
            libro.setStock(Integer.parseInt(vista.txtStock.getText()));
            libro.setIdCategoria(Integer.parseInt(vista.txtIdCategoria.getText()));

            if (libroDAO.actualizar(libro)) {
                JOptionPane.showMessageDialog(vista, "✅ Libro actualizado");
                limpiar();
                cargarTabla();
            }

        } else if (e.getSource() == vista.btnEliminar) {
            int fila = vista.tablaLibros.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un libro");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (libroDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(vista, "✅ Libro eliminado");
                cargarTabla();
            }

        } else if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    private void limpiar() {
        vista.txtTitulo.setText("");
        vista.txtAutor.setText("");
        vista.txtIsbn.setText("");
        vista.txtEditorial.setText("");
        vista.txtStock.setText("");
        vista.txtIdCategoria.setText("");
    }
}