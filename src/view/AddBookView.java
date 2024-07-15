package src.view;

import src.controller.BookController;
import src.model.Book;
import src.utils.DatabaseConnection;
import src.utils.ViewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookView {
    private JFrame frame;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField categoryField;
    private JTextField publicationField;
    private JButton addButton;

    public AddBookView() {
        DatabaseConnection.createTables(); // Ensure the tables are created

        frame = new JFrame("Add Book");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        titleField = new JTextField();
        authorField = new JTextField();
        categoryField = new JTextField();
        publicationField = new JTextField();
        addButton = new JButton("Add");
        JButton backButton = ViewUtils.createBackButton(frame);

        // Set bounds
        titleField.setBounds(50, 50, 200, 20);
        authorField.setBounds(50, 80, 200, 20);
        categoryField.setBounds(50, 110, 200, 20);
        publicationField.setBounds(50, 140, 200, 20);
        addButton.setBounds(100, 180, 100, 20);

        // Add components to frame
        frame.add(titleField);
        frame.add(authorField);
        frame.add(categoryField);
        frame.add(publicationField);
        frame.add(addButton);
        frame.add(backButton);

        // Add action listener to button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                book.setTitle(titleField.getText());
                book.setAuthor(authorField.getText());
                book.setCategory(categoryField.getText());
                book.setPublication(publicationField.getText());
                book.setIssued(false);

                BookController bookController = new BookController();
                bookController.addBook(book);
                JOptionPane.showMessageDialog(frame, "Book added successfully!");
            }
        });

        frame.setVisible(true);
    }
}
