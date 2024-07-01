package Add;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddPublication extends JPanel {
    private final Connection con = DBInfo.getConn();
    private JTextField textField;
    private JTextField textField_1;

    public AddPublication() {
        initComponents();
    }

    private void initComponents() {
        JLabel lblAddNewPublication = new JLabel("Add New Publication");
        lblAddNewPublication.setFocusable(false);
        lblAddNewPublication.setFont(lblAddNewPublication.getFont().deriveFont(13.0f));

        JLabel label_1 = new JLabel("Name");
        label_1.setFocusable(false);

        JLabel label_2 = new JLabel("Id");
        label_2.setFocusable(false);

        JLabel label_3 = new JLabel("");
        label_3.setFocusable(false);

        JButton buttonSave = new JButton("Save");
        buttonSave.addActionListener(this::handleSaveAction);
        buttonSave.setFocusable(false);

        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(this::handleResetAction);
        buttonReset.setFocusable(false);

        textField = new JTextField(10);
        textField_1 = new JTextField(10);

        createLayout(lblAddNewPublication, label_1, label_2, label_3, buttonSave, buttonReset);
    }

    private void createLayout(JLabel lblAddNewPublication, JLabel label_1, JLabel label_2, JLabel label_3, JButton buttonSave, JButton buttonReset) {
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
                            .addGap(173)
                            .addComponent(lblAddNewPublication, GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                            .addGap(146)))
                    .addGap(0))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAddNewPublication)
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

        if (isPublicationExists(id, name)) {
            JOptionPane.showMessageDialog(getParent(), "Publication Already Exists", "Error!", JOptionPane.ERROR_MESSAGE);
            handleResetAction(e);
        } else {
            savePublication(id, name);
            handleResetAction(e);
            refreshLibrarianPage();
        }
    }

    private void handleResetAction(ActionEvent e) {
        textField.setText(null);
        textField_1.setText(null);
    }

    private boolean isPublicationExists(String id, String name) {
        String query = "SELECT * FROM publication WHERE id = ? OR name = ?";
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

    private void savePublication(String id, String name) {
        String query = "INSERT INTO publication (id, name) VALUES (?, ?)";
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
        lp.addPublication.setVisible(true);
        lp.addCategory.setVisible(false);
        lp.addSubject.setVisible(false);
        lp.welcome.setVisible(false);
        lp.allBooks.setVisible(false);
        lp.editStudent.setVisible(false);
        lp.editStudentButtons.setVisible(false);
    }
}
