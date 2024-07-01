
package Edit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditBook_1 extends JFrame {

    private JPanel contentPane;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_2;
    private JTextField textField_1;
    private String bookID;
    private BookService bookService;
    private JComboBox<String> comboBox_3;
    private JComboBox<String> comboBox_2;
    private JComboBox<String> comboBox_1;
    private JComboBox<String> comboBox;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() -> {
            try {
                EditBook_1 frame = new EditBook_1("");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EditBook_1(String str) {
        bookID = str;
        bookService = new BookService();

        setTitle("Edit Book");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 458, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(this);

        JLabel label_1 = new JLabel("Quantity");
        label_1.setFocusable(false);

        JLabel label_2 = new JLabel("Price");
        label_2.setFocusable(false);

        JLabel label_3 = new JLabel("ISBN");
        label_3.setFocusable(false);

        JLabel label_4 = new JLabel("Subject");
        label_4.setFocusable(false);

        JLabel label_5 = new JLabel("Category");
        label_5.setFocusable(false);

        JLabel label_6 = new JLabel("Publication");
        label_6.setFocusable(false);

        JLabel label_7 = new JLabel("Author");
        label_7.setFocusable(false);

        JLabel label_8 = new JLabel("Title");
        label_8.setFocusable(false);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        comboBox_3 = new JComboBox<>(bookService.getAuthors());
        comboBox_3.setFocusable(false);

        comboBox_2 = new JComboBox<>(bookService.getPublications());
        comboBox_2.setFocusable(false);

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        comboBox_1 = new JComboBox<>(bookService.getCategories());
        comboBox_1.setFocusable(false);

        comboBox = new JComboBox<>(bookService.getSubjects());
        comboBox.setFocusable(false);

        textField_5 = new JTextField();
        textField_5.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        JLabel lblEditBook_1 = new JLabel("Edit Book");

        JLabel label = new JLabel("Id");
        label.setFocusable(false);

        textField_1 = new JTextField((String) null);
        textField_1.setEditable(false);
        textField_1.setColumns(10);

        loadBookDetails();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap(187, Short.MAX_VALUE)
                    .addComponent(lblEditBook_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    .addGap(146))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap(33, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(label, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addGap(68)
                            .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_8, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68)
                                    .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_7, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68)
                                    .addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_6, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                    .addGap(55)
                                    .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_5, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                    .addGap(43)
                                    .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68)
                                    .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68)
                                    .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68)
                                    .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                            .addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(ComponentPlacement.RELATED)
                                            .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))))
                            .addGap(27))))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblEditBook_1)
                    .addGap(20)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(13)
                            .addComponent(label))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(10)
                            .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE