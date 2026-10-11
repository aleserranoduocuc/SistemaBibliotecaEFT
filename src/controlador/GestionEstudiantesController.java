package controlador;

import dao.EstudianteDAO;
import modelo.Estudiante;
import vista.GestionEstudiantesView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionEstudiantesController implements ActionListener {

    private GestionEstudiantesView vista;
    private EstudianteDAO estudianteDAO;

    public GestionEstudiantesController(GestionEstudiantesView vista) {
        this.vista = vista;
        this.estudianteDAO = new EstudianteDAO();
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);
        List<Estudiante> lista = estudianteDAO.listar();
        for (Estudiante e : lista) {
            modelo.addRow(new Object[]{
                    e.getId(), e.getNombre(), e.getRut(), e.getCurso(), e.getCorreo()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            Estudiante est = new Estudiante();
            est.setNombre(vista.txtNombre.getText());
            est.setRut(vista.txtRut.getText());
            est.setCurso(vista.txtCurso.getText());
            est.setCorreo(vista.txtCorreo.getText());
            if (estudianteDAO.insertar(est)) {
                JOptionPane.showMessageDialog(vista, "✅ Estudiante agregado");
                limpiar();
                cargarTabla();
            }

        } else if (e.getSource() == vista.btnModificar) {
            int fila = vista.tablaEstudiantes.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un estudiante");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            Estudiante est = new Estudiante();
            est.setId(id);
            est.setNombre(vista.txtNombre.getText());
            est.setRut(vista.txtRut.getText());
            est.setCurso(vista.txtCurso.getText());
            est.setCorreo(vista.txtCorreo.getText());
            if (estudianteDAO.actualizar(est)) {
                JOptionPane.showMessageDialog(vista, "✅ Estudiante actualizado");
                limpiar();
                cargarTabla();
            }

        } else if (e.getSource() == vista.btnEliminar) {
            int fila = vista.tablaEstudiantes.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un estudiante");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (estudianteDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(vista, "✅ Estudiante eliminado");
                cargarTabla();
            }

        } else if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    private void limpiar() {
        vista.txtNombre.setText("");
        vista.txtRut.setText("");
        vista.txtCurso.setText("");
        vista.txtCorreo.setText("");
    }
}