package Edit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class EditStudentName extends JFrame {

    Connection con = DBInfo.getConn();
    private JPanel contentPane;
    private JTextField oldNameField;
    private JTextField newNameField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() -> {
            try {
                EditStudentName frame = new EditStudentName();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public EditStudentName() {
        setTitle("Edit Name");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 375, 303);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblOldName = new JLabel("Old Name");

        JLabel lblNewName = new JLabel("New Name");

        JLabel lblConfirmPassword = new JLabel("Confirm Password");

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        oldNameField = new JTextField();
        oldNameField.setColumns(10);

        newNameField = new JTextField();
        newNameField.setColumns(10);

        passwordField = new JPasswordField();
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(35)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblNewName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblConfirmPassword, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOldName, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                            .addGap(18)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(passwordField)
                                .addComponent(newNameField)
                                .addComponent(oldNameField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))
                    .addContainerGap(58, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(25)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblOldName)
                        .addComponent(oldNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(28)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewName)
                        .addComponent(newNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(34)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblConfirmPassword)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(42)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnReset)
                        .addComponent(btnSave))
                    .addContainerGap(48, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(this);
    }

    private void handleSaveAction() {
        String newName = newNameField.getText();
        String password = new String(passwordField.getPassword());
        String oldName = oldNameField.getText();

        if (oldName.isEmpty() || newName.isEmpty()) {
            JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
            return;
        }

        String query = "UPDATE student SET name = ? WHERE password = ?";
        String query2 = "SELECT name, password FROM student WHERE name = ?";
        String query3 = "UPDATE login SET name = ? WHERE name = ? AND usertype = 'student'";

        try (PreparedStatement ps2 = con.prepareStatement(query2)) {
            ps2.setString(1, oldName);
            try (ResultSet res = ps2.executeQuery()) {
                if (res.next()) {
                    String dbName = res.getString("name");
                    String dbPassword = res.getString("password");

                    if (dbPassword.equals(password)) {
                        try (PreparedStatement ps = con.prepareStatement(query)) {
                            ps.setString(1, newName);
                            ps.setString(2, password);
                            ps.executeUpdate();

                            try (PreparedStatement ps3 = con.prepareStatement(query3)) {
                                ps3.setString(1, newName);
                                ps3.setString(2, oldName);
                                ps3.executeUpdate();
                            }

                            JOptionPane.showMessageDialog(getParent(), "Name updated successfully");
                            resetFields();
                        }
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Password doesn't match!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Name not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(getParent(), "An error occurred while updating the name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        oldNameField.setText(null);
        newNameField.setText(null);
        passwordField.setText(null);
    }
}
