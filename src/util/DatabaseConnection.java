package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Instancia única (Patrón Singleton)
    private static DatabaseConnection instance;
    private Connection connection;

    // Datos de conexión (AJUSTA si es necesario)
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Zelda2775434"; // <-- CAMBIA ESTO

    // Constructor privado
    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos 'biblioteca'");
        } catch (ClassNotFoundException e) {
            throw new SQLException("❌ Driver MySQL no encontrado", e);
        }
    }

    // Método getInstance()
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}