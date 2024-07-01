package Add;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import DBInfo;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class AddStudent extends JPanel {
    private final Connection con = DBInfo.getConn();
    private JTextField textFieldName;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public AddStudent() {
        initComponents();
    }

    private void initComponents() {
        JLabel lblAddNewStudent = new JLabel("Add New Student");
        lblAddNewStudent.setFocusable(false);
        lblAddNewStudent.setFont(lblAddNewStudent.getFont().deriveFont(13.0f));

        JLabel lblName = new JLabel("Name");
        lblName.setFocusable(false);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFocusable(false);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFocusable(false);

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFocusable(false);

        textFieldName = new JTextField(10);
        textFieldUsername = new JTextField(10);
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(this::handleResetAction);
        btnReset.setFocusable(false);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(this::handleSaveAction);
        btnSave.setFocusable(false);

        createLayout(lblAddNewStudent, lblName, lblUsername, lblPassword, lblConfirmPassword, btnReset, btnSave);
    }

    private void createLayout(JLabel lblAddNewStudent, JLabel lblName, JLabel lblUsername, JLabel lblPassword, JLabel lblConfirmPassword, JButton btnReset, JButton btnSave) {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblAddNewStudent)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblConfirmPassword))
                            .addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(textFieldUsername)
                                .addComponent(textFieldName)
                                .addComponent(passwordField)
                                .addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                    .addGap(56))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAddNewStudent)
                    .addGap(48)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(50)
                            .addComponent(lblName)
                            .addGap(47)
                            .addComponent(lblUsername))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(35)
                            .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(54)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPassword)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(44)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblConfirmPassword)
                        .addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnReset)
                        .addComponent(btnSave))
                    .addGap(40))
        );
        setLayout(groupLayout);
    }

    private void handleResetAction(ActionEvent e) {
        textFieldName.setText(null);
        textFieldUsername.setText(null);
        passwordField.setText(null);
        confirmPasswordField.setText(null);
    }

    private void handleSaveAction(ActionEvent e) {
        String name = textFieldName.getText();
        String username = textFieldUsername.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (isStudentExists(name, username)) {
            JOptionPane.showMessageDialog(getParent(), "Student Already Exists", "Error!", JOptionPane.ERROR_MESSAGE);
            handleResetAction(e);
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(getParent(), "Password didn't match", "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(null);
            confirmPasswordField.setText(null);
        } else {
            saveStudent(name, username, password);
            handleResetAction(e);
            refreshLibrarianPage();
        }
    }

    private boolean isStudentExists(String name, String username) {
        String query = "SELECT * FROM student WHERE username = ? OR name = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, name);
            ResultSet res = ps.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveStudent(String name, String username, String password) {
        String query = "INSERT INTO student (name, username, password) VALUES (?, ?, ?)";
        String query2 = "INSERT INTO login (fullname, username, usertype, password) VALUES (?, ?, 'student', ?)";
        try (PreparedStatement ps = con.prepareStatement(query);
             PreparedStatement ps2 = con.prepareStatement(query2)) {
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();

            ps2.setString(1, name);
            ps2.setString(2, username);
            ps2.setString(3, password);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshLibrarianPage() {
        LibrarianPage lp = new LibrarianPage();
        lp.dispose();
        lp.setVisible(true);
        lp.addStudent.setVisible(true);
        lp.addBook.setVisible(false);
        lp.addAuthor.setVisible(false);
        lp.addPublication.setVisible(false);
        lp.addCategory.setVisible(false);
        lp.addSubject.setVisible(false);
        lp.welcome.setVisible(false);
        lp.allBooks.setVisible(false);
        lp.editStudent.setVisible(false);
        lp.editStudentButtons.setVisible(false);
    }
}
