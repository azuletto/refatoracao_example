package src.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        

        // Set bounds
        usernameField.setBounds(50, 50, 200, 20);
        passwordField.setBounds(50, 80, 200, 20);
        loginButton.setBounds(100, 120, 100, 20);

        // Add components to frame
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginButton);

        // Add action listener to button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.equals("admin") && password.equals("admin123")) {
                    new AdminPageView();
                } else {
                    new LibrarianPageView();
                }
            }
        });

        frame.setVisible(true);
    }
}
