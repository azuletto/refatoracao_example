package Edit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class EditStudentUserName extends JFrame {
    
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField textField_1;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(EditStudentUserName::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            EditStudentUserName frame = new EditStudentUserName();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public EditStudentUserName() {
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 375, 303);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("Edit Username");
        setLocationRelativeTo(this);

        JButton button = new JButton("Reset");
        button.addActionListener(e -> resetFields());

        JButton button_1 = new JButton("Save");
        button_1.addActionListener(e -> saveUserName());

        JLabel lblNewUsername = new JLabel("New Username");
        JLabel label_1 = new JLabel("Confirm Password");
        JLabel lblOldUsername = new JLabel("Old Username");

        passwordField = new JPasswordField();
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField = new JTextField();
        textField.setColumns(10);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(43)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label_1)
                        .addComponent(lblNewUsername, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOldUsername, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                    .addGap(16))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(29)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblOldUsername)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(40)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNewUsername)
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(40)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(3)
                            .addComponent(label_1))
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(45)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button)
                        .addComponent(button_1)))
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void resetFields() {
        textField_1.setText(null);
        textField.setText(null);
        passwordField.setText(null);
    }

    private void saveUserName() {
        String newUserName = textField_1.getText();
        String password = passwordField.getText();
        String oldUserName = textField.getText();
        
        if(oldUserName.isEmpty() || newUserName.isEmpty()) {
            JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
            return;
        }
        
        if (validateUser(oldUserName, password)) {
            updateUserName(oldUserName, newUserName, password);
            resetFields();
            updateLibrarianPage();
        } else {
            JOptionPane.showMessageDialog(getParent(), "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateUser(String oldUserName, String password) {
        String query = "SELECT * FROM student WHERE username = ? AND password = ?";
        try (Connection con = DBInfo.getConn();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, oldUserName);
            ps.setString(2, password);
            ResultSet res = ps.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateUserName(String oldUserName, String newUserName, String password) {
        String query1 = "UPDATE student SET Username = ? WHERE Password = ?";
        String query2 = "UPDATE login SET Username = ? WHERE Password = ? AND usertype = 'student'";
        try (Connection con = DBInfo.getConn();
             PreparedStatement ps1 = con.prepareStatement(query1);
             PreparedStatement ps2 = con.prepareStatement(query2)) {
            ps1.setString(1, newUserName);
            ps1.setString(2, password);
            ps1.executeUpdate();
            
            ps2.setString(1, newUserName);
            ps2.setString(2, password);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateLibrarianPage() {
        LibrarianPage lp = new LibrarianPage();
        lp.setVisible(true);
        lp.dispose();
        dispose();

        lp.addStudent.setVisible(false);
        lp.addBook.setVisible(false);
        lp.addAuthor.setVisible(false);
        lp.addPublication.setVisible(false);
        lp.addCategory.setVisible(false);
        lp.addSubject.setVisible(false);
        lp.welcome.setVisible(false);
        lp.allBooks.setVisible(false);
        lp.editStudent.setVisible(true);
        lp.editStudentButtons.setVisible(true);
    }
}
