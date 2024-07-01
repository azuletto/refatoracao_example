package Add;
package Edit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Db.DBInfo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditAuthor extends JFrame {

    private JPanel contentPane;
    private JTextField oldAuthorNameField;
    private JTextField newAuthorNameField;
    private AuthorDAO authorDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditAuthor frame = new EditAuthor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EditAuthor() {
        authorDAO = new AuthorDAO();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Edit Author");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 353, 289);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblOldAuthorName = new JLabel("Old Author Name");
        oldAuthorNameField = new JTextField();
        oldAuthorNameField.setColumns(10);

        JLabel lblNewAuthorName = new JLabel("New Author Name");
        newAuthorNameField = new JTextField();
        newAuthorNameField.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSaveButton();
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(32)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblOldAuthorName)
                        .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewAuthorName, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                    .addGap(52)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(newAuthorNameField)
                        .addComponent(oldAuthorNameField)
                        .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(49, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addGap(55)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblOldAuthorName)
                        .addComponent(oldAuthorNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(39)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewAuthorName)
                        .addComponent(newAuthorNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnReset)
                        .addComponent(btnSave))
                    .addGap(46))
        );
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(this);
    }

    private void handleSaveButton() {
        String oldAuthorName = oldAuthorNameField.getText();
        String newAuthorName = newAuthorNameField.getText();

        if (oldAuthorName.isEmpty() || newAuthorName.isEmpty()) {
            JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
            return;
        }

        boolean success = authorDAO.updateAuthorName(oldAuthorName, newAuthorName.toUpperCase());

        if (success) {
            JOptionPane.showMessageDialog(getParent(), "Record Updated!");
        } else {
            JOptionPane.showMessageDialog(getParent(), "No record found");
        }

        resetFields();
    }

    private void resetFields() {
        oldAuthorNameField.setText(null);
        newAuthorNameField.setText(null);
    }
}

class AuthorDAO {
    private Connection con;

    public AuthorDAO() {
        con = DBInfo.getConn();
    }

    public boolean updateAuthorName(String oldName, String newName) {
        String query = "UPDATE author SET name = ? WHERE name = ?";
        String query1 = "SELECT name FROM author WHERE name = ?";
        try {
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, oldName);
            ResultSet res = ps1.executeQuery();
            if (res.next()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, newName);
                ps.setString(2, oldName);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
