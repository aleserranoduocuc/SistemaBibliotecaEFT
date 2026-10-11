package dao;

import modelo.Usuario;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para validar login
    public Usuario validarLogin(String rut, String contraseña) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE rut = ? AND contraseña = ?";

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rut);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setRut(rs.getString("rut"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en validarLogin: " + e.getMessage());
        }
        return usuario;
    }

    // Obtener el id real del estudiante desde la tabla estudiantes
    public int obtenerIdEstudiantePorRut(String rut) {
        int id = -1;
        String sql = "SELECT id FROM estudiantes WHERE rut = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rut);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener id estudiante: " + e.getMessage());
        }
        return id;
    }
}