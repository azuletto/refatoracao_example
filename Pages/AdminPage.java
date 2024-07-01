import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPage extends JFrame {

    private static final String INSERT_QUERY = "insert into login(fullname,username,usertype,email,mobile,password) values(?,?,?,?,?,?)";
    private static final String SELECT_QUERY = "select * from login";

    private Connection con = DBInfo.getConn();
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JTextField textField_4;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() -> {
            try {
                AdminPage frame = new AdminPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminPage() {
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 484, 585);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblFullName = new JLabel("Full Name");
        textField = new JTextField();
        textField.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        textField_1 = new JTextField();
        textField_1.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        textField_2 = new JTextField();
        textField_2.setColumns(10);

        JLabel lblMobileNo = new JLabel("Mobile No.");
        textField_3 = new JTextField();
        textField_3.setColumns(10);

        JLabel lblPassword = new JLabel("Password");

        JButton btnCreate = new JButton("Save");
        btnCreate.addActionListener(this::handleCreateButton);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> clearFields());

        JLabel lblAddLibrarian = new JLabel("Add User");
        lblAddLibrarian.setFont(lblAddLibrarian.getFont().deriveFont(13.0f));

        passwordField = new JPasswordField();

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        passwordField_1 = new JPasswordField();

        JLabel lblUsertype = new JLabel("Usertype");
        textField_4 = new JTextField();
        textField_4.setColumns(10);

        createLayout(lblFullName, lblUsername, lblEmail, lblMobileNo, lblPassword, btnCreate, btnReset, lblAddLibrarian, lblConfirmPassword, lblUsertype);
        setTitle("Admin Page");
        setLocationRelativeTo(null);
    }

    private void createLayout(JLabel lblFullName, JLabel lblUsername, JLabel lblEmail, JLabel lblMobileNo, JLabel lblPassword, JButton btnCreate, JButton btnReset, JLabel lblAddLibrarian, JLabel lblConfirmPassword, JLabel lblUsertype) {
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(192)
                                .addComponent(lblAddLibrarian, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addGap(165))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(44)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMobileNo, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(lblFullName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                                        .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblUsertype, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnCreate, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        .addComponent(textField_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        .addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                        .addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                        .addComponent(passwordField, Alignment.LEADING, 212, 231, Short.MAX_VALUE)
                                        .addComponent(passwordField_1, Alignment.LEADING, 212, 231, Short.MAX_VALUE)
                                        .addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                        .addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                                .addGap(41))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblAddLibrarian)
                                .addGap(30)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblFullName)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(38)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblUsername)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(44)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblUsertype)
                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(43)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblEmail)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(46)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblMobileNo)
                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(48)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPassword))
                                .addGap(46)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblConfirmPassword))
                                .addGap(50)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(18))
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void handleCreateButton(ActionEvent e) {
        try {
            if (isUserExists()) {
                showMessage("User Already Exits", "Error !", JOptionPane.ERROR_MESSAGE);
                clearFields();
            } else if (passwordsMatch()) {
                saveUser();
                clearFields();
            } else {
                showMessage("Password didn't match", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField_1.setText(null);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isUserExists() throws SQLException {
        String a = textField.getText();
        String b = textField_1.getText();

        try (PreparedStatement ps2 = con.prepareStatement(SELECT_QUERY);
             ResultSet res = ps2.executeQuery()) {
            while (res.next()) {
                String id = res.getString(1);
                String Name = res.getString(2);
                if (id.equalsIgnoreCase(a) || Name.equalsIgnoreCase(b)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean passwordsMatch() {
        String p1 = new String(passwordField.getPassword());
        String p2 = new String(passwordField_1.getPassword());
        return p1.equals(p2);
    }

    private void saveUser() throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, textField.getText().toUpperCase());
            ps.setString(2, textField_1.getText().toUpperCase());
            ps.setString(3, textField_4.getText().toUpperCase());
            ps.setString(4, textField_2.getText().toUpperCase());
            ps.setString(5, textField_3.getText().toUpperCase());
            ps.setString(6, new String(passwordField.getPassword()));
            ps.executeUpdate();
        }
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    private void clearFields() {
        textField.setText(null);
        textField_1.setText(null);
        textField_2.setText(null);
        textField_3.setText(null);
        textField_4.setText(null);
        passwordField.setText(null);
        passwordField_1.setText(null);
    }
}
