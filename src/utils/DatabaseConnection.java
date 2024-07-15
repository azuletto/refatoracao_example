package src.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:lib.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading SQLite driver", e);
        }
        return DriverManager.getConnection(URL);
    }

    public static void createTables() {
        String createAuthorsTable = "CREATE TABLE IF NOT EXISTS authors (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL)";
        
        String createBooksTable = "CREATE TABLE IF NOT EXISTS books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "author_id INTEGER, " +
                "FOREIGN KEY (author_id) REFERENCES authors(id))";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createAuthorsTable);
            stmt.execute(createBooksTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
