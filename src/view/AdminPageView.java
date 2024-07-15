package src.view;

import javax.swing.*;

import src.utils.ViewUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPageView {
    private JFrame frame;
    private JButton addBookButton;
    private JButton addCategoryButton;
    private JButton addPublicationButton;
    private JButton addStudentButton;

    public AdminPageView() {
        frame = new JFrame("Admin Page");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        addBookButton = new JButton("Add Book");
        addCategoryButton = new JButton("Add Category");
        addPublicationButton = new JButton("Add Publication");
        addStudentButton = new JButton("Add Student");

        // Set bounds
        addBookButton.setBounds(100, 50, 200, 20);
        addCategoryButton.setBounds(100, 100, 200, 20);
        addPublicationButton.setBounds(100, 150, 200, 20);
        addStudentButton.setBounds(100, 200, 200, 20);
        JButton backButton = ViewUtils.createBackButton(frame);

        // Add components to frame
        frame.add(addBookButton);
        frame.add(addCategoryButton);
        frame.add(addPublicationButton);
        frame.add(addStudentButton);
        frame.add(backButton);

        // Add action listeners to buttons
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookView();
            }
        });

        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCategoryView();
            }
        });

        addPublicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPublicationView();
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentView();
            }
        });

        frame.setVisible(true);
    }
}
