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

import DBInfo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSubject extends JFrame {

    private static final String TITLE = "Edit Subject";
    private static final String OLD_SUBJECT_NAME_LABEL = "Old Subject Name";
    private static final String NEW_SUBJECT_NAME_LABEL = "New Subject Name";
    private static final String RESET_BUTTON_LABEL = "Reset";
    private static final String SAVE_BUTTON_LABEL = "Save";
    private static final String EMPTY_FIELDS_ERROR = "Empty fields not allowed";
    private static final String RECORD_UPDATED_MESSAGE = "Record Updated !";
    private static final String NO_RECORD_FOUND_MESSAGE = "No record found";
    
    private JPanel contentPane;
    private JTextField oldSubjectField;
    private JTextField newSubjectField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EditSubject frame = new EditSubject();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public EditSubject() {
        initializeFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 428, 292);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(this);
    }

    private void initializeComponents() {
        JLabel lblOldSubjectName = new JLabel(OLD_SUBJECT_NAME_LABEL);
        JLabel lblNewSubjectName = new JLabel(NEW_SUBJECT_NAME_LABEL);

        oldSubjectField = new JTextField();
        oldSubjectField.setColumns(10);

        newSubjectField = new JTextField();
        newSubjectField.setColumns(10);

        JButton btnReset = createResetButton();
        JButton btnSave = createSaveButton();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(41)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblOldSubjectName, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewSubjectName, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oldSubjectField)
                        .addComponent(newSubjectField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addContainerGap(63, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(39)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(oldSubjectField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOldSubjectName))
                    .addGap(45)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewSubjectName)
                        .addComponent(newSubjectField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnReset))
                    .addGap(51))
        );
        contentPane.setLayout(gl_contentPane);
    }

    private JButton createResetButton() {
        JButton btnReset = new JButton(RESET_BUTTON_LABEL);
        btnReset.addActionListener(e -> resetFields());
        return btnReset;
    }

    private JButton createSaveButton() {
        JButton btnSave = new JButton(SAVE_BUTTON_LABEL);
        btnSave.addActionListener(e -> saveSubject());
        return btnSave;
    }

    private void resetFields() {
        oldSubjectField.setText(null);
        newSubjectField.setText(null);
    }

    private void saveSubject() {
        String oldSubName = oldSubjectField.getText();
        String newSubName = newSubjectField.getText();

        if (oldSubName.isEmpty() || newSubName.isEmpty()) {
            JOptionPane.showMessageDialog(this, EMPTY_FIELDS_ERROR);
            return;
        }

        try (Connection con = DBInfo.getConn()) {
            if (updateSubjectName(con, oldSubName, newSubName)) {
                JOptionPane.showMessageDialog(this, RECORD_UPDATED_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, NO_RECORD_FOUND_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resetFields();
    }

    private boolean updateSubjectName(Connection con, String oldSubName, String newSubName) throws SQLException {
        String queryUpdate = "update author set name = ? where name = ?";
        String querySelect = "select name from author where name = ?";
        
        try (PreparedStatement psSelect = con.prepareStatement(querySelect)) {
            psSelect.setString(1, oldSubName);
            try (ResultSet res = psSelect.executeQuery()) {
                if (res.next()) {
                    try (PreparedStatement psUpdate = con.prepareStatement(queryUpdate)) {
                        psUpdate.setString(1, newSubName.toUpperCase());
                        psUpdate.setString(2, oldSubName);
                        psUpdate.executeUpdate();
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }
}
