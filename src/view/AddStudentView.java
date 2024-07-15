package src.view;

import src.controller.StudentController;
import src.model.Student;
import src.utils.ViewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentView {
    private JFrame frame;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton addButton;

    public AddStudentView() {
        frame = new JFrame("Add Student");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        nameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        addButton = new JButton("Add");
        JButton backButton = ViewUtils.createBackButton(frame);

        // Set bounds
        nameField.setBounds(50, 50, 200, 20);
        usernameField.setBounds(50, 80, 200, 20);
        passwordField.setBounds(50, 110, 200, 20);
        addButton.setBounds(100, 150, 100, 20);

        // Add components to frame
        frame.add(nameField);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(addButton);
        frame.add(backButton);

        // Add action listener to button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                student.setName(nameField.getText());
                student.setUsername(usernameField.getText());
                student.setPassword(new String(passwordField.getPassword()));

                StudentController studentController = new StudentController();
                studentController.addStudent(student);
                JOptionPane.showMessageDialog(frame, "Student added successfully!");
            }
        });

        frame.setVisible(true);
    }
}
