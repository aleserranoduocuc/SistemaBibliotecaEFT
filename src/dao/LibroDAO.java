package dao;

import modelo.Libro;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // LISTAR todos los libros
    public List<Libro> listar() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setStock(rs.getInt("stock"));
                libro.setIdCategoria(rs.getInt("id_categoria"));
                lista.add(libro);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en listar libros: " + e.getMessage());
        }
        return lista;
    }

    // INSERTAR un libro
    public boolean insertar(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, isbn, editorial, stock, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdCategoria());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar libro: " + e.getMessage());
            return false;
        }
    }

    // ACTUALIZAR un libro
    public boolean actualizar(Libro libro) {
        String sql = "UPDATE libros SET titulo=?, autor=?, isbn=?, editorial=?, stock=?, id_categoria=? WHERE id=?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdCategoria());
            ps.setInt(7, libro.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar libro: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR un libro
    public boolean eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id=?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }
}