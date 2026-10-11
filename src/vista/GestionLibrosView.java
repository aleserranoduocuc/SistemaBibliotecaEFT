package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionLibrosView extends JFrame {

    public JTextField txtTitulo, txtAutor, txtIsbn, txtEditorial, txtStock, txtIdCategoria;
    public JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;
    public JTable tablaLibros;
    public DefaultTableModel modeloTabla;

    public GestionLibrosView() {
        setTitle("Gestión de Libros");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ----- Formulario -----
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 80, 25);
        add(lblTitulo);
        txtTitulo = new JTextField();
        txtTitulo.setBounds(100, 20, 200, 25);
        add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(20, 55, 80, 25);
        add(lblAutor);
        txtAutor = new JTextField();
        txtAutor.setBounds(100, 55, 200, 25);
        add(txtAutor);

        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setBounds(20, 90, 80, 25);
        add(lblIsbn);
        txtIsbn = new JTextField();
        txtIsbn.setBounds(100, 90, 200, 25);
        add(txtIsbn);

        JLabel lblEditorial = new JLabel("Editorial:");
        lblEditorial.setBounds(20, 125, 80, 25);
        add(lblEditorial);
        txtEditorial = new JTextField();
        txtEditorial.setBounds(100, 125, 200, 25);
        add(txtEditorial);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(20, 160, 80, 25);
        add(lblStock);
        txtStock = new JTextField();
        txtStock.setBounds(100, 160, 200, 25);
        add(txtStock);

        JLabel lblIdCat = new JLabel("ID Categoría:");
        lblIdCat.setBounds(20, 195, 100, 25);
        add(lblIdCat);
        txtIdCategoria = new JTextField();
        txtIdCategoria.setBounds(120, 195, 180, 25);
        add(txtIdCategoria);

        // ----- Botones -----
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

        // ----- Tabla -----
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Título", "Autor", "ISBN", "Editorial", "Stock", "ID Cat"}, 0);
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaLibros);
        scroll.setBounds(20, 250, 850, 300);
        add(scroll);
    }
}