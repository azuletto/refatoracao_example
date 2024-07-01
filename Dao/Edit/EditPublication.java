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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EditPublication extends JFrame {

    private Connection con;
    private JPanel contentPane;
    private JTextField oldNameTextField;
    private JTextField newNameTextField;
    private PublicationService publicationService;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EditPublication frame = new EditPublication();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public EditPublication() {
        con = Db.DBInfo.getConn();
        publicationService = new PublicationService(con);

        setTitle("Edit Publication");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblOldPublicationName = new JLabel("Old Publication Name");
        JLabel lblNewPublicationName = new JLabel("New Publication Name");

        oldNameTextField = new JTextField();
        oldNameTextField.setColumns(10);

        newNameTextField = new JTextField();
        newNameTextField.setColumns(10);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> resetFields());

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> savePublication());

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(48)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblOldPublicationName, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewPublicationName)
                        .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newNameTextField)
                        .addComponent(oldNameTextField, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                    .addContainerGap(37, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(54)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblOldPublicationName)
                        .addComponent(oldNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(37)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewPublicationName)
                        .addComponent(newNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(45)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnReset)
                        .addComponent(btnSave))
                    .addContainerGap(48, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(this);
    }

    private void resetFields() {
        oldNameTextField.setText(null);
        newNameTextField.setText(null);
    }

    private void savePublication() {
        String oldPubName = oldNameTextField.getText();
        String newPubName = newNameTextField.getText();

        if (oldPubName.isEmpty() || newPubName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Empty fields not allowed");
            return;
        }

        try {
            if (publicationService.updatePublicationName(oldPubName, newPubName)) {
                JOptionPane.showMessageDialog(this, "Record Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "No record found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resetFields();
    }
}

class PublicationService {
    private Connection con;

    public PublicationService(Connection con) {
        this.con = con;
    }

    public boolean updatePublicationName(String oldName, String newName) throws SQLException {
        String query = "update publication set name = ? where name = ?";
        String query1 = "select name from publication where name = ?";

        try (PreparedStatement ps1 = con.prepareStatement(query1)) {
            ps1.setString(1, oldName);
            try (ResultSet res = ps1.executeQuery()) {
                if (res.next()) {
                    try (PreparedStatement ps = con.prepareStatement(query)) {
                        ps.setString(1, newName.toUpperCase());
                        ps.setString(2, oldName);
                        ps.executeUpdate();
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }
}
