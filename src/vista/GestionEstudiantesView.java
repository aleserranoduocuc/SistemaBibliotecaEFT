package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionEstudiantesView extends JFrame {

    public JTextField txtNombre, txtRut, txtCurso, txtCorreo;
    public JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;
    public JTable tablaEstudiantes;
    public DefaultTableModel modeloTabla;

    public GestionEstudiantesView() {
        setTitle("Gestión de Estudiantes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 25);
        add(txtNombre);

        JLabel lblRut = new JLabel("RUT:");
        lblRut.setBounds(20, 55, 80, 25);
        add(lblRut);
        txtRut = new JTextField();
        txtRut.setBounds(100, 55, 200, 25);
        add(txtRut);

        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setBounds(20, 90, 80, 25);
        add(lblCurso);
        txtCurso = new JTextField();
        txtCurso.setBounds(100, 90, 200, 25);
        add(txtCurso);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 125, 80, 25);
        add(lblCorreo);
        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 125, 200, 25);
        add(txtCorreo);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(350, 20, 120, 30);
        add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(350, 60, 120, 30);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(350, 100, 120, 30);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(350, 140, 120, 30);
        add(btnLimpiar);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "RUT", "Curso", "Correo"}, 0);
        tablaEstudiantes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEstudiantes);
        scroll.setBounds(20, 200, 850, 350);
        add(scroll);
    }
}