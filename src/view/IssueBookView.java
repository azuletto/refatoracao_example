package src.view;

import src.controller.IssueBookController;
import src.model.IssueBook;
import src.utils.ViewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssueBookView {
    private JFrame frame;
    private JTextField bookIdField;
    private JTextField studentIdField;
    private JTextField issueDateField;
    private JTextField returnDateField;
    private JButton issueButton;

    public IssueBookView() {
        frame = new JFrame("Issue Book");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        bookIdField = new JTextField();
        studentIdField = new JTextField();
        issueDateField = new JTextField();
        returnDateField = new JTextField();
        issueButton = new JButton("Issue");
        JButton backButton = ViewUtils.createBackButton(frame);
        frame.add(backButton);


        // Set bounds
        bookIdField.setBounds(50, 50, 200, 20);
        studentIdField.setBounds(50, 80, 200, 20);
        issueDateField.setBounds(50, 110, 200, 20);
        returnDateField.setBounds(50, 140, 200, 20);
        issueButton.setBounds(100, 180, 100, 20);

        // Add components to frame
        frame.add(bookIdField);
        frame.add(studentIdField);
        frame.add(issueDateField);
        frame.add(returnDateField);
        frame.add(issueButton);

        // Add action listener to button
        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IssueBook issueBook = new IssueBook();
                issueBook.setBookId(Integer.parseInt(bookIdField.getText()));
                issueBook.setStudentId(Integer.parseInt(studentIdField.getText()));
                issueBook.setIssueDate(issueDateField.getText());
                issueBook.setReturnDate(returnDateField.getText());

                IssueBookController issueBookController = new IssueBookController();
                issueBookController.issueBook(issueBook);
                JOptionPane.showMessageDialog(frame, "Book issued successfully!");
            }
        });

        frame.setVisible(true);
    }
}
