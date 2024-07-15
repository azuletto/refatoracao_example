package src.controller;

import src.model.Book;
import src.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookController {

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, category, publication, issued) VALUES(?, ?, ?, ?, ?)";

      try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setString(4, book.getPublication());
            pstmt.setBoolean(5, book.isIssued());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding book", e);
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublication(rs.getString("publication"));
                book.setIssued(rs.getBoolean("issued"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching books", e);
        }

        return books;
    }
}
