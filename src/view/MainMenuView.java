package src.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView {
    private JFrame frame;
    private JButton addBookButton;
    private JButton searchBookButton;

    public MainMenuView() {
        frame = new JFrame("Main Menu");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        addBookButton = new JButton("Add Book");
        searchBookButton = new JButton("Search Book");

        // Set bounds
        addBookButton.setBounds(150, 100, 100, 50);
        searchBookButton.setBounds(150, 200, 100, 50);

        // Add components to frame
        frame.add(addBookButton);
        frame.add(searchBookButton);

        // Add action listeners
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddBookView();
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SearchBookView();
            }
        });

        frame.setVisible(true);
    }
}
