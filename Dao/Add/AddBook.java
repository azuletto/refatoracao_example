package Add;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBook extends JPanel {
    private static final String ERROR_MESSAGE = "Fill all section";
    private static final String ERROR_TITLE = "Error";
    private static final String DEFAULT_COMBOBOX_ITEM = "select";

    private JTextField idField;
    private JTextField titleField;
    private JTextField isbnField;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<String> authorComboBox;
    private JComboBox<String> publicationComboBox;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> subjectComboBox;

    public AddBook() {
        initializeComponents();
        configureLayout();
    }

    private void initializeComponents() {
        JLabel lblTitle = createLabel("Title");
        JLabel lblAuthor = createLabel("Author");
        JLabel lblPublication = createLabel("Publication");
        JLabel lblCategory = createLabel("Category");
        JLabel lblSubject = createLabel("Subject");
        JLabel lblIsbn = createLabel("ISBN");
        JLabel lblPrice = createLabel("Price");
        JLabel lblQuantity = createLabel("Quantity");
        JLabel lblId = createLabel("Id");

        idField = createNonEditableTextField(generateRandomId());
        titleField = createEditableTextField();
        isbnField = createEditableTextField();
        priceField = createEditableTextField();
        quantityField = createEditableTextField();

        authorComboBox = createComboBox("author");
        publicationComboBox = createComboBox("publication");
        categoryComboBox = createComboBox("category");
        subjectComboBox = createComboBox("subject");

        JButton btnSave = createButton("Save", this::handleSaveAction);
        JButton btnReset = createButton("Reset", this::handleResetAction);

        JLabel lblAddNewBook = createLabel("Add New Book");
        lblAddNewBook.setFont(lblAddNewBook.getFont().deriveFont(13.0f));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(49)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblId, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIsbn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPublication, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAuthor, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(isbnField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(subjectComboBox, Alignment.TRAILING, 0, 212, Short.MAX_VALUE)
                        .addComponent(categoryComboBox, 0, 212, Short.MAX_VALUE)
                        .addComponent(priceField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(publicationComboBox, 0, 212, Short.MAX_VALUE)
                        .addComponent(authorComboBox, 0, 212, Short.MAX_VALUE)
                        .addComponent(idField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(btnSave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(quantityField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(titleField, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(lblAddNewBook, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                    .addGap(156))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAddNewBook)
                    .addGap(28)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblId))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitle))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(authorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAuthor))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(publicationComboBox)
                        .addComponent(lblPublication))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCategory)
                        .addComponent(categoryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(subjectComboBox)
                        .addComponent(lblSubject))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(isbnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIsbn))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPrice)
                        .addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblQuantity)
                        .addComponent(quantityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnReset)
                        .addComponent(btnSave))
                    .addGap(28))
        );
        setLayout(groupLayout);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFocusable(false);
        return label;
    }

    private JTextField createNonEditableTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setEditable(false);
        textField.setColumns(10);
        return textField;
    }

    private JTextField createEditableTextField() {
        JTextField textField = new JTextField();
        textField.setColumns(10);
        return textField;
    }

    private JComboBox<String> createComboBox(String type) {
        JComboBox<String> comboBox = new JComboBox<>(DBInfo.getValues(type));
        comboBox.setFocusable(false);
        return comboBox;
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }

    private String generateRandomId() {
        StringBuilder id = new StringBuilder();
        for (int a = 0; a < 10; a++) {
            id.append((int) (Math.random() * 10));
        }
        return id.toString();
    }

    private void handleSaveAction(ActionEvent e) {
        String id = idField.getText();
        String title = titleField.getText();
        String author = (String) authorComboBox.getSelectedItem();
        String publication = (String) publicationComboBox.getSelectedItem();
        String category = (String) categoryComboBox.getSelectedItem();
        String subject = (String) subjectComboBox.getSelectedItem();
        String isbn = isbnField.getText();
        String price = priceField.getText();
        String quantity = quantityField.getText();

        if (isAnyFieldEmpty(title, isbn, price, quantity) || isAnyComboBoxDefault(author, publication, category, subject)) {
            JOptionPane.showMessageDialog(getParent(), ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        } else {
            saveBook(id, title, author, publication, category, subject, isbn, price, quantity);
            resetFields();
        }
    }

    private boolean isAnyFieldEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyComboBoxDefault(String... comboBoxValues) {
        for (String value : comboBoxValues) {
            if (DEFAULT_COMBOBOX_ITEM.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private void saveBook(String id, String title, String author, String publication, String category, String subject, String isbn, String price, String quantity) {
        String query = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBInfo.getConn();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, publication);
            ps.setString(5, category);
            ps.setString(6, subject);
            ps.setString(7, isbn);
            ps.setString(8, price);
            ps.setString(9, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetFields() {
        idField.setText(generateRandomId());
        titleField.setText(null);
        isbnField.setText(null);
        priceField.setText(null);
        quantityField.setText(null);
        authorComboBox.setSelectedIndex(0);
        publicationComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
        subjectComboBox.setSelectedIndex(0);
    }

    private void handleResetAction(ActionEvent e) {
        resetFields();
    }
}
