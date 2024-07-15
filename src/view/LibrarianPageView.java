package src.view;

import javax.swing.*;

import src.utils.ViewUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianPageView {
    private JFrame frame;
    private JButton issueBookButton;
    private JButton returnBookButton;

    public LibrarianPageView() {
        frame = new JFrame("Librarian Page");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        issueBookButton = new JButton("Issue Book");
        returnBookButton = new JButton("Return Book");
        JButton backButton = ViewUtils.createBackButton(frame);

        // Set bounds
        issueBookButton.setBounds(100, 50, 200, 20);
        returnBookButton.setBounds(100, 100, 200, 20);

        // Add components to frame
        frame.add(issueBookButton);
        frame.add(returnBookButton);
        frame.add(backButton);

        // Add action listeners to buttons
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IssueBookView();
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement return book functionality
            }
        });

        frame.setVisible(true);
    }
}
