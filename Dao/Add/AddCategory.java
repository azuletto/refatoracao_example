package Add;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCategory extends JPanel {
    private final Connection con = DBInfo.getConn();
    private JTextField textField;
    private JTextField textField_1;

    public AddCategory() {
        initComponents();
    }

    private void initComponents() {
        JLabel lblAddNewCategory = new JLabel("Add New Category");
        lblAddNewCategory.setFocusable(false);
        lblAddNewCategory.setFont(lblAddNewCategory.getFont().deriveFont(13.0f));

        JLabel label_1 = new JLabel("Name");
        label_1.setFocusable(false);

        JLabel label_2 = new JLabel("Id");
        label_2.setFocusable(false);

        JLabel label_3 = new JLabel("");

        JButton buttonSave = new JButton("Save");
        buttonSave.addActionListener(this::handleSaveAction);
        buttonSave.setOpaque(false);
        buttonSave.setFocusable(false);

        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(this::handleResetAction);
        buttonReset.setOpaque(false);
        buttonReset.setFocusable(false);

        textField = new JTextField(10);
        textField_1 = new JTextField(10);

        createLayout(lblAddNewCategory, label_1, label_2, label_3, buttonSave, buttonReset);
    }

    private void createLayout(JLabel lblAddNewCategory, JLabel label_1, JLabel label_2, JLabel label_3, JButton buttonSave, JButton buttonReset) {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(label_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                                .addGap(67)
                                                .addComponent(textField, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                .addGap(10))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                                .addGap(67)
                                                .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                .addGap(10))
                                        .addComponent(label_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(buttonSave, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                                                .addGap(10))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(buttonReset, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                                                .addGap(10))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(182)
                                                .addComponent(lblAddNewCategory, GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
                                                .addGap(145)))
                                .addGap(0))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblAddNewCategory)
                                .addGap(62)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(label_2))
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(60)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(label_1))
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(41)
                                .addComponent(label_3, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                .addGap(37)
                                .addComponent(buttonSave, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addGap(38)
                                .addComponent(buttonReset, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(53, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }

    private void handleSaveAction(ActionEvent e) {
        String id = textField.getText().toUpperCase();
        String name = textField_1.getText().toUpperCase();

        if (isCategoryExists(id, name)) {
            JOptionPane.showMessageDialog(getParent(), "Category Already Exists", "Error!", JOptionPane.ERROR_MESSAGE);
            handleResetAction(e);
        } else {
            saveCategory(id, name);
            handleResetAction(e);
            refreshLibrarianPage();
        }
    }

    private void handleResetAction(ActionEvent e) {
        textField.setText(null);
        textField_1.setText(null);
    }

    private boolean isCategoryExists(String id, String name) {
        String query = "SELECT * FROM category WHERE id = ? OR name = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ResultSet res = ps.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveCategory(String id, String name) {
        String query = "INSERT INTO category (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshLibrarianPage() {
        LibrarianPage lp = new LibrarianPage();
        lp.dispose();
        lp.setVisible(true);
        lp.addStudent.setVisible(false);
        lp.addBook.setVisible(false);
        lp.addAuthor.setVisible(false);
        lp.addPublication.setVisible(false);
        lp.addCategory.setVisible(true);
        lp.addSubject.setVisible(false);
        lp.welcome.setVisible(false);
        lp.allBooks.setVisible(false);
        lp.editStudent.setVisible(false);
        lp.editStudentButtons.setVisible(false);
    }
}
