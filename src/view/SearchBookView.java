package src.view;

import src.controller.BookController;
import src.model.Book;
import src.utils.ViewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchBookView {
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SearchBookView() {
        frame = new JFrame("Search Book");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        searchField = new JTextField();
        searchButton = new JButton("Search");
        resultArea = new JTextArea();
        JButton backButton = ViewUtils.createBackButton(frame);
        frame.add(backButton);


        // Set bounds
        searchField.setBounds(50, 50, 200, 20);
        searchButton.setBounds(260, 50, 100, 20);
        resultArea.setBounds(50, 100, 300, 200);

        // Add components to frame
        frame.add(searchField);
        frame.add(searchButton);
        frame.add(resultArea);

        // Add action listener to button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                BookController bookController = new BookController();
                List<Book> books = bookController.searchBooks(keyword);

                resultArea.setText("");
                for (Book book : books) {
                    resultArea.append(book.getTitle() + " by " + book.getAuthor() + "\n");
                }
            }
        });

        frame.setVisible(true);
    }
}
