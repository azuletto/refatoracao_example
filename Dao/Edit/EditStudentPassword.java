package Edit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class EditStudentPassword extends JFrame {

    private static final String UPDATE_STUDENT_PASSWORD = "UPDATE student SET password = ? WHERE username = ?";
    private static final String SELECT_STUDENT = "SELECT * FROM student WHERE username = ? AND password = ?";
    private static final String UPDATE_LOGIN_PASSWORD = "UPDATE login SET password = ? WHERE username = ? AND usertype = 'student'";
    
    private Connection con;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() -> {
            try {
                EditStudentPassword frame = new EditStudentPassword(DBInfo.getConn());
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EditStudentPassword(Connection connection) {
        this.con = connection;
        setTitle("Edit Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 370, 303);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(this);

        JLabel lblUsername = new JLabel("Username");
        JLabel lblOldPassword = new JLabel("Old Password");
        JLabel lblNewPassword = new JLabel("New Password");

        usernameField = new JTextField();
        usernameField.setColumns(10);

        oldPasswordField = new JPasswordField();
        newPasswordField = new JPasswordField();

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetFields());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> savePassword());

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblUsername)
                                .addComponent(lblOldPassword)
                                .addComponent(lblNewPassword))
                            .addGap(30)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(usernameField)
                                .addComponent(oldPasswordField)
                                .addComponent(newPasswordField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(40)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblUsername)
                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(49)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblOldPassword)
                        .addComponent(oldPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(43)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewPassword)
                        .addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(42)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(resetButton)
                        .addComponent(saveButton))
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void resetFields() {
        usernameField.setText(null);
        oldPasswordField.setText(null);
        newPasswordField.setText(null);
    }

    private void savePassword() {
        String username = usernameField.getText();
        String oldPassword = new String(oldPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Empty fields not allowed");
            return;
        }

        try (PreparedStatement ps1 = con.prepareStatement(SELECT_STUDENT);
             PreparedStatement ps2 = con.prepareStatement(UPDATE_STUDENT_PASSWORD);
             PreparedStatement ps3 = con.prepareStatement(UPDATE_LOGIN_PASSWORD)) {

            ps1.setString(1, username);
            ps1.setString(2, oldPassword);

            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                ps2.setString(1, newPassword);
                ps2.setString(2, username);
                ps2.executeUpdate();

                ps3.setString(1, newPassword);
                ps3.setString(2, username);
                ps3.executeUpdate();

                JOptionPane.showMessageDialog(this, "Password updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        resetFields();
        refreshLibrarianPage();
    }

    private void refreshLibrarianPage() {
        LibrarianPage lp = new LibrarianPage();
        lp.dispose();
        lp.setVisible(true);
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
        dispose();
    }
}
